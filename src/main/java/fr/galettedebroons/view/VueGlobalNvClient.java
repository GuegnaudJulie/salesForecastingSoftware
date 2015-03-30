package fr.galettedebroons.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.galettedebroons.domain.Client;
import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.Temporaire;
import fr.galettedebroons.domain.Tournee;
import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.model.TraitementDonneesTemporaire;
import fr.galettedebroons.model.selectBase.RecupTemporaire;
import fr.galettedebroons.test.Main;
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
	JComboBox[] comboTournee;
	JComboBox[] comboGamme;
	List<NouveauClient> listnvclient;
	List<Object[]> clients;
	String nomClient;
	String codeClient;
	String textCombo;
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
		initialisationClients();
	}

	/**
	 * initialisation des composants
	 */
	private void initialisationClients() {
		panelGeneral = new JPanel();
		panelBouton = new JPanel();
		panelGlobal = new JPanel();
		fenetre = new JFrame();
		fenetre.setSize(700,500);
		listnvclient = new ArrayList<NouveauClient>();
    	
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
		
		// récupération de la liste de gamme
    	String[] gamme = rd_.recuperationGamme();
    	comboGamme = new JComboBox[nbNewClient];
    	comboTournee = new JComboBox[nbNewClient];
    	
    	int indice = 0;
    	int indiceTournee = 0;
    	if (panel_ == null){

    		GridBagLayout gbl = new GridBagLayout();
    		panelGeneral.setLayout(gbl);
    		GridBagConstraints gbc = new GridBagConstraints();
    		
			for(Object[] cli : clients){
	    		JComboBox jb = new JComboBox(gamme);
	    		comboGamme[indice] = jb;
	    		
		    	JComboBox jt = new JComboBox(tournee);
		    	comboTournee[indiceTournee] = jt;
	    		
	    		NouveauClient np = new NouveauClient(main_, cli[1].toString(),cli[0].toString(), jt, jb);
	    		
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
    	else{
			JComboBox jb = new JComboBox(gamme);
			JComboBox jt = new JComboBox(tournee);
			panelGeneral.setLayout(new GridLayout(nbNewClient*2,0));
			NouveauClient np = new NouveauClient(main_, null, null, jt, jb);
    		panelGeneral.add(np);
    		listnvclient.add(np);
		}
		
    	/*int i = 0;
		for(Object[] result : results) {
			panelGeneral.setLayout(new GridLayout(results.size()*2,0));
			System.out.println("Je passe dans le for :");
			
			System.out.println("code Profil : " +result[0] + " nom client : " +result[1]);
			
			
			nomClientLabel = "Nom Client"+i;
			nomClientJTF = "jtfNomClient"+i;
			codeClientLabel = "Code Client"+i;
			codeClientJTF = "jtfCodeClient"+i;
			List<Tournee> listeTournee = new ArrayList<Tournee>();
			List<Gamme> listeGamme = new ArrayList<Gamme>();
			
			System.out.println("mon code client est : " +codeClientJTF + " " +nomClientJTF);
			NouveauClient nv = new NouveauClient(result[1].toString(),result[0].toString(), comboTournee, comboGamme);
			panelGeneral.add(nv);
			//fenetre.setVisible(true);
			//JButton bouton = new JButton("test");
			//this.fenetre.getContentPane().add(bouton);
			System.out.println("j'ajoute à ma fenetre");
			
			
		}*/
    	
    	scrollPane = new JScrollPane(panelGeneral);
		panelGlobal.setLayout(new BorderLayout());
		panelGlobal.add(panelGeneral, BorderLayout.CENTER);
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
		fenetre.add(panelGlobal);
		fenetre.setLocationRelativeTo(null);
		//fenetre.add(panelBouton);
		fenetre.setVisible(true);
	}
	
	private void enregistrercliActionPerformed(ActionEvent evt) {
		Client c = new Client();
		Profil p;
		//listnvclient
		
		for (NouveauClient listclient : listnvclient){
			// reinitialisation des parametre de creation de produit
			nomClient = "";
			codeClient = "";
			textCombo = "";
			codeGamme = "";
			
			nomClient = ((NouveauClient) listclient).getTextFieldNC().getText();
			codeClient = ((NouveauClient) listclient).getTextFieldCC().getText();
				
			try{
				main_.getTransaction().begin();
				c = new Client(nomClient, null);
				main_.getManager().persist(c);
				main_.getTransaction().commit();
				
				if (panel_ != null)
					panel_.terminerAjoutClient();
				
				if (ClasseTraitement_ != null)
					ClasseTraitement_.insertionProduit();
				
				fenetre.setVisible(false);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}
