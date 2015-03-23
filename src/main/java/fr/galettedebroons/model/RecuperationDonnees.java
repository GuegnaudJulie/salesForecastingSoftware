package fr.galettedebroons.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.galettedebroons.domain.Temporaire;
import fr.galettedebroons.domain.Tournee;
import fr.galettedebroons.test.Main;

public class RecuperationDonnees {
	
	private EntityManager manager_;
	
	public RecuperationDonnees(Main main){
		manager_ = main.getManager();
		System.out.println(manager_.toString());
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
	
	public List<String> recuperationProduitTemp(){
		List<String> produit = manager_.createQuery("select distinct t.code_produit " +
				"from Temporaire t where code_erreur = 'CP' or  code_erreur = 'P'", String.class).getResultList();
		return produit;
	}
	
	public List<Object[]> recuperationClientTemp(){
		List<Object[]> produit = manager_.createQuery("select distinct t.nom_client, t.code_profil " +
				"from Temporaire t where code_erreur = 'CP' or  code_erreur = 'P'", Object[].class).getResultList();
		return produit;
	}
	
	public String[] recuperationGamme(){
		List<String> ListGamme = manager_.createQuery("select g.code_gamme " +
				"from Gamme g ", String.class).getResultList();
		
		String[] gamme = new String[ListGamme.size()];
		int i = 0;
		for (String g : ListGamme){
			gamme[i] = g;
			i++;
		}
		
		return gamme;
	}
	
	public List<Object[]> recuperationTourneee(){
		List<Object[]> tournee = manager_.createQuery("select distinct t.id, t.jour_tournee, t.nom" +
				" from Tournee t", Object[].class).getResultList();
		return tournee;
	}
	
	public String[] recuperationTournee(){
		List<String> ListTournee = manager_.createQuery("select t.nom " +
				"from Tournee t ", String.class).getResultList();
		
		String[] tournee = new String[ListTournee.size()];
		int i = 0;
		for (String g : ListTournee){
			tournee[i] = g;
			i++;
		}
		
		return tournee;
	}
}
