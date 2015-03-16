package fr.galettedebroons.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.galettedebroons.domain.Client;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.view.InterfaceGenerale;

public class salesForecastingSoftwareTest {

	private EntityManager manager_;

	public salesForecastingSoftwareTest(EntityManager manager) {
		this.manager_ = manager;
	}

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("maj"); //createAndDrop ou maj
		EntityManager manager = factory.createEntityManager();
		salesForecastingSoftwareTest test = new salesForecastingSoftwareTest(manager);
		/*
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		// TODO create entity
		List<Profil> lToto = new ArrayList();
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
		
		InterfaceGenerale IHM = new InterfaceGenerale();
		IHM.setLocationRelativeTo(null);
		IHM.setVisible(true);
		
		/*
		RecuperationDonnees rd = new RecuperationDonnees();
		rd.recuperationCodeClient();
		*/
		
		System.out.println(".. done");
	}

}
