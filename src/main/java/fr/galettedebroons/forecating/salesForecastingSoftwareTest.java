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

		new FormulaireGalette().setVisible(true);
		
		// TODO create entity

		// TODO persist entity

		// TODO run request
		
		//tx.commit();
		
		System.out.println(".. done");
	}

}
