package fr.galettedebroons.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.galettedebroons.jri.Rcall;
import fr.galettedebroons.model.RangerDonneeTemporaire;
import fr.galettedebroons.model.EnchainementInsertionDonnees;
import fr.galettedebroons.view.InterfaceGenerale;

public class Main {
	
	private EntityManager manager_;
	private EntityTransaction tx_;
	private String[] args_;
	private EntityManagerFactory factory_;
	
	public Main(EntityManagerFactory factory, EntityManager manager, EntityTransaction tx, String[] args) {
		factory_ = factory;
		manager_ = manager;
		tx_ = tx;
		args_ = args;
	}
	
	public EntityManagerFactory getFactory() {
		return factory_;
	}

	public EntityManager getManager(){
		return manager_;
	}
	
	public EntityTransaction getTransaction(){
		return this.tx_;
	}

	public String[] getArgs() {
		return args_;
	}

	public void setFactory(EntityManagerFactory factory_) {
		this.factory_ = factory_;
	}
	
	public void setManager(EntityManager manager){
		manager_ = manager;
	}
	
	public void setTransaction(EntityTransaction tx){
		tx_ = tx;
	}
	
	public void setArgs(String[] args_) {
		this.args_ = args_;
	}
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("connexion"); // MAJ, createAndDrop
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		
		Main main = new Main(factory, manager, tx, args);
		
		RangerDonneeTemporaire rdt = new RangerDonneeTemporaire(main);
		//Rcall.main(args); //A été remis dans EnchaînementDonnees
		rdt.vidage();
		
		InterfaceGenerale IHM = new InterfaceGenerale(main);
		IHM.setLocationRelativeTo(null);
		IHM.setVisible(true);
		
		System.out.println(".. done");
	}

}
