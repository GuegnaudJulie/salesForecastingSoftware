package fr.galettedebroons.controller;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.Temporaire;
import java.util.ArrayList;
import javax.persistence.Query;

/**
 * Classe de verification des donnees et de leur ajout dans la table temporaire
 * Elle realise aussi le vidage de la table Temporaire
 * 
 * @author	Julie Guegnaud
 * @version 1.0
 * @since   2015-03-13
 */
public class RangerDonneeFichier {
	private EntityManager manager_;
	private EntityManagerFactory factory_;
	
	public RangerDonneeFichier(){
		factory_ = Persistence.createEntityManagerFactory("maj");
		manager_ = factory_.createEntityManager();
	}
	
	/**
	 * verification de la presence du client et du produit dans la base
	 * 
	 * @param donnees
	 * @return 0 la donnee est presente dans la base
	 * @return 1 le client n'exite pas
	 * @return 2 le produit n'existe pas
	 * @return 3 le client et le produit n'existe pas
	 * @return -1 Il manque des informations
	 */
	public int verification(String[] donnees){
		int code_retour = 0;
		boolean c_present = true;
		boolean p_present = true;
		
		//Toutes les informations sont présentes
		for (int i = 0; i < donnees.length; i++){
			if (donnees[i] == "")
				code_retour = -1;
		}
		
		//Le client et le produit existe
		if (code_retour != -1)
		{
			@SuppressWarnings("unchecked")
			List<Profil> profil = manager_.createQuery("select c from Profil c where c.code_client LIKE :codeClient ")
					.setParameter("codeClient", donnees[2])
					.setMaxResults(1).getResultList();
			if (profil.size() == 0)
				c_present = false;
			
			@SuppressWarnings("unchecked")
			List<Produit> produit = manager_.createQuery("select p from Produit p where p.code_produit LIKE :codeProduit ")
					.setParameter("codeProduit", donnees[4])
					.setMaxResults(1).getResultList();
			if (produit.size() == 0)
				p_present = false;
			
			if (!(c_present && p_present))
				code_retour = 3;
			else if (!p_present)
				code_retour = 2;
			else if (!c_present)
				code_retour = 1;
		}
		
		return code_retour;
	}
	
	/**
	 * Ajout de la ligne du fichier dans la table Temporaire
	 * 
	 * @param donnee donnee du fichier
	 * @param code code d'erreur donnee par la fonction present()
	 */
	public void ajout(String[] donnee, String code) {
		EntityTransaction tx = manager_.getTransaction();
		tx.begin();
		
		Date date = null;
		int indice;
		String dateStr;
		SimpleDateFormat format = null;
		DateFormatSymbols dfsFR = new DateFormatSymbols(Locale.FRENCH);
		
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
		}
		else if (donnee[1].contains("-")) {
			format = new SimpleDateFormat("dd-MMMM-yyyy",dfsFR);
		}
		
		try {
			java.util.Date d = format.parse(donnee[1]);
			date = new Date(d.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (donnee[5].contains(",")){
			indice = donnee[5].indexOf(",");
			donnee[5] = donnee[5].substring(0, indice);
		}
		else if (donnee[5].contains(".")){
			indice = donnee[5].indexOf(".");
			donnee[5] = donnee[5].substring(0, indice);
		}
		int qtite = Integer.parseInt(donnee[5]);
		
		Temporaire temp;
		if (qtite>0)
			temp = new Temporaire(donnee[0], date, donnee[2], donnee[3], donnee[4], qtite, 0, code);
		else
			temp = new Temporaire(donnee[0], date, donnee[2], donnee[3], donnee[4], 0, qtite, code);
		
		manager_.persist(temp);
               
		tx.commit();
	}
	
	
         
        
        /**
	 * Fonction de vidage de la table Temporaire
	 */
	public void vidage(){
		String vidage = "delete temp from Temporaire temp";
		manager_.createQuery(vidage);
	}
}
