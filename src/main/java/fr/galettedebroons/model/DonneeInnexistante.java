package fr.galettedebroons.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 * @author	Julie Guegnaud
 * @version 1.0
 * @since   2015-03-06
 */
public class DonneeInnexistante {
	
	private EntityManagerFactory factory_;
	private EntityManager manager_;
	
	public DonneeInnexistante(){
		factory_ = Persistence.createEntityManagerFactory("Crea&Sup");
		manager_ = factory_.createEntityManager();
	}

	public void creaTemp(){
		EntityTransaction tx = manager_.getTransaction();
		tx.begin();
		
	    String createTemp =
	        "create temporary table if not exists donneeFichier " +
	        		"(bon_livraison varchar(15) NOT NULL, " +
	        		"date DATE NOT NULL" + 
	        		"code_profil varchar(15) NOT NULL" +
	        		"nom_client varchar(40) NOT NULL" +
	        		"code_produit varchar(40) NOT NULL" +
	        		"quantite int NOT NULL" +
	        		"code_erreur Varchar(2) NOT NULL" +
	        		");" ;
	 
	    manager_.createQuery(createTemp);
	    
	    tx.commit();
	}
	
	public void ajout(String[] donnee, String code) {
		// nom de la table temp : donneeFichier
		EntityTransaction tx = manager_.getTransaction();
		tx.begin();
		
		String sql = "INSERT INTO donneeFichier VALUES(";
		for(int i = 0; i<donnee.length ; i++){
			sql += donnee[i] + ", ";
		}
		sql += code + ");" ;
		
		System.out.println("le profil " + donnee[2] + " ou le produit " + donnee[4] + "n'existe pas (" + code + ")");
		
		manager_.createQuery(sql);
		
		tx.commit();
	}
	
}
