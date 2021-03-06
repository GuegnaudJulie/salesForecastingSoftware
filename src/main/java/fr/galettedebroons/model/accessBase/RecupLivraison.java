package fr.galettedebroons.model.accessBase;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.main.Main;

public class RecupLivraison {

	public EntityManager manager_;
	
	public RecupLivraison(Main main){
		manager_ = main.getManager();
	}
	
	public Livraison recuperationLivraison(String bl, Date date, String code_prod){
		Livraison livr;
		
		try{
			livr = manager_.createQuery("select l from Livraison l where " +
					"l.bon_livraison LIKE :bl AND " +
					"l.date_livraison LIKE :date AND " +
					"l.livraison_produit.code_produit LIKE :code", Livraison.class)
					.setParameter("bl", bl)
					.setParameter("date", date)
					.setParameter("code", code_prod)
					.getSingleResult();
		} catch (Exception e){
			livr = null;
		}
		
		return livr;
	}
	
	public Livraison recupLivraison(Produit prod, Profil profil, Date date){
		Livraison livraison;
		try{
			livraison =  manager_.createQuery("select l from Livraison l WHERE " +
						"livraison_profil LIKE :profil AND " +
						"livraison_produit LIKE :produit AND " +
						"date_livraison LIKE :date", Livraison.class)
						.setParameter("profil", profil)
						.setParameter("produit", prod)
						.setParameter("date", date)
						.getSingleResult();
		} catch (Exception e){
			livraison = null;
		}
		
		return livraison;
	}

	public Livraison recupLivraisonPrec(Produit prod, Profil profil, Date date){
		Livraison livr =  manager_.createQuery("select l from Livraison l WHERE " +
						"livraison_profil LIKE :profil AND " +
						"livraison_produit LIKE :produit AND " +
						"date_livraison < :date " +
						"ORDER BY date_livraison DESC LIMIT 1", Livraison.class)
						.setParameter("profil", profil)
						.setParameter("produit", prod)
						.setParameter("date", date)
						.setMaxResults(1)
						.getSingleResult();
		
		return livr;
	}
	
	public List<Object[]> recuperationListLivraison(){
		List<Object[]> livr = manager_.createQuery("select l.bon_livraison, l.livraison_profil.code_client, l.livraison_produit.code_produit, l.date_livraison, l.qte_livraison, l.qte_reprise " +
				"from Livraison l", Object[].class)
				.getResultList();
		
		return livr;
	}
	
	public Livraison recupLivraisonPrec(Produit prod, Profil profil, Date date1, Date date2){
		Livraison livr = manager_.createQuery("select l from Livraison l where " +
				"l.date_livraison >= :date1 AND " +
				"l.date_livraison <= :date2 AND " +
				"l.livraison_produit LIKE :produit AND " +
				"l.livraison_profil LIKE :profil ", Livraison.class)
				.setParameter("date1", date1)
				.setParameter("date2", date2)
				.setParameter("produit", prod)
				.setParameter("profil", profil)
				.getSingleResult();
		
		return livr;
	}
}
