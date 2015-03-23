package fr.galettedebroons.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Livraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Temporaire;
import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.test.Main;

public class VueGlobalNvProduit {
	private static EntityManager manager_;
	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction tx;
	static List<Object[]> results;
	
	static JPanel panelGeneral;
	static JPanel panelGlobal;
	static JPanel panelBouton;
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
	Main main_;
	static PanelEdition panel_;
	static List<String> produits;
	
	/**
	 * constructeur appel methode dynamique creation panel nouveau client
	 */
	public VueGlobalNvProduit(){
		initialisationProduits();
	}
	
	public VueGlobalNvProduit(Main main, PanelEdition panel){
		main_ = main; //Contient notre EntityManager => plus besoin de faire : new Main(manager_) et de créer le manager
		panel_ = panel;
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
		
		listnvproduit = new ArrayList<NouveauProduit>();
		fenetre = new JFrame();
		fenetre.setSize(700,500);
		
		
		factory = Persistence.createEntityManagerFactory("majAnteros");
    	manager = factory.createEntityManager();
    	setManager(manager);
    	tx = manager_.getTransaction();
		tx.begin();
    	
		RecuperationDonnees rd = new RecuperationDonnees(new Main(manager));
    	// recupere les produits dans la table temporaire -----
		/*
    	CriteriaBuilder cb = manager_.getCriteriaBuilder();
		CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
    	Root<Temporaire> t = q.from(Temporaire.class);
		q.select(cb.array(t.get("code_produit"), t.get("nom_client"))).distinct(true);
		q.where(cb.or(cb.equal(t.get("code_erreur"), "C"),cb.equal(t.get("code_erreur"), "CP")));
		results = manager_.createQuery(q).getResultList();
		
		for(Object[] result : results) {
			System.out.println("Test recupère Code produit ?!" +result[0].toString());
		}*/
		
		
		produits = null;
		int nbNewProd = 0;
		if (panel_ == null){
			produits = rd.recuperationProduitTemp();
			nbNewProd = produits.size();
		}else
			nbNewProd = 1;
		// fin recupere produits -----
		
		
		
		// recupere les gammes existantes pour comboBox -----
		/*
		List<Gamme> gammes = new ArrayList<Gamme>();
		gammes = manager_.createQuery("select g from Gamme g", Gamme.class).getResultList();
		System.out.println("MA LISTE DEROULANTE !!!! " +gammes.size());
		int k=0;
    	List<String> lagamme = new ArrayList<String>();
    	for(Gamme gamme : gammes){
    		String magamme = gammes.get(k).getCode_gamme() + " " +gammes.get(k).getDuree_conservation();
    		System.out.println("Ma gamme : " +magamme);
    		lagamme.add(magamme);
    		comboGamme.addItem(magamme);
    		k++;
    	}*/
		String[] gamme = rd.recuperationGamme();
		// fin recupere gammes existantes -----
    	
    	comboGamme = new JComboBox[nbNewProd];
    	int indice = 0;
    	if (panel_ == null){
			for(String prod : produits){
    	
    		JComboBox jb = new JComboBox(gamme);
    		comboGamme[indice] = jb;
    		System.out.println("Ma combo gaaaaamme : " +jb);
    		panelGeneral.setLayout(new GridLayout(nbNewProd*2,0));
    		NouveauProduit np = new NouveauProduit(prod.toString(), jb);
    		panelGeneral.add(np);
    		listnvproduit.add(np);
    		indice ++;
			}
    	}else{
    		System.out.println("je suis rataché a un panel T,T");
				JComboBox jb = new JComboBox(gamme);
				panelGeneral.setLayout(new GridLayout(nbNewProd*2,0));
				NouveauProduit np = new NouveauProduit(null, jb);
				panelGeneral.add(np);
			}
    	
		
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
		//fenetre.add(panelGlobal);
		
		JScrollPane pane = new JScrollPane(panelGlobal);
		fenetre.setContentPane(pane);
		
		//fenetre.add(panelBouton);
		fenetre.setVisible(true);
    	
	}
	
	/**
	 * recupere et enregistre les informations des produits 
	 * @param evt
	 */
	private static void enregistrercliActionPerformed(ActionEvent evt) {
		
		if (panel_ != null)
			panel_.terminerAjoutProduit();

		JViewport viewport = new JViewport();
		System.out.println("taille de la liste : " +listnvproduit.size());
		
		for (NouveauProduit listprod : listnvproduit){
			// reinitialisation des parametre de creation de produit
			code_produit = "";
			nature_produit = "";
			nom_produit = "";
			qte_produit = 0;
			presentation_produit = "";
			gamme_produit = "";
			textCombo = "";
			
			
			System.out.println("Nombre de composants" +listprod.getComponentCount());
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
			System.out.println("ma quantite : " +s);
			
			//System.out.println("la valeur de mon textfield qte : " + ((JTextField) listprod.getComponent(1)).getText());
			//System.out.println("Ma quantité est de : " +Integer.parseInt(((JTextField) listprod.getComponent(1)).getText()));
			
			// nature produit
			//System.out.println(((JLabel) (listprod.getComponent(2))).getText());
			nature_produit = ((JTextField) listprod.getComponent(3)).getText();
			
			// code produit
			//System.out.println(((JLabel) (listprod.getComponent(4))).getText());
			code_produit = ((JTextField) listprod.getComponent(5)).getText();
			
			// nom produit
			//System.out.println(((JLabel) (listprod.getComponent(6))).getText());
			nom_produit = ((JTextField) listprod.getComponent(7)).getText();
			
			// label présentation produit
			//System.out.println(((JLabel) (listprod.getComponent(8))).getText());
			viewport = ((JScrollPane)listprod.getComponent(10)).getViewport();
			presentation_produit = ((JTextArea) viewport.getComponent(0)).getText();
			
			//gamme label
			gamme_produit = ((JLabel) listprod.getComponent(9)).getText();
			
			// gamme
			textCombo = (String) listprod.getCombo().getSelectedItem();
			String[] t = textCombo.split(" ");
			for(int i = 0; i < t.length; i++){
				System.out.println("mon elmt : " +t[i]);
			}
			Gamme gamme = new Gamme(t[0], Integer.parseInt(t[1]), null, null);
			System.out.println(listprod.getCombo().getSelectedItem());
			
			
			// ajoute en base les elements recuperes
			
			Produit temp;
			try{
				temp = new Produit(code_produit, nom_produit, presentation_produit, gamme, qte_produit);
				manager_.persist(temp);
				tx.commit();
			} catch(Exception e){
				e.printStackTrace();
			}
			
		}
		JOptionPane.showMessageDialog(panelGeneral, "Les nouveaux produits ont été ajouté");
	}
	

	/*public static void main(String[] args){
	}*/
	
	public EntityManager getManager(){
		return manager_;
	}
	public static void setManager(EntityManager manager){
		manager_ = manager;
	}

}
