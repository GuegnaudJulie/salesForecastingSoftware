package fr.galettedebroons.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.test.Main;

public class ModificationDonnees {
	
	Main main_;
	private EntityManager manager_;
	private EntityTransaction tx;
	
	public ModificationDonnees(Main main){
		this.main_ = main;
		manager_ = main.getManager();
		System.out.println("mon manager : " +manager_);
	}
	
	
	public void updateTauxReprise(double pourcen, String nomprofil, String nomproduit){
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
		this.tx = main_.getTransaction();
		System.out.println("je re passe");
		tx.commit();
	}

	public void updateLivraison(Livraison l, int qtePlus, int qteMoins){
		tx.begin();
		
		manager_.createQuery("UPDATE Livraison l SET qte_livraison LIKE :qteLi AND" +
				"qte_repris LIKE :qteRe"
				+ "WHERE l LIKE :livraison")
				.setParameter("livraison", l)
				.setParameter("qteLi", qtePlus)
				.setParameter("qteRe", qteMoins)
				.executeUpdate();
		
		tx.commit();
	}
	
}
