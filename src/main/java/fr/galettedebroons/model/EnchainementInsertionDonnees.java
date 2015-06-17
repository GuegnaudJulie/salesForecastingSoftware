package fr.galettedebroons.model;

import javax.swing.JOptionPane;

import fr.galettedebroons.jri.Rcall;
import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.accessBase.RecupTemporaire;
import fr.galettedebroons.view.PanelEdition;
import fr.galettedebroons.view.PanelPrevision;
import fr.galettedebroons.view.VueGlobalNvClient;
import fr.galettedebroons.view.VueGlobalNvProduit;

public class EnchainementInsertionDonnees {

	private Main main_;
	private PanelEdition panel_;
	private PanelPrevision panbelPrev_;
	private VueGlobalNvClient fenetreClient;
	private VueGlobalNvProduit fenetreProduit;
	private RecupTemporaire rt_;
	
	public EnchainementInsertionDonnees(Main main, PanelEdition panel, PanelPrevision panbelPrev){
		main_ = main;
		panel_ = panel;
		panbelPrev_ = panbelPrev;
		rt_ = new RecupTemporaire(main_);
	}
	
	public void insertionDonnee(){
		if (rt_.recuperationClientInexistant())
			fenetreClient = new VueGlobalNvClient(main_, null, panbelPrev_, this);
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
		
		MargeLivraisonCalcul ml = new MargeLivraisonCalcul(main_);
		ml.calculMarge();
		
		RangerDonneeTemporaire rdt = new RangerDonneeTemporaire(main_);
		rdt.vidage();
		
		Rcall.main(main_.getArgs());
		
		JOptionPane.showMessageDialog(panel_, "Les livraisons ont été ajoutées");
	}
}
