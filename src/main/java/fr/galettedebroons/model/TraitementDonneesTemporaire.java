package fr.galettedebroons.model;

import javax.swing.JOptionPane;

import fr.galettedebroons.test.Main;
import fr.galettedebroons.view.PanelEdition;
import fr.galettedebroons.view.VueGlobalNvClient;
import fr.galettedebroons.view.VueGlobalNvProduit;

public class TraitementDonneesTemporaire {

	private Main main_;
	private PanelEdition panel_;
	private VueGlobalNvClient fenetreClient;
	private VueGlobalNvProduit fenetreProduit;
	
	public TraitementDonneesTemporaire(Main main, PanelEdition panel){
		main_ = main;
		panel_ = panel;
	}
	
	/*
	 * Frame VueGlobaleNvClient
	 * Attente de fin de traitement
	 * Frame VueGlobaleNvClient
	 * Attente de fin de traitement
	 * Insertion dans livraisons
	 */
	public void insertionDonnee(){

		fenetreClient = new VueGlobalNvClient(this);
		
		/* DANS VueGlobalNvClient
		
		private TraitementDonneesTemporaire ClasseTraitement_;
		
		private static void enregistrercliActionPerformed(ActionEvent evt) {
			//Ajout Ã  la fin
			ClasseTraitement_.insertionProduit();
		}
		*/
	}
	
	public void insertionProduit(){
		//fenetreProduit = new VueGlobalNvProduit(this);
		
		/* DANS VueGlobalNvProduit
		
		private TraitementDonneesTemporaire ClasseTraitement_;
		
		private static void enregistrercliActionPerformed(ActionEvent evt) {
			//Ajout Ã  la fin
			ClasseTraitement_.insertionDonneeFin();
		}
		*/
	}
	
	public void insertionDonneeFin(){
		new RemplissageLivraison(main_);
		RangerDonneeTemporaire rdt = new RangerDonneeTemporaire(main_);
		JOptionPane.showMessageDialog(panel_, "Les livraisons ont Ã©tÃ© ajoutÃ©es");
		rdt.vidage();
	}
}
