package fr.galettedebroons.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hsqldb.lib.Iterator;

import fr.galettedebroons.domain.Client;
import fr.galettedebroons.domain.Profil;

public class RecuperationDonnees {
	
	private EntityManager manager_;
	private EntityManagerFactory factory_;
	
	public RecuperationDonnees(){
		factory_ = Persistence.createEntityManagerFactory("maj");
		manager_ = factory_.createEntityManager();
	}
	
	public void recuperationCodeClient(){
		List<Client> profil = manager_.createQuery("select c from Client c").getResultList();
		
		System.out.println("le code client est " + profil.toString());
		//return profil;
	}
}
