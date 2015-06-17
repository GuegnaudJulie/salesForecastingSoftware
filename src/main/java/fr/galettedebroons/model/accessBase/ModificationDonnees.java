package fr.galettedebroons.model.accessBase;

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

public class ModificationDonnees {
	
	Main main_;
	private EntityManager manager_;
	private EntityTransaction tx_;
	
	public ModificationDonnees(Main main){
		this.main_ = main;
		manager_ = main.getManager();
		tx_ = main.getTransaction();
	}
	
	
	public void updateTauxReprise(double pourcen, String nomprofil, String nomproduit){
		RecupProduit rp = new RecupProduit(main_);
		RecupClientProfil rcp = new RecupClientProfil(main_);
		
		Produit np = rp.recuperationProduitComp(nomproduit);
		Profil pp = rcp.recupProfil(nomprofil);
		
		tx_.begin();
		
		manager_.createQuery("UPDATE MargeLivraison m SET taux_reprise = :pourcentage "
				+ "WHERE m.marge_produit LIKE :paramcode_prod AND m.marge_profil LIKE :paramcode_client")
				.setParameter("pourcentage", pourcen)
				.setParameter("paramcode_prod", np)
				.setParameter("paramcode_client", pp)
				.executeUpdate();
		
		tx_.commit();
	}

	public void updateLivraison(Livraison l, int qtePlus, int qteMoins){
		l.setQte_livraison(qtePlus);
		l.setQte_reprise(qteMoins);
	}
	
	public void insertNvTxReprise(double nvTauxReprise, String nomprofil, String nomproduit, boolean editer){
		RecupProduit rp = new RecupProduit(main_);
		RecupClientProfil rcp = new RecupClientProfil(main_);
		
		Produit np = rp.recuperationProduitComp(nomproduit);
		Profil pp = rcp.recupProfil(nomprofil);
		
		tx_.begin();
		
		MargeLivraison margeL = new MargeLivraison(nvTauxReprise, pp, np, editer);
		
		manager_.persist(margeL);
		tx_.commit();
	}
	
	public void updatePrevisionManuelle(Date date, int quantite, String produit, String profil){
		RecupPrevision rprev = new RecupPrevision(main_);
		RecupProduit rp = new RecupProduit(main_);
		RecupClientProfil rcp = new RecupClientProfil(main_);
		
		Produit np = rp.recuperationProduitComp(produit);
		Profil pp = rcp.recupProfil(profil);
		
		try{
			Prevision prev = rprev.prevision(pp, np, date);
			prev.setQuantite(quantite);
		} catch (Exception e){
			tx_.begin();
			
			Prevision p = new Prevision(quantite, pp, np, date);
			
			manager_.persist(p);
			tx_.commit();
		}
	}
	
	public void updateMargeLivraisonApresRepriseOuRupture(String profil, String produit, double taux_reprise, boolean editer){
		RecupProduit rp = new RecupProduit(main_);
		RecupClientProfil rcp = new RecupClientProfil(main_);
		
		Produit np = rp.recuperationProduitComp(produit);
		Profil pp = rcp.recupProfil(profil);
		
		tx_.begin();
		
		try{
			manager_.createQuery("select ml from MargeLivraison ml "
				+ "WHERE ml.marge_produit LIKE :paramcode_prod AND ml.marge_profil LIKE :paramcode_client"
				, MargeLivraison.class)
				.setParameter("paramcode_prod", np)
				.setParameter("paramcode_client", pp)
				.getSingleResult();
			
			manager_.createQuery("UPDATE MargeLivraison ml SET taux_reprise = :taux_reprise "
					+ "WHERE ml.marge_produit LIKE :paramcode_prod AND ml.marge_profil LIKE :paramcode_client")
					.setParameter("taux_reprise", taux_reprise)
					.setParameter("paramcode_prod", np)
					.setParameter("paramcode_client", pp)
					.executeUpdate();
			
			tx_.commit();
			
		}catch(NoResultException e){
			MargeLivraison ml = new MargeLivraison(taux_reprise, pp, np, editer);
			manager_.persist(ml);
			tx_.commit();
		}
	}
	
}
