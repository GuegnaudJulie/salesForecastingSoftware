package fr.galettedebroons.model.accessBase;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.main.Main;

public class RecupClientProfil {

	public EntityManager manager_;
	
	public RecupClientProfil(Main main){
		manager_ = main.getManager();
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
	
	public boolean recuperationProfil(String code_client){
		boolean present;
		
		try{
			manager_.createQuery("select p from Profil p where p.code_client LIKE :code", Profil.class)
					.setParameter("code", code_client)
					.getSingleResult();
			present = true;
		} catch (Exception e){
			present = false;
		}
		
		return present;
	}
	
	public List<Profil> recuperationToutProfil(){
		List<Profil> listProfil = manager_.createQuery("select p from Profil p", Profil.class)
					.getResultList();
			
		return listProfil;
	}
	
	public Profil recupProfil(String codeClient){
		Profil profil = manager_.createQuery("select p from Profil p where code_client LIKE :codeClient", Profil.class)
				.setParameter("codeClient", codeClient)
				.getSingleResult();
		return profil;
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
			tableauProfil[i] = p.getCode_client();
			i++;
			
		}
		
		return tableauProfil;
	}

	public List<Object[]> recupuniqueProfil(java.util.Date data, String profil){
		List<Object[]> listePrevision = null;
		
		listePrevision = manager_.createQuery("select p.quantite, p.prevision_profil.code_client, "
					+ "p.prevision_produit.code_produit from Prevision p where p.date LIKE :date "
					+ "and p.prevision_profil.code_client LIKE :profil"
					, Object[].class)
					.setParameter("date", data)
					.setParameter("profil", profil)
					.getResultList();
		
		return listePrevision;
	}
	
}
