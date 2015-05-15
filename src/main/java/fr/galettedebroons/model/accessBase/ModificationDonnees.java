package fr.galettedebroons.model;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.MargeLivraison;
import fr.galettedebroons.domain.Prevision;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.selectBase.RecupPrevision;

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
	
	public void insertNvTxReprise(double nvTauxReprise, String nomprofil, String nomproduit, boolean editer){
		tx_.begin();
		RecuperationDonnees rd = new RecuperationDonnees(main_);
		Produit np = rd.recuperationProduitComp(nomproduit);
		Profil pp = rd.recuperationProfil(nomprofil);
		// double taux_reprise, Profil marge_profil, Produit marge_produit
		MargeLivraison margeL = new MargeLivraison(nvTauxReprise, pp, np, editer);
		manager_.persist(margeL);
		tx_.commit();
	}
	
	public void updatePrevisionManuelle(Date date, int quantite, String produit, String profil){
		
		System.out.println("Je rentre !!!!!!!!!");
		
		RecuperationDonnees rd = new RecuperationDonnees(main_);
		RecupPrevision rp = new RecupPrevision(main_);
		
		Produit np = rd.recuperationProduitComp(produit);
		Profil pp = rd.recuperationProfil(profil);
		
		try{
			Prevision prev = rp.prevision(pp, np, date);
			prev.setQuantite(quantite);
			System.out.println("la quantite a change : " + prev.getQuantite());
		} catch (Exception e){
			tx_.begin();
			
			Prevision p = new Prevision(quantite, pp, np, date);
			manager_.persist(p);
			tx_.commit();
			
			System.out.println("J'ai créer une nouvelle prev ! : " + p.getQuantite());
		}
	}
	
	public void updateMargeLivraisonApresRepriseOuRupture(String profil, String produit, double taux_reprise, boolean editer){
		tx_.begin();
		RecuperationDonnees rd = new RecuperationDonnees(main_);
		Produit np = rd.recuperationProduitComp(produit);
		System.out.println("mon produit :  " +np);
		Profil pp = rd.recuperationProfil(profil);
		System.out.println("mon profilllllll ; " +pp);
		
		MargeLivraison margeL = null;
		try{margeL = manager_.createQuery("select ml from MargeLivraison ml "
				+ "WHERE ml.marge_produit LIKE :paramcode_prod AND ml.marge_profil LIKE :paramcode_client"
				, MargeLivraison.class)
				.setParameter("paramcode_prod", np)
				.setParameter("paramcode_client", pp)
				.getSingleResult();
		
			System.out.println("Ma ligne existe déjà donc je l'a met à jour");
			manager_.createQuery("UPDATE MargeLivraison ml SET taux_reprise = :taux_reprise "
					+ "WHERE ml.marge_produit LIKE :paramcode_prod AND ml.marge_profil LIKE :paramcode_client")
					.setParameter("taux_reprise", taux_reprise)
					.setParameter("paramcode_prod", np)
					.setParameter("paramcode_client", pp)
					.executeUpdate();
			
			this.tx_ = main_.getTransaction();
			tx_.commit();
			
		}catch(NoResultException e){
			System.out.println("PAs de ligne correspondante, création d'une nouvelle marge livraison !");
			MargeLivraison ml = new MargeLivraison(taux_reprise, pp, np, editer);
			main_.getManager().persist(ml);
			main_.getTransaction().commit();
		}
		
		/*if(margeL != null){
			System.out.println("Ma ligne existe déjà donc je l'a met à jour");
			manager_.createQuery("UPDATE MargeLivraison ml SET taux_reprise = :taux_reprise "
					+ "WHERE ml.marge_produit LIKE :paramcode_prod AND ml.marge_profil LIKE :paramcode_client")
					.setParameter("taux_reprise", taux_reprise)
					.setParameter("paramcode_prod", np)
					.setParameter("paramcode_client", pp)
					.executeUpdate();
			
			this.tx_ = main_.getTransaction();
			tx_.commit();
		
		}*//* else {
			System.out.println("PAs de ligne correspondante, création d'une nouvelle marge livraison !");
			MargeLivraison ml = new MargeLivraison(taux_reprise, pp, np);
			main_.getManager().persist(ml);
			main_.getTransaction().commit();
		}*/
		
	}
	
}
