package fr.galettedebroons.forecating;

import javax.persistence.EntityManager;
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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
		EntityManager manager = factory.createEntityManager();
		salesForecastingSoftwareTest test = new salesForecastingSoftwareTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		*/

		new FormulaireGalette().setVisible(true);
		
		// TODO create entity

		// TODO persist entity

		// TODO run request
		
		//tx.commit();
		
		System.out.println(".. done");
	}

}
