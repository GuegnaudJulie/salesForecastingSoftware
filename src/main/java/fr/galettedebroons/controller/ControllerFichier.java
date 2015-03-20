package fr.galettedebroons.controller;

import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.test.Main;

public class ControllerFichier {

	private EntityManager manager_;
	
	public ControllerFichier(Main main){
		manager_ = main.getManager();
	}
	
	/**
	 * verification de la presence du client et du produit dans la base
	 * 
	 * @param donnees
	 * @return 0 la donnee est presente dans la base
	 * @return 1 le client n'exite pas
	 * @return 2 le produit n'existe pas
	 * @return 3 le client et le produit n'existe pas
	 * @return -1 Il manque des informations
	 */
	public int verification(String[] donnees){
		int code_retour = 0;
		boolean c_present = true;
		boolean p_present = true;
		
		//Toutes les informations sont présentes
		for (int i = 0; i < donnees.length; i++){
			if (donnees[i] == "")
				code_retour = -1;
		}
		
		//Le client et le produit existe
		if (code_retour != -1)
		{
			@SuppressWarnings("unchecked")
			List<Profil> profil = manager_.createQuery("select c from Profil c where c.code_client LIKE :codeClient ")
					.setParameter("codeClient", donnees[2])
					.setMaxResults(1).getResultList();
			if (profil.isEmpty())
				c_present = false;
			
			@SuppressWarnings("unchecked")
			List<Produit> produit = manager_.createQuery("select p from Produit p where p.code_produit LIKE :codeProduit ")
					.setParameter("codeProduit", donnees[4])
					.setMaxResults(1).getResultList();
			if (produit.isEmpty())
				p_present = false;
			
			if (!c_present && !p_present)
				code_retour = 3;
			else if (!p_present)
				code_retour = 2;
			else if (!c_present)
				code_retour = 1;
		}
		
		return code_retour;
	}
}
