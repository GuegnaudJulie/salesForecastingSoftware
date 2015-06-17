package fr.galettedebroons.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.MargeLivraison;
import fr.galettedebroons.domain.Prevision;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.QuantiteReelle;
import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.RangerDonneeTemporaire;
import fr.galettedebroons.model.EnchainementInsertionDonnees;
import fr.galettedebroons.model.accessBase.RecupGamme;
import fr.galettedebroons.model.accessBase.RecupTemporaire;

public class VueGlobalNvProduit {
	private List<Object[]> results;
	
	private JPanel panelGeneral;
	private JPanel panelGlobal;
	private JPanel panelBouton;
	private JScrollPane scrollPane;
	private JLabel labelErreur;
	private JFrame fenetre;
	private JComboBox[] comboGamme;
	private List<NouveauProduit> listnvproduit;
	
	private String textCombo;
	private Main main_;
	private PanelEdition panel_;
	private EnchainementInsertionDonnees ClasseTraitement_;
	private RecupGamme rg_;
	private List<String> produits;
	
	/**
	 * constructeur appel methode dynamique creation panel nouveau client
	 */
	public VueGlobalNvProduit(Main main, PanelEdition panel, EnchainementInsertionDonnees tdt){
		main_ = main; //Contient notre EntityManager => plus besoin de faire : new Main(manager_) et de créer le manager
		panel_ = panel;
		ClasseTraitement_ = tdt;
		rg_ = new RecupGamme(main_);
		initialisationProduits();
	}
	
	/**
	 * initialisation dynamique des nouveaux clients dans la base temporaire
	 */
	private void initialisationProduits() {
		labelErreur = new JLabel();
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
		String[] gamme = rg_.recuperationGamme();
    	
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
	    		
	    		NouveauProduit np = new NouveauProduit(main_, prod.toString(), jb, this);
	    		
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
			NouveauProduit np = new NouveauProduit(main_, null, jb, this);
			panelGeneral.add(np);
			listnvproduit.add(np);
		}
    	
    	scrollPane = new JScrollPane(panelGeneral);
    	
    	labelErreur.setPreferredSize(new Dimension(600, 30));
		labelErreur.setForeground(new java.awt.Color(204, 0, 0));
        labelErreur.setText("");
    	
    	panelGlobal.setLayout(new BorderLayout());
    	panelGlobal.add(labelErreur, BorderLayout.NORTH);
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
	
	public void majComboGamme(){
		//On crée des nouvelles combobox que l'on rajoute à chaque client en leur précisant l'objet qui a déjà été selectionné
		String[] gamme = rg_.recuperationGamme();
		
		for (NouveauProduit np : listnvproduit){
			Object selection;
			try{
				selection = np.getSelectGamme();
			} catch (Exception e){
				selection = null;
			}
			np.majListGamme(gamme, selection);
		}
	}
	
	/**
	 * recupere et enregistre les informations des produits 
	 * @param evt
	 */
	private void enregistrercliActionPerformed(ActionEvent evt) {
		labelErreur.setText("");
		RecupGamme rg = new RecupGamme(main_);
		String messageErreur = "";
		
		main_.getTransaction().begin();
		for (NouveauProduit listprod : listnvproduit){
			try{
				Gamme gamme = rg.recuperationGamme(listprod.getCombo().getSelectedItem().toString());
				
				List<Livraison> livr = new ArrayList<Livraison>();
				List<Prevision> prev = new ArrayList<Prevision>();
				List<QuantiteReelle> qr = new ArrayList<QuantiteReelle>();
				List<MargeLivraison> ml = new ArrayList<MargeLivraison>();
				
				Produit temp = new Produit(listprod.getSelectCode(), listprod.getSelectNature(), listprod.getSelectNom(), listprod.getSelectPresentation(), 
						gamme, Integer.parseInt(listprod.getSelectQte()), livr, prev, qr, ml);
				main_.getManager().persist(temp);
				
			} catch (Exception e){
				messageErreur = "Erreur : des informations sont manquantes ou erronées";
			}
		}
		
		if (messageErreur == ""){
			main_.getTransaction().commit();
			fenetre.setVisible(false);
			
			if (panel_ != null)
				panel_.terminerAjoutClient();
			
			if (ClasseTraitement_ != null)
				ClasseTraitement_.insertionDonneeFin();
		}
		else{
			main_.getTransaction().rollback();
			labelErreur.setText(messageErreur);
		}
	}

}
