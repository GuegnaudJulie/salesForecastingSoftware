package fr.galettedebroons.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.Produit;
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
	
	public String[] recuperationClient(){
		List<String> listClient = manager_.createQuery("select c.enseigne_client " +
				"from Client c ", String.class).getResultList();
		String[] client = new String[listClient.size()];
		int i = 0;
		for (String g : listClient){
			client[i] = g;
			i++;
		}		
		return client;
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
	
	public boolean recuperationLivraison(String bl, Date date, String code_prod){
		boolean present = false;
		List<Livraison> ListLivraison = null;
		Produit produit = null;
		
		List<Produit> listeProduits =  manager_.createQuery("select p from Produit p WHERE p.code_produit LIKE :codeProd", Produit.class)
				.setParameter("codeProd", code_prod)
				.setMaxResults(1)
				.getResultList();
		for (Produit prod : listeProduits)
			produit = prod;

		ListLivraison = manager_.createQuery("select l from Livraison l WHERE " +
				"l.bon_livraison LIKE :bl AND " +
				"l.date_livraison LIKE :date", Livraison.class)
				.setParameter("bl", bl)
				.setParameter("date", date)
				.setMaxResults(1)
				.getResultList();
		
		for (Livraison l : ListLivraison)
		{
			for (Produit p : l.getLivraison_produit())
				System.out.println("Le code du produit est : " + p.getCode_produit());
			
			if (l.getLivraison_produit().contains(produit))
				present = true;
		}
		
		return present;
	}
	
	public Gamme recuperationGammeProduit(String code_produit){
		Gamme gamme = manager_.createQuery("select p.produit_gamme from Produit p where code_produit LIKE :cp", Gamme.class).setParameter("cp", code_produit).getSingleResult();	
		return gamme;
	}
}
