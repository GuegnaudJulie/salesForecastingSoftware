package fr.galettedebroons.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Temporaire;
import fr.galettedebroons.domain.Tournee;

public class VueGlobalNvProduit {
	private static EntityManager manager_;
	static EntityManagerFactory factory;
	static EntityManager manager;
	static List<Object[]> results;
	
	static JPanel panelGeneral;
	static JPanel panelGlobal;
	static JPanel panelBouton;
	static JFrame fenetre;
	static JComboBox comboGamme;
	
	
	
	public VueGlobalNvProduit(){
		initialisationProduits();
	}
	
	private static void initialisationProduits() {
		panelGeneral = new JPanel();
		panelGlobal = new JPanel();
		panelBouton = new JPanel();
		fenetre = new JFrame();
		comboGamme = new JComboBox();
		fenetre = new JFrame();
		fenetre.setSize(700,500);
		
		factory = Persistence.createEntityManagerFactory("majAnteros");
    	manager = factory.createEntityManager();
    	setManager(manager);
    	
    	// recupere les produits dans la table temporaire -----
    	CriteriaBuilder cb = manager_.getCriteriaBuilder();
		CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
    	Root<Temporaire> t = q.from(Temporaire.class);
		q.select(cb.array(t.get("code_produit"), t.get("nom_client"))).distinct(true);
		q.where(cb.or(cb.equal(t.get("code_erreur"), "C"),cb.equal(t.get("code_erreur"), "CP")));
		results = manager_.createQuery(q).getResultList();
		
		for(Object[] result : results) {
			System.out.println("Test recupère Code produit ?!" +result[0].toString());
		}
		// fin recupere produits -----
		
		
		
		// recupere les gammes existantes pour comboBox -----
		List<Gamme> gammes = new ArrayList<Gamme>();
		gammes = manager_.createQuery("select g from Gamme g", Gamme.class).getResultList();
		System.out.println("MA LISTE DEROULANTE !!!! " +gammes.size());
		int k=0;
    	List<String> lagamme = new ArrayList<String>();
    	for(Gamme gamme : gammes){
    		String magamme = gammes.get(k).getCode_gamme() + " " +gammes.get(k).getDuree_conservation() + " " 
    				+ gammes.get(k).getGamme_marge();
    		System.out.println("Ma gamme : " +magamme);
    		lagamme.add(magamme);
    		comboGamme.addItem(magamme);
    		k++;
    		
    	}
		// fin recupere gammes existantes -----
    	
    	for(Object[] result : results) {
    		panelGeneral.setLayout(new GridLayout(results.size()*2,0));
    		NouveauProduit np = new NouveauProduit(result[0].toString(), comboGamme);
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
	
	private static void enregistrercliActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	

	public static void main(String[] args){
		VueGlobalNvProduit np = new VueGlobalNvProduit();
	}
	
	public EntityManager getManager(){
		return manager_;
	}
	public static void setManager(EntityManager manager){
		manager_ = manager;
	}

}
