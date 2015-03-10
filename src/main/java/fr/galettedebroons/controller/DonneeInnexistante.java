package fr.galettedebroons.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.galettedebroons.domain.Temporaire;

/**
 * @author	Julie Guegnaud
 * @version 1.0
 * @since   2015-03-06
 */
public class DonneeInnexistante {
	
	private EntityManager manager_;
	private EntityManagerFactory factory_;
	
	public DonneeInnexistante(){
		factory_ = Persistence.createEntityManagerFactory("maj");
		manager_ = factory_.createEntityManager();
	}

	public void ajout(String[] donnee, String code) {
		// nom de la table temp : donneeFichier
		EntityTransaction tx = manager_.getTransaction();
		tx.begin();
		
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	    	java.util.Date d = format.parse(donnee[1]);
			date = new Date(d.getTime());
		} catch (Exception e) {e.printStackTrace();}
	    
	    int indice = donnee[5].indexOf(",");
	    donnee[5] = donnee[5].substring(0, indice);
	    
	    Temporaire temp = new Temporaire(donnee[0], date, donnee[2], donnee[3], donnee[4], Integer.parseInt(donnee[5]), code);
	    
		manager_.persist(temp);
		
		tx.commit();
	}
	
	public void suppression(){
		
	}
	
}
