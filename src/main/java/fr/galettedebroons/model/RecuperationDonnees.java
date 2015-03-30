package fr.galettedebroons.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.MargeLivraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
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
	
	public String[] recuperationProfil(){
		List<String> listClient = manager_.createQuery("select p.code_client " +
				"from Profil p ", String.class).getResultList();
		String[] client = new String[listClient.size()];
		int i = 0;
		for (String g : listClient){
			client[i] = g;
			i++;
		}		
		return client;
	}
	
	public Profil recupProfil(String codeClient){
		Profil profil = manager_.createQuery("select p from Profil p where code_client LIKE :codeClient", Profil.class).setParameter("codeClient", codeClient).getSingleResult();
		return profil;
	}
	
	public String[] recuperationProduit(){
		List<String> listProduit = manager_.createQuery("select p.code_produit " +
				"from Produit p ", String.class).getResultList();
		String[] produit = new String[listProduit.size()];
		int i = 0;
		for (String g : listProduit){
			produit[i] = g;
			i++;
		}		
		return produit;
	}
	
	public Produit recupProduit(String code_produit) {
		Produit prod =  manager_.createQuery("select p from Produit p WHERE " +
				"code_produit LIKE :produit", Produit.class)
				.setParameter("produit", code_produit)
				.getSingleResult();
		
		return prod;
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
	
	public Produit recuperationProduitComp(String codeProd){
		Produit produit = manager_.createQuery("select p from Produit p where code_produit LIKE :cp ", Produit.class)
				.setParameter("cp", codeProd)
				.getSingleResult();
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
	
	public Profil recuperationProfil(String code_client){
		Profil profil = manager_.createQuery("select p from Profil p where code_client LIKE :cc ", Profil.class)
				.setParameter("cc", code_client)
				.getSingleResult();
		return profil;
	}
	
	public String[] recupJoursLivraison(Profil profil){
		String[] joursLivraison = manager_.createQuery("select t.jour_tournee from Tournee t where profil_tournee LIKE :profil", String[].class)
				.setParameter("profil", profil)
				.getSingleResult();
		
		return joursLivraison;
	}
	
	public Gamme recuperationGammeProduit(String code_produit){
		Gamme gamme = manager_.createQuery("select p.produit_gamme from Produit p where code_produit LIKE :cp", Gamme.class).setParameter("cp", code_produit).getSingleResult();	
		return gamme;
	}
	
	public double recuperationTxReprise(String code_prod, String code_client){
		Produit np = recuperationProduitComp(code_prod);
		System.out.println("mon produit :  " +np);
		Profil pp = recuperationProfil(code_client);
		System.out.println("mon profil ; " +pp);
		
		double res = 0.0;
		
		//Query query = (Query) manager_.createQuery("select ml.taux_reprise from MargeLivraison ml " +
		//		"where ml.marge_produit_code_produit = '" +code_prod + "' and ml.marge_profil_code_client = '" +code_client +"'", Double.class);
		res = manager_.createQuery("select m.taux_reprise from MargeLivraison m " +
				"WHERE m.marge_produit LIKE :paramcode_prod AND m.marge_profil LIKE :paramcode_client ", Double.class)
		.setParameter("paramcode_prod", np)
		.setParameter("paramcode_client", pp).getSingleResult();
		
		System.out.println("mon res : " +res);
		
		return res;
	}
	
	public String[] recuperationProfilTournee(String tournee){
		// tableau de String profil
		String[] tableauProfil = null;
		List<Collection> listProfil = manager_.createQuery("select t.profil_tournee "
				+ "FROM Tournee t WHERE t.nom LIKE :tournee", Collection.class)
				.setParameter("tournee", tournee)
				.getResultList();
		tableauProfil = new String[listProfil.size()];
		// chercher comment gérer une liste de collectiooooooooooon !!!!!
		Iterator it = listProfil.iterator();
		int i=0;
		while(it.hasNext()){
			//System.out.println("coucouuuu " +it.next());
			
			Profil p = (Profil) it.next();
			System.out.println("mon profil : " +p.getCode_client() + " " +p.getGamme_profil());
			tableauProfil[i] = p.getCode_client();
			i++;
			
		}
		//String[] profil = new String[listProfil.size()];
		//int i = 0;
		/*for (Profil g : listProfil){
			profil[i] = g.getCode_client();
			i++;
		}		*/
		return tableauProfil;
	}
	
}
