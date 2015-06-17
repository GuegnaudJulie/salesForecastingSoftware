package fr.galettedebroons.model.accessBase;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.MargeLivraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.main.Main;

public class RecupMargeLivraison {

	Main main_;
	public EntityManager manager_;
	
	public RecupMargeLivraison(Main main){
		main_ = main;
		manager_ = main.getManager();
	}
	
	public MargeLivraison recuperationML(Profil profil, Produit produit){
		MargeLivraison ml = manager_.createQuery("select ml from MargeLivraison ml where " +
				"marge_profil LIKE :profil AND " +
				"marge_produit LIKE :prod", MargeLivraison.class)
				.setParameter("profil", profil)
				.setParameter("prod", produit)
				.setMaxResults(1)
				.getSingleResult();
		
		return ml;
	}

	public MargeLivraison recuperationML(Profil profil, String code_prod) {
		RecupProduit rp = new RecupProduit(main_);
		Produit produit = rp.recupProduit(code_prod);
		
		MargeLivraison ml = recuperationML(profil, produit);
		
		return ml;
	}
	
	public double recuperationTxReprise(String code_prod, String code_client){
		RecupProduit rp = new RecupProduit(main_);
		RecupClientProfil rcp = new RecupClientProfil(main_);
		
		Produit np = rp.recuperationProduitComp(code_prod);
		Profil pp = rcp.recupProfil(code_client);
		
		double res = 0.0;
		
		try{
			res = manager_.createQuery("select m.taux_reprise from MargeLivraison m " +
				"WHERE m.marge_produit LIKE :paramcode_prod AND m.marge_profil LIKE :paramcode_client ", Double.class)
				.setParameter("paramcode_prod", np)
				.setParameter("paramcode_client", pp).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
	}
	
	/**
	 * Recupere marge de livraison en fonction d'un profil et d'un produit
	 * pour intégrer à l'interface de prévision de vente
	 * tableau (prévision + marge)
	 * @return le taux de reprise 
	 */
	public double recuperationMargeLivraison(String profil, String produit){
		RecupProduit rp = new RecupProduit(main_);
		RecupClientProfil rcp = new RecupClientProfil(main_);
		
		double margeLivraison = 0.0;
		
		Profil monProfil = rcp.recupProfil(profil);
		Produit monProduit = rp.recuperationProduitComp(produit);
		
		margeLivraison = manager_.createQuery("select ml.taux_reprise from MargeLivraison "
				+"where ml.marge_profil LIKE :prof and ml.marge_produit LIKE :prod ", Double.class)
				.setParameter("prof", monProfil)
				.setParameter("prod", monProduit)
				.getSingleResult();
		
		return margeLivraison;
		
	}
}
