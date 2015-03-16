package fr.galettedebroons.test;

import fr.galettedebroons.controller.RemplissageLivraison;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class salesForecastingSoftwareTest {

	private EntityManager manager_;

	public salesForecastingSoftwareTest(EntityManager manager) {
		this.manager_ = manager;
	}

	public static void main(String[] args) {
		
		/*EntityManagerFactory factory = Persistence.createEntityManagerFactory("create");
		EntityManager manager = factory.createEntityManager();
		salesForecastingSoftwareTest test = new salesForecastingSoftwareTest(manager);
		
	
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		
		new FormulaireGalette().setVisible(true);*/
                
               //RemplissageLivraison rl=new RemplissageLivraison();
                //rl.remplissage();
		
		// TODO create entity

		// TODO persist entity

		// TODO run request
		
		//tx.commit();
		
		System.out.println(".. done");
	}

}
