package fr.galettedebroons.model;

import javax.swing.JOptionPane;

import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.selectBase.RecupTemporaire;
import fr.galettedebroons.view.PanelEdition;
import fr.galettedebroons.view.VueGlobalNvClient;
import fr.galettedebroons.view.VueGlobalNvProduit;

public class TraitementDonneesTemporaire {

	private Main main_;
	private PanelEdition panel_;
	private VueGlobalNvClient fenetreClient;
	private VueGlobalNvProduit fenetreProduit;
	private RecupTemporaire rt_;
	
	public TraitementDonneesTemporaire(Main main, PanelEdition panel){
		main_ = main;
		panel_ = panel;
		rt_ = new RecupTemporaire(main_);
	}
	
	public void insertionDonnee(){
		if (rt_.recuperationClientInexistant())
			fenetreClient = new VueGlobalNvClient(main_, null, this);
		else
			insertionProduit();
	}
	
	public void insertionProduit(){
		if (rt_.recuperationProdInexistant())
			fenetreProduit = new VueGlobalNvProduit(main_, null, this);
		else
			insertionDonneeFin();
	}
	
	public void insertionDonneeFin(){
		RemplissageLivraison rl = new RemplissageLivraison(main_);
		rl.remplissage();
		JOptionPane.showMessageDialog(panel_, "Les livraisons ont été ajoutées");
		
		RangerDonneeTemporaire rdt = new RangerDonneeTemporaire(main_);
		rdt.vidage();
	}
}
