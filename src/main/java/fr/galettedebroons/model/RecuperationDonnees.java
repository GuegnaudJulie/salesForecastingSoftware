package fr.galettedebroons.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RecuperationDonnees {
	
	private EntityManager manager_;
	private EntityManagerFactory factory_;
	
	public RecuperationDonnees(){
		factory_ = Persistence.createEntityManagerFactory("majAnteros");
		manager_ = factory_.createEntityManager();
	}
	
	/**
	 * Recuperation du code d'un client et de son enseigne
	 * @return [0] => code du client
	 * @return [1] => l'enseigne du client
	 */
	public List<Object[]> recuperationCodeClient(){
		List<Object[]> profil = manager_.createQuery("select p.code_client, c.enseigne_client " +
				"from Client c, Profil p " +
				"where p.client_profil = c", Object[].class).getResultList();
		
		return profil;
	}
	
	public List<Object[]> recuperationCodeProduit(){
		List<Object[]> produit = manager_.createQuery("select p.code_produit, p.nom_produit " +
				"from Produit p ", Object[].class).getResultList();
		return produit;
	}
}
