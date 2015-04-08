package fr.galettedebroons.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.galettedebroons.domain.Client;
import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.MargeLivraison;
import fr.galettedebroons.domain.Prevision;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.QuantiteReelle;
import fr.galettedebroons.domain.Temporaire;
import fr.galettedebroons.domain.Tournee;
import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.RangerDonneeTemporaire;
import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.model.TraitementDonneesTemporaire;
import fr.galettedebroons.model.selectBase.RecupClientProfil;
import fr.galettedebroons.model.selectBase.RecupGamme;
import fr.galettedebroons.model.selectBase.RecupTemporaire;
import fr.galettedebroons.model.selectBase.RecupTournee;
import fr.galettedebroons.*;
/**
 * Vue globale de la creation d'un nouveau client
 * Vue dynamique en fonction du nombre de client non creer 
 * (present dans la table temporaire)
 * @author poher
 *
 */
public class VueGlobalNvClient {
	
	Main main_;
	PanelEdition panel_;
	TraitementDonneesTemporaire ClasseTraitement_;
	RecuperationDonnees rd_;
	RecupTemporaire rt_;
	RecupClientProfil rcp_;
	List<Object[]> results;
	
	String nomClientLabel;
	String nomClientJTF;
	String codeClientLabel;
	String codeClientJTF;
	JFrame fenetre;
	JPanel panelGeneral;
	JPanel panelBouton;
	JPanel panelGlobal;
	JScrollPane scrollPane;
	JList[] listTournee;
	JLabel labelErreur;
	List<NouveauClient> listnvclient;
	List<Object[]> clients;
	List<Object> tourneeContenu;
	String codeGamme;
		
	/**
	 * Constructeur initialise composants graphiques
	 * @param main acces au Entity Manager pour gerer la BDD
	 * @param panel
	 */
	public VueGlobalNvClient(Main main, PanelEdition panel, TraitementDonneesTemporaire traitementDonneesTemporaire){
		main_ = main; 
		panel_ = panel;
		ClasseTraitement_ = traitementDonneesTemporaire;
		rd_ = new RecuperationDonnees(main_);
		rt_ = new RecupTemporaire(main_);
		rcp_ = new RecupClientProfil(main_);
		initialisationClients();
	}

	/**
	 * initialisation des composants
	 */
	private void initialisationClients() {
		labelErreur = new JLabel();
		panelGeneral = new JPanel();
		panelBouton = new JPanel();
		panelGlobal = new JPanel();
		fenetre = new JFrame();
		fenetre.setSize(700,500);
		listnvclient = new ArrayList<NouveauClient>();
		
		fenetre.addWindowListener( new WindowAdapter()
        {
        	public void windowClosing (WindowEvent e)
        	{
        		RangerDonneeTemporaire tdt = new RangerDonneeTemporaire(main_);
        		tdt.vidage();
        	}
        });
		fenetre.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
    	
		// récupération de la liste de client
		clients = null;
		int nbNewClient = 0;
    	if (panel_ == null){
	    	clients = rt_.recuperationClientTemp();
			nbNewClient = clients.size();
		}
		else
			nbNewClient = 1;
		
		// récupération de la liste de tournee
		String[] tournee = rd_.recuperationTournee();
		listTournee = new JList[nbNewClient];
		
		// récupération de la liste de gamme
    	String[] gamme = rd_.recuperationGamme();
    	
    	
    	
    	/*
   	
	JList liste = new JList(dlm);
	dlm.addElement("un");
	dlm.addElement("deux");
    	 * 
    	 */
    	
    	
    	int indice = 0;
    	if (panel_ == null){
    		List<String> listClients = new ArrayList<String>();
    		
    		GridBagLayout gbl = new GridBagLayout();
    		panelGeneral.setLayout(gbl);
    		GridBagConstraints gbc = new GridBagConstraints();
    		
			for(Object[] cli : clients){
				if (!rcp_.recuperationProfil(cli[1].toString()) && !listClients.contains(cli[1].toString()) ){
					listClients.add(cli[1].toString());
		    		
					DefaultListModel dlm = new DefaultListModel();
					JList jt = new JList(dlm);
					for (int i = 0; i<tournee.length; i++){
						dlm.addElement(tournee[i]);
					}
					listTournee[indice] = jt;
					
		    		NouveauClient np = new NouveauClient(main_, this, cli[1].toString(),cli[0].toString(), jt);
		    		
		    		gbc.gridx = 0;
		    		gbc.gridy = indice;
		    		gbc.fill = gbc.BOTH;
		    		gbc.weightx = 10;
		    		gbc.weighty = 1;
		    		gbc.insets = new Insets(0, 0, 1, 0);
		    		gbc.ipady = gbc.anchor = GridBagConstraints.CENTER;
		    		
		    		panelGeneral.add(np, gbc);
		    		listnvclient.add(np);
		    		indice ++;
				}
	    	}
    	}
    	else{
			JList jt = new JList(tournee);
			panelGeneral.setLayout(new GridLayout(nbNewClient*2,0));
			NouveauClient np = new NouveauClient(main_, this, null, null, jt);
    		panelGeneral.add(np);
    		listnvclient.add(np);
		}
    	
    	scrollPane = new JScrollPane(panelGeneral);
    	
		labelErreur.setPreferredSize(new Dimension(600, 30));
		labelErreur.setForeground(new java.awt.Color(204, 0, 0));
        labelErreur.setText("");
    	
		panelGlobal.setLayout(new BorderLayout());
		panelGlobal.add(labelErreur, BorderLayout.NORTH);
		panelGlobal.add(scrollPane, BorderLayout.CENTER);
		
		JButton boutonEnregistrer = new JButton();
		boutonEnregistrer.setText("Enregistrer");
		panelBouton.add(boutonEnregistrer);
		boutonEnregistrer.addActionListener(new java.awt.event.ActionListener() {
	           public void actionPerformed(java.awt.event.ActionEvent evt) {
	               enregistrercliActionPerformed(evt);
	           }
	    });
		
		panelGlobal.add(panelBouton, BorderLayout.SOUTH);
		fenetre.add(panelGlobal);
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
	}
	
