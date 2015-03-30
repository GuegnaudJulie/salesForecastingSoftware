package fr.galettedebroons.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.model.RangerDonneeTemporaire;
import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.model.TraitementDonneesTemporaire;
import fr.galettedebroons.model.selectBase.RecupTemporaire;
import fr.galettedebroons.test.Main;

public class VueGlobalNvProduit {
	static List<Object[]> results;
	
	static JPanel panelGeneral;
	static JPanel panelGlobal;
	static JPanel panelBouton;
	static JScrollPane scrollPane;
	static JFrame fenetre;
	static JComboBox[] comboGamme;
	static List<NouveauProduit> listnvproduit;
	
	static String code_produit;
	static String nature_produit;
	static String nom_produit;
	static int qte_produit;
	static String presentation_produit;
	static String gamme_produit;
	static String textCombo;
	static Main main_;
	static PanelEdition panel_;
	private static TraitementDonneesTemporaire ClasseTraitement_;
	static List<String> produits;
	
	/**
	 * constructeur appel methode dynamique creation panel nouveau client
	 */
	public VueGlobalNvProduit(Main main, PanelEdition panel, TraitementDonneesTemporaire tdt){
		main_ = main; //Contient notre EntityManager => plus besoin de faire : new Main(manager_) et de créer le manager
		panel_ = panel;
		ClasseTraitement_ = tdt;
		initialisationProduits();
	}
	
	/**
	 * initialisation dynamique des nouveaux clients dans la base temporaire
	 */
	private static void initialisationProduits() {
		panelGeneral = new JPanel();
		panelGlobal = new JPanel();
		panelBouton = new JPanel();
		fenetre = new JFrame();
		
		fenetre.addWindowListener( new WindowAdapter()
        {
        	public void windowClosing (WindowEvent e)
        	{
        		RangerDonneeTemporaire tdt = new RangerDonneeTemporaire(main_);
        		tdt.vidage();
        	}
        });
		fenetre.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
		
		listnvproduit = new ArrayList<NouveauProduit>();
		fenetre = new JFrame();
		fenetre.setSize(700,500);
		
		RecuperationDonnees rd = new RecuperationDonnees(main_);
		RecupTemporaire rt = new RecupTemporaire(main_);
		
    	// recupere les produits dans la table temporaire
		produits = null;
		int nbNewProd = 0;
		if (panel_ == null){
			produits = rt.recuperationProduitTemp();
			nbNewProd = produits.size();
		}else
			nbNewProd = 1;
		
		// recupere les gammes existantes pour JcomboBox
		String[] gamme = rd.recuperationGamme();
    	
    	comboGamme = new JComboBox[nbNewProd];
    	int indice = 0;
    	if (panel_ == null){
    		
    		GridBagLayout gbl = new GridBagLayout();
    		panelGeneral.setLayout(gbl);
    		GridBagConstraints gbc = new GridBagConstraints();
    		
			for(String prod : produits){
	    		JComboBox jb = new JComboBox(gamme);
	    		comboGamme[indice] = jb;
	    		//panelGeneral.setLayout(new GridLayout(nbNewProd*2,0));
	    		
	    		NouveauProduit np = new NouveauProduit(main_, prod.toString(), jb);
	    		
	    		gbc.gridx = 0;
	    		gbc.gridy = indice;
	    		gbc.fill = gbc.BOTH;
	    		gbc.weightx = 10;
	    		gbc.weighty = 1;
	    		gbc.insets = new Insets(0, 0, 1, 0);
	    		gbc.ipady = gbc.anchor = GridBagConstraints.CENTER;
	    		
	    		panelGeneral.add(np, gbc);
	    		listnvproduit.add(np);
	    		indice ++;
			}
    	}
    	else{
			JComboBox jb = new JComboBox(gamme);
			panelGeneral.setLayout(new GridLayout(nbNewProd*2,0));
			NouveauProduit np = new NouveauProduit(main_, null, jb);
			panelGeneral.add(np);
			listnvproduit.add(np);
		}
    	
    	scrollPane = new JScrollPane(panelGeneral);
    	
    	panelGlobal.setLayout(new BorderLayout());
		panelGlobal.add(scrollPane, BorderLayout.CENTER);
		//fenetre.add(panelGeneral);
		
		JButton boutonEnregistrer = new JButton();
		boutonEnregistrer.setText("Enregistrer");
		panelBouton.add(boutonEnregistrer);
		boutonEnregistrer.addActionListener(new java.awt.event.ActionListener() {
	           public void actionPerformed(java.awt.event.ActionEvent evt) {
	               enregistrercliActionPerformed(evt);
	           }
	    });
		//this.getContentPane().add(new JButton("CENTER"), BorderLayout.CENTER);
		panelGlobal.add(panelBouton, BorderLayout.SOUTH);
		//fenetre.add(panelGlobal);
		
		fenetre.setContentPane(panelGlobal);
		fenetre.setLocationRelativeTo(null);
		
		//fenetre.add(panelBouton);
		fenetre.setVisible(true);
    	
	}
	
	/**
	 * recupere et enregistre les informations des produits 
	 * @param evt
	 */
	private static void enregistrercliActionPerformed(ActionEvent evt) {
		JViewport viewport = new JViewport();
		
		for (NouveauProduit listprod : listnvproduit){
			// reinitialisation des parametre de creation de produit
			code_produit = "";
			nature_produit = "";
			nom_produit = "";
			qte_produit = 0;
			presentation_produit = "";
			gamme_produit = "";
			textCombo = "";
			
			// qté/lot
			//System.out.println(((JLabel) (listprod.getComponent(0))).getText());
			//qte_produit = Integer.parseInt(((JTextField) listprod.getComponent(1)).getText());
			
			String s = ((JTextField) listprod.getComponent(1)).getText();
			try{
				qte_produit = Integer.parseInt(s);
			}catch(Exception e){
				System.out.println("la quantité n'est pas bonne");
				qte_produit = 0;
			}
			
			// nature produit
			nature_produit = ((JTextField) listprod.getComponent(3)).getText();
			
			// code produit
			code_produit = ((JTextField) listprod.getComponent(5)).getText();
			
			// nom produit
			nom_produit = ((JTextField) listprod.getComponent(7)).getText();
			
			// label présentation produit
			viewport = ((JScrollPane)listprod.getComponent(10)).getViewport();
			presentation_produit = ((JTextArea) viewport.getComponent(0)).getText();
			
			//gamme label
			gamme_produit = ((JLabel) listprod.getComponent(9)).getText();
			
			// gamme
			textCombo = (String) listprod.getCombo().getSelectedItem();
			String[] t = textCombo.split(" ");
			
			//Gamme gamme = new Gamme(t[0], Integer.parseInt(t[1]), null, null);
			Gamme gamme = new Gamme(t[0], 2, null, null);
			
			// ajoute en base les elements recuperes
			
			Produit temp;
			try{
				main_.getTransaction().begin();
				temp = new Produit(code_produit, nature_produit, nom_produit, presentation_produit, gamme, qte_produit);
				main_.getManager().persist(temp);
				main_.getTransaction().commit();
				
				fenetre.setVisible(false);
				
			} catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		if (panel_ != null)
			panel_.terminerAjoutProduit();
		
		if (ClasseTraitement_ != null)
			ClasseTraitement_.insertionDonneeFin();
		
	}

}
