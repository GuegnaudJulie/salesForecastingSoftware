package fr.galettedebroons.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.galettedebroons.view.InterfaceGenerale;

public class Main {
	
	private EntityManager manager_;
	private EntityTransaction tx_;
	
	public Main(EntityManager manager) {
		manager_ = manager;
	}
	
	public EntityManager getManager(){
		return manager_;
	}
	
	public EntityTransaction getTransaction(){
		return this.tx_;
	}
	
	public void setManager(EntityManager manager){
		manager_ = manager;
	}
	
	public void setTransaction(EntityTransaction tx){
		tx_ = tx;
	}
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("majAnteros"); // majAnteros, createAnteros, dropAnteros, createAndDrop ...
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		
		Main main = new Main(manager);
		main.setTransaction(tx);

		// TODO create entity
		/*List<Profil> lToto = new ArrayList();
		List<Profil> lTiti = new ArrayList();
		
        Profil profilToto = new Profil();
		profilToto.setCode_client("Toto");
		
		Profil profilTiti = new Profil();
		profilTiti.setCode_client("Titi");
		
		Client client1 = new Client();
		client1.setEnseigne_client("test de toto");
		client1.setClient_profil(lToto);
        client1.addProfil(profilToto);
        
        Client client2 = new Client();
		client2.setEnseigne_client("test de titi");
		client2.setClient_profil(lTiti);
        client2.addProfil(profilTiti);
        
        profilToto.setClient_profil(client1);
        profilTiti.setClient_profil(client2);
        
		// TODO persist entity
        manager.persist(client1);
        manager.persist(client2);
        
		// TODO run request
		
		tx.commit();
		*/
		
		InterfaceGenerale IHM = new InterfaceGenerale(main);
		IHM.setLocationRelativeTo(null);
		IHM.setVisible(true);
		
		System.out.println(".. done");
	}

}
