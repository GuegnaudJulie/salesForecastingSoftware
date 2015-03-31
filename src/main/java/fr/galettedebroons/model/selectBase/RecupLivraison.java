package fr.galettedebroons.model.selectBase;

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
		Livraison livr = manager_.createQuery("select l from Livraison l where " +
				"l.bon_livraison LIKE :bl AND " +
				"l.date_livraison LIKE :date" +
				"l.livraison_produit.code_produit LIKE :code", Livraison.class)
				.setParameter("bl", bl)
				.setParameter("date", date)
				.setParameter("code", code_prod)
				.getSingleResult();
		
		return livr;
	}

	public Livraison recupLivraisonPrec(Produit prod, Profil profil, Date date){
		Livraison livraison =  manager_.createQuery("select l from Livraison l WHERE " +
				"livraison_profil LIKE :profil AND " +
				"livraison_produit LIKE :produit AND " +
				"date_livraison < :date " +
				"ORDER BY date_livraison ASC LIMIT 1", Livraison.class)
				.setParameter("profil", profil)
				.setParameter("produit", prod)
				.setParameter("date", date)
				.getSingleResult();
		
		return livraison;
	}
	
	public List<Object[]> recuperationListLivraison(){
		List<Object[]> livr = manager_.createQuery("select l.bon_livraison, l.livraison_profil.code_client, l.livraison_produit.code_produit, l.date_livraison, l.qte_livraison, l.qte_reprise " +
				"from Livraison l", Object[].class)
				.getResultList();
		
		return livr;
	}
	
	public List<Livraison> recupLivraisonPrec(Produit prod, Profil profil, Date date1, Date date2){
		List<Livraison> livr = manager_.createQuery("select l from Livraison l where " +
				"l.date_livraison > :date1" +
				"l.date_livraison < :date2" +
				"l.livraison_produit LIKE :produit" +
				"l.livraison_profil LIKE :profil", Livraison.class)
				.setParameter("date1", date1)
				.setParameter("date2", date2)
				.setParameter("produit", prod)
				.setParameter("profil", profil)
				.getResultList();
		
		return livr;
	}
}
