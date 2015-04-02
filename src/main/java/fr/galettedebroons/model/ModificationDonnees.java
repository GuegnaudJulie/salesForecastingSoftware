package fr.galettedebroons.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.MargeLivraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.main.Main;

public class ModificationDonnees {
	
	Main main_;
	private EntityManager manager_;
	private EntityTransaction tx_;
	
	public ModificationDonnees(Main main){
		this.main_ = main;
		manager_ = main.getManager();
		tx_ = main.getTransaction();
		System.out.println("mon manager : " +manager_);
	}
	
	
	public void updateTauxReprise(double pourcen, String nomprofil, String nomproduit){
		tx_.begin();
		System.out.println("mon pourcen : " +pourcen);
		RecuperationDonnees rd = new RecuperationDonnees(main_);
		Produit np = rd.recuperationProduitComp(nomproduit);
		System.out.println("mon produit :  " +np);
		Profil pp = rd.recuperationProfil(nomprofil);
		System.out.println("mon profil ; " +pp);
		manager_.createQuery("UPDATE MargeLivraison m SET taux_reprise = :pourcentage "
				+ "WHERE m.marge_produit LIKE :paramcode_prod AND m.marge_profil LIKE :paramcode_client")
				.setParameter("pourcentage", pourcen)
				.setParameter("paramcode_prod", np)
				.setParameter("paramcode_client", pp)
				.executeUpdate();
		
		System.out.println("je passeeee");
		this.tx_ = main_.getTransaction();
		System.out.println("je re passe");
		tx_.commit();
	}

	public void updateLivraison(Livraison l, int qtePlus, int qteMoins){
		l.setQte_livraison(qtePlus);
		l.setQte_reprise(qteMoins);
	}
	
	public void insertNvTxReprise(double nvTauxReprise, String nomprofil, String nomproduit){
		tx_.begin();
		RecuperationDonnees rd = new RecuperationDonnees(main_);
		Produit np = rd.recuperationProduitComp(nomproduit);
		System.out.println("mon produit :  " +np);
		Profil pp = rd.recuperationProfil(nomprofil);
		System.out.println("mon profil ; " +pp);
		// double taux_reprise, Profil marge_profil, Produit marge_produit
		MargeLivraison margeL = new MargeLivraison(nvTauxReprise, pp, np);
		manager_.persist(margeL);
		tx_.commit();
		
	}
	
}
