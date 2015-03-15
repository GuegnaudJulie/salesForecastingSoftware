package fr.galettedebroons.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.galettedebroons.view.InterfaceGenerale;

public class salesForecastingSoftwareTest {

	private EntityManager manager_;

	public salesForecastingSoftwareTest(EntityManager manager) {
		this.manager_ = manager;
	}

	public static void main(String[] args) {
		/*
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("maj");
		EntityManager manager = factory.createEntityManager();
		salesForecastingSoftwareTest test = new salesForecastingSoftwareTest(manager);
		*/
		
		/*
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		*/
		InterfaceGenerale IHM = new InterfaceGenerale();
		IHM.setLocationRelativeTo(null);
		IHM.setVisible(true);
		
		// TODO create entity

		// TODO persist entity

		// TODO run request
		
		//tx.commit();
		
		System.out.println(".. done");
	}

}
