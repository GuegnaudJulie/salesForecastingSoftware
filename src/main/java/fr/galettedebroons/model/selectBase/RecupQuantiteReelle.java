package fr.galettedebroons.model.selectBase;

import java.sql.Date;
import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.QuantiteReelle;
import fr.galettedebroons.main.Main;

public class RecupQuantiteReelle {

	public EntityManager manager_;
	
	public RecupQuantiteReelle(Main main){
		manager_ = main.getManager();
	}
	
	public QuantiteReelle recuperationQR(Profil profil, Produit produit, Date date1, Date date2){
		QuantiteReelle qr = manager_.createQuery("select q from QuantiteReelle where "
				+ "reel_profil LIKE :profil AND "
				+ "reel_produit LIKE :prod AND "
				+ "date >= :d1 AND "
				+ "date <= :d2", QuantiteReelle.class)
				.setParameter("profil", profil)
				.setParameter("prod", produit)
				.setParameter("d1", date1)
				.setParameter("d2", date2)
				.getSingleResult();
		
		return qr;
	}
}
