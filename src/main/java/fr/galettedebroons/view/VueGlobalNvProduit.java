package fr.galettedebroons.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VueGlobalNvProduit {
	static JPanel panelGeneral = new JPanel();
	static JFrame fenetre = new JFrame();
	
	public static void main(String[] args){
		fenetre.setSize(800,600);
		NouveauProduit np = new NouveauProduit();
		panelGeneral.add(np);
		fenetre.add(panelGeneral);
		fenetre.setVisible(true);
	}

}
