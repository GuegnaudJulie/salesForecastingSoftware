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
	
	public void insertionDonnee(){
		fenetreClient = new VueGlobalNvClient(main_, null, this);
	}
	
	public void insertionProduit(){
		fenetreProduit = new VueGlobalNvProduit(main_, null, this);
	}
	
	public void insertionDonneeFin(){
		new RemplissageLivraison(main_);
		RangerDonneeTemporaire rdt = new RangerDonneeTemporaire(main_);
		JOptionPane.showMessageDialog(panel_, "Les livraisons ont été ajoutées");
		rdt.vidage();
	}
}
