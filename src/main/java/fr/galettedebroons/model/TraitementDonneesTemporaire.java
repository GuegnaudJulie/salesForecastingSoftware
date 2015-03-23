package fr.galettedebroons.model;

import javax.swing.JOptionPane;

import fr.galettedebroons.test.Main;
import fr.galettedebroons.view.PanelEdition;

public class TraitementDonneesTemporaire {

	private Main main_;
	private PanelEdition panel_;
	//private JFrame fenetreClient;
	//private JFrame fenetreProduit;
	
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
		/* DANS THIS
		fenetreClient = new JFrame();
		fenetreClient.setSize(700,500);
		fenetreClient.setLocationRelativeTo(null);
		VueGlobalNvClient creaClients = new VueGlobalNvClient(this);
		JScrollPane pane = new JScrollPane(creaClients);
		fenetreClient.setContentPane(pane);
		fenetreClient.setVisible(true);
		*/
		
		/* DANS VueGlobalNvClient
		private static void enregistrercliActionPerformed(ActionEvent evt) {
			//Ajout à la fin
			closeClient();
		}
		*/
	}
	
	public void closeClient(){
		//fenetreClient.setVisible(false);
		insertionDonneeSuite();
	}
	
	public void insertionDonneeSuite(){
		/* DANS THIS
		fenetreProduit = new JFrame();
		fenetreProduit.setSize(700,500);
		fenetreProduit.setLocationRelativeTo(null);
		VueGlobalNvProduit creaProduits = new VueGlobalNvProduit(this);
		JScrollPane pane = new JScrollPane(creaProduits);
		fenetreProduit.setContentPane(pane);
		fenetreProduit.setVisible(true);
		*/
		
		/* DANS VueGlobalNvProduit
		private static void enregistrercliActionPerformed(ActionEvent evt) {
			//Ajout à la fin
			closeProduit();
		}
		*/
	}
	
	public void closeProduit(){
		//fenetreProduit.setVisible(false);
		insertionDonneeFin();
	}
	
	public void insertionDonneeFin(){
		new RemplissageLivraison(main_);
		RangerDonneeTemporaire rdt = new RangerDonneeTemporaire(main_);
		JOptionPane.showMessageDialog(panel_, "Les livraisons ont été ajoutées");
		rdt.vidage();
	}
}
