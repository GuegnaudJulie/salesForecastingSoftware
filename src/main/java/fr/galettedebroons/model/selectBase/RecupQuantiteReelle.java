package fr.galettedebroons.model.selectBase;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.QuantiteReelle;
import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.RecuperationDonnees;

public class RecupQuantiteReelle {

	Main main_;
	public EntityManager manager_;
	
	public RecupQuantiteReelle(Main main){
		main_ = main;
		manager_ = main.getManager();
	}
	
	public QuantiteReelle recuperationQR(Profil profil, Produit produit, Date date1, Date date2){
		QuantiteReelle qr = manager_.createQuery("select q from QuantiteReelle q where "
				+ "reel_profil LIKE :profil AND "
				+ "reel_produit LIKE :prod AND "
				+ "date >= :d1 AND "
				+ "date <= :d2 ", QuantiteReelle.class)
				.setParameter("profil", profil)
				.setParameter("prod", produit)
				.setParameter("d1", date1)
				.setParameter("d2", date2)
				.getSingleResult();
		
		return qr;
	}

	public QuantiteReelle recuperationQR(Profil profil, Produit produit, Date date) {
		QuantiteReelle qr = manager_.createQuery("select q from QuantiteReelle q where "
				+ "reel_profil LIKE :profil AND "
				+ "reel_produit LIKE :prod AND "
				+ "date LIKE :d ", QuantiteReelle.class)
				.setParameter("profil", profil)
				.setParameter("prod", produit)
				.setParameter("d", date)
				.getSingleResult();
		
		return qr;
	}

	public QuantiteReelle recuperationPrecQR(Profil profil, Produit produit, Date date) {
		QuantiteReelle qr = manager_.createQuery("select q from QuantiteReelle q where "
				+ "reel_profil LIKE :profil AND "
				+ "reel_produit LIKE :prod AND "
				+ "date < :d " 
				+ "ORDER BY date DESC LIMIT 1", QuantiteReelle.class)
				.setParameter("profil", profil)
				.setParameter("prod", produit)
				.setParameter("d", date)
				.setMaxResults(1)
				.getSingleResult();
		
		return qr;
	}
	
	public boolean recuperationPrecQRBool(String profil, String produit, java.util.Date date) {
		//tx_.begin();
		RecuperationDonnees rd = new RecuperationDonnees(main_);
		Profil monProfil = rd.recuperationProfil(profil);
		System.out.println("Récupère le produit en fonction du String passé en paramètre");
		Produit monProduit = rd.recuperationProduitComp(produit);
		boolean qr = manager_.createQuery("select q.rupture_stock from QuantiteReelle q where "
				+ "reel_profil LIKE :profil AND "
				+ "reel_produit LIKE :prod AND "
				+ "date = :d ", Boolean.class)
				.setParameter("profil", monProfil)
				.setParameter("prod", monProduit)
				.setParameter("d", date)
				.getSingleResult();
		
		return qr;
	}
}