	public void listTournee(){
		//On crée des nouvelles combobox que l'on rajoute à chaque client en leur précisant l'objet qui a déjà été selectionné
		String[] tournee = rd_.recuperationTournee();
		
		for (NouveauClient nc : listnvclient){
			Object selection = nc.getSelectTournee();
			nc.majComboTournee(tournee, selection);
		}
    }
	
	private void enregistrercliActionPerformed(ActionEvent evt) {
		labelErreur.setText("");
		String messageErreur = "";
		Client c;
		Profil p;
		Tournee tournee;
		RecupTournee rt = new RecupTournee(main_);
		
		main_.getTransaction().begin();
		for (NouveauClient listclient : listnvclient){
			try{
				//Recuperation code gamme
				tourneeContenu = listclient.getSelectTournee();
				
				//Création d'un profil
				List<Livraison> livr = new ArrayList<Livraison>();
				List<Prevision> prev = new ArrayList<Prevision>();
				List<QuantiteReelle> qr = new ArrayList<QuantiteReelle>();
				List<MargeLivraison> ml = new ArrayList<MargeLivraison>();
				List<Tournee> listTour = new ArrayList<Tournee>();
				
				if (tourneeContenu.isEmpty())
					messageErreur = "Erreur : des informations sont manquantes ou erronées";
				
				for (Object t : tourneeContenu){
					tournee = rt.recuperationTournee(t.toString());
					listTour.add(tournee);
				}
				p = new Profil(listclient.getTextFieldCC().getText(), livr, prev, qr, ml, true);
				p.setProfil_tournee(listTour);
				
				//Création d'un client
				List<Profil> profil = new ArrayList<Profil>();
				profil.add(p);
				c = new Client(listclient.getTextFieldCC().getText(), profil);
				p.setClient_profil(c);
				
				//Liaison du profil et de la tournee
				for (Tournee t : listTour){
					List<Profil> listP = t.getProfil_tournee();
					if (listP == null)
						t.setProfil_tournee(profil);
					else
						t.addProfil(p);
				}
				
				main_.getManager().persist(c);
					
			} catch (Exception e) {
				messageErreur = "Erreur : des informations sont manquantes ou erronées";
			}
		}
		
		if (messageErreur == ""){
			main_.getTransaction().commit();
			fenetre.setVisible(false);
			
			if (panel_ != null)
				panel_.terminerAjoutClient();
			
			if (ClasseTraitement_ != null)
				ClasseTraitement_.insertionProduit();
		}
		else{
			main_.getTransaction().rollback();
			labelErreur.setText(messageErreur);
		}
	}
	
}
