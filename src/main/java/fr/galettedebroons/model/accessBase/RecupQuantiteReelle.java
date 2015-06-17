package fr.galettedebroons.model.accessBase;

import java.sql.Date;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.QuantiteReelle;
import fr.galettedebroons.main.Main;

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
	
	public QuantiteReelle recuperationPrecQR(Profil profil, String code_prod, Date date) {
		RecupProduit rp = new RecupProduit(main_);
		Produit produit = rp.recupProduit(code_prod);
		
		QuantiteReelle qr = recuperationPrecQR(profil, produit, date);
		
		return qr;
	}
	
	public boolean recuperationPrecQRBool(String profil, String produit, java.util.Date date) {
		RecupProduit rp = new RecupProduit(main_);
		RecupClientProfil rcp = new RecupClientProfil(main_);
		
		Profil monProfil = rcp.recupProfil(profil);
		Produit monProduit = rp.recuperationProduitComp(produit);
		
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
