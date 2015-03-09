package fr.galettedebroons.controller;

public class VerificationDonnee {

	public VerificationDonnee(){}
	
	/**
	 * vérification de la presence du client et du produit dans la base
	 * 
	 * @param donnees
	 * @return 0 la donnée est présente dans la base
	 * @return -1 le client n'exite pas
	 * @return -2 le produit n'existe pas
	 * @return -3 le client et le produit n'existe pas
	 */
	public int present(String[] donnees){
		return -3;
	}
}
