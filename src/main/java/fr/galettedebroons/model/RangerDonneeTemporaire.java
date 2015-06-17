package fr.galettedebroons.model;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.galettedebroons.domain.Temporaire;
import fr.galettedebroons.main.Main;

import javax.persistence.Query;

/**
 * Classe de verification des donnees et de leur ajout dans la table temporaire
 * Elle realise aussi le vidage de la table Temporaire
 * 
 * @author	Julie Guegnaud
 * @version 1.0
 * @since   2015-03-13
 */
public class RangerDonneeTemporaire {
	private EntityManager manager_;
	private Main main_;
	
	public RangerDonneeTemporaire(Main main){
		main_ = main;
		manager_ = main.getManager();
	}
	
	/**
	 * Ajout de la ligne du fichier dans la table Temporaire
	 * 
	 * @param donnee donnee du fichier
	 * @param code code d'erreur donnee par la fonction present()
	 */
	public void ajout(String[] donnee, String code) {
		Date date = null;
		int indice;
		String dateStr;
		SimpleDateFormat format = null;
		DateFormatSymbols dfsFR = new DateFormatSymbols(Locale.FRENCH);
		
		/* Tranformation de la date en date SQL */
		Long time = null;
		
		if (donnee[1].contains("/")){
			//Trouver le format de date dd/MM/yy ou dd/MM/yyyy
			indice = donnee[1].indexOf("/");
			dateStr = donnee[1].substring(indice+1, donnee[1].length());
			indice = dateStr.indexOf("/");
			dateStr = dateStr.substring(indice+1, dateStr.length());
			
			if (dateStr.length() == 4)
				format = new SimpleDateFormat("dd/MM/yyyy");
			else if (dateStr.length() == 2)
				format = new SimpleDateFormat("dd/MM/yy");
			
			try { 
				java.util.Date d = format.parse(donnee[1]);
				time = d.getTime();
			} catch (ParseException e) { e.printStackTrace(); }
		}
		else if (donnee[1].contains("-")) {
			format = new SimpleDateFormat("dd-MMMM-yyyy",dfsFR);
			try { 
				java.util.Date d = format.parse(donnee[1]);
				time = d.getTime();
			} catch (ParseException e) { e.printStackTrace(); }
		}
		else if (donnee[1].matches("^[0-9]+$")) {
			time = Long.parseLong(donnee[1]);
		}
		
		date = new Date(time);
		
		/* Transformation de la quantite */ 
		if (donnee[5].contains(",")){
			indice = donnee[5].indexOf(",");
			donnee[5] = donnee[5].substring(0, indice);
		}
		else if (donnee[5].contains(".")){
			indice = donnee[5].indexOf(".");
			donnee[5] = donnee[5].substring(0, indice);
		}
		int qtite = Integer.parseInt(donnee[5]);
		
		// si mon produit est un 1 ou 2 on n'insert pas 
		if(donnee[4].equals("1") || donnee[4].equals("2")){
			System.out.println("On ne doit pas insérer dans notre table temporaire, produit 1 ou 2");
		}else {
			/* Insertion dans la table temporaire */
			main_.getTransaction().begin();
			
			Temporaire temp;
			if (qtite>0)
				temp = new Temporaire(donnee[0], date, donnee[2], donnee[3], donnee[4], qtite, 0, code);
			else
				temp = new Temporaire(donnee[0], date, donnee[2], donnee[3], donnee[4], 0, qtite, code);
			
			manager_.persist(temp);
			main_.getTransaction().commit();
		}
	}
	
	/**
	 * Fonction de vidage de la table Temporaire
	 */
	public void vidage(){
		EntityTransaction tx = manager_.getTransaction();
		tx.begin();
		
		String vidage = "delete from Temporaire";
		Query viderTemp = manager_.createQuery(vidage);
		
		viderTemp.executeUpdate();
		
		tx.commit();
	}
}
