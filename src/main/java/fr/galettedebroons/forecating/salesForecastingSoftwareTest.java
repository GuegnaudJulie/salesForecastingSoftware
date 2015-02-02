package fr.galettedebroons.forecating;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class salesForecastingSoftwareTest {

	private EntityManager manager;

	public salesForecastingSoftwareTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		salesForecastingSoftwareTest test = new salesForecastingSoftwareTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		// TODO create entity

		// TODO persist entity

		tx.commit();

		// TODO run request

		System.out.println(".. done");
	}

}
