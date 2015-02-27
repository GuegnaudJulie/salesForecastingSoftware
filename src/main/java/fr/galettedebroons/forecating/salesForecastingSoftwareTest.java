package fr.galettedebroons.forecating;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import fr.galettedebroons.model.LectureFichier;
import fr.galettedebroons.view.FormulaireGalette;

public class salesForecastingSoftwareTest {

	private EntityManager manager_;

	public salesForecastingSoftwareTest(EntityManager manager) {
		this.manager_ = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		salesForecastingSoftwareTest test = new salesForecastingSoftwareTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		*/
		
		/*
		 ****** Plan pour la mise en place des données dans la base *******
		 *  
		 *  
		 *  Lecture du fichier ligne par ligne
		 ***  Vérification de l'existance sur la base de la données (profil/produit/livraison à une date T)
		 ***** Si exite déjà => pas d'effet ou question "Voulez-vous modifier la donnée... OUI/NON ?"
		 ***** Sinon => Vérification client puis produit puis livraison
		 ******* Si une des données précédentes n'existe pas on la créer dans la base (formulaire à envoyer graphiquement)
		 ******* On enregistre la livraison dans la base
		 *** On passe à la ligne suivante
		 *
		 */
		
		
		// Run !!! Test !!! =p (by juju)

		new FormulaireGalette().setVisible(true);
				
		

		// TODO create entity

		// TODO persist entity

		// TODO run request
		
		//tx.commit();
		
		System.out.println(".. done");
	}

}
