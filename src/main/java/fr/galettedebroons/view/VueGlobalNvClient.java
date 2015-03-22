package fr.galettedebroons.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
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
import javax.swing.JPanel;

import fr.galettedebroons.domain.Client;
import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Temporaire;
import fr.galettedebroons.domain.Tournee;
import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.test.Main;
import fr.galettedebroons.*;
/**
 * Vue globale de la creation d'un nouveau client
 * Vue dynamique en fonction du nombre de client non creer 
 * (present dans la table temporaire)
 * @author poher
 *
 */
public class VueGlobalNvClient extends Frame{
	

	private static EntityManager manager_;
	List<Object[]> results;
	static EntityTransaction tx;
	EntityManagerFactory factory;
	EntityManager manager;
	String nomClientLabel;
	String nomClientJTF;
	String codeClientLabel;
	String codeClientJTF;
	JFrame fenetre;
	JPanel panelGeneral;
	JPanel panelBouton;
	JPanel panelGlobal;
	JComboBox[] comboTournee;
	JComboBox[] comboGamme;
	List<NouveauClient> listnvclient;
	List<Object[]> clients;
	
	
	public VueGlobalNvClient(){
		initialisationClients();
		//fenetre.setVisible(true);
	}

	private void initialisationClients() {

		//this.comboTournee = new JComboBox();
		//this.comboGamme = new JComboBox();
		panelGeneral = new JPanel();
		panelBouton = new JPanel();
		panelGlobal = new JPanel();
		fenetre = new JFrame();
		fenetre.setSize(700,500);
		listnvclient = new ArrayList<NouveauClient>();
		
		
		factory = Persistence.createEntityManagerFactory("majAnteros");
    	manager = factory.createEntityManager();
    	this.setManager(manager);
    	
    	// recupere les clients dans la table temporaire -----
    	/*CriteriaBuilder cb = manager_.getCriteriaBuilder();
		CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
    	Root<Temporaire> t = q.from(Temporaire.class);
		q.select(cb.array(t.get("code_profil"), t.get("nom_client"))).distinct(true);
		q.where(cb.or(cb.equal(t.get("code_erreur"), "C"),cb.equal(t.get("code_erreur"), "CP")));
		results = manager_.createQuery(q).getResultList();*/
		
		RecuperationDonnees rd = new RecuperationDonnees(new Main(manager));
		clients = rd.recuperationClientTemp();
		int nbNewProd = clients.size();
		
		// fin recupere clients -----
		
		// debut de traitement de ma liste de tournee -----
		/*List<Tournee> tournees = new ArrayList<Tournee>();
		tournees = manager_.createQuery("select t from Tournee t", Tournee.class).getResultList();
		System.out.println("MA LISTE DEROULANTE !!!! " +tournees.size());
    	//System.out.println("MA LISTE DEROULANTE !!!! " +tournees.get(0).getId());
    	int k=0;
    	List<String> latournee = new ArrayList<String>();
    	for(Tournee tournee : tournees){
    		String matournee = tournees.get(k).getId() + " " +tournees.get(k).getJour_tournee() + " " 
    				+ tournees.get(k).getNom();
    		System.out.println("Ma tournee : " +matournee);
    		latournee.add(matournee);
    		comboTournee.addItem(matournee);
    		k++;
    		
    	}*/
    	
    	String[] tournee = rd.recuperationTournee(); 
    	
    	// fin de traitement de ma liste de tournee -----

    	
    	// debut de traitement de ma liste de gamme
    	/*List<Gamme> gammes = new ArrayList<Gamme>();
		gammes = manager_.createQuery("select g from Gamme g", Gamme.class).getResultList();
		int g = 0;
		List<String> lagamme = new ArrayList<String>();
    	for(Gamme gamme : gammes){
    		String magamme = gammes.get(g).getCode_gamme() + " " +gammes.get(g).getDuree_conservation();
    		System.out.println("Ma gamme : " +magamme);
    		lagamme.add(magamme);
    		comboGamme.addItem(magamme);
    		k++;
    		
    	}*/
    	
    	String[] gamme = rd.recuperationGamme();
    	comboGamme = new JComboBox[nbNewProd];
    	comboTournee = new JComboBox[nbNewProd];
    	
    	int indice = 0;
    	int indiceTournee = 0;
    	System.out.println("taille des clients : " +clients.size());
    	for(Object[] cli : clients) {
    		JComboBox jb = new JComboBox(gamme);
    		comboGamme[indice] = jb;
    		System.out.println("contenu de comboGamme : " +comboGamme[indice]);
    		
    		//for(Object[] tournee : tournees){
	    		JComboBox jt = new JComboBox(tournee);
	    		comboTournee[indiceTournee] = jt;
	    		System.out.println("contenu de comboTournee : " +comboTournee[indiceTournee]);
    		//}
    		panelGeneral.setLayout(new GridLayout(nbNewProd*2,0));
    		/*String codeclient, String nc, JComboBox tournee, JComboBox gamme*/
    		NouveauClient np = new NouveauClient(cli[1].toString(),cli[0].toString(), jt, jb);
    		panelGeneral.add(np);
    		listnvclient.add(np);
    		indice ++;
    		System.out.println("j'aiiiiiiii qq chose");
    	}
    	
		// fin de traitement de ma liste de gamme
		
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
		//fenetre.add(panelBouton);
		fenetre.setVisible(true);
		
	}
	
	public static void main(String[] args){
		VueGlobalNvClient nc = new VueGlobalNvClient();
	}
		
		public EntityManager getManager(){
			return manager_;
		}
		public void setManager(EntityManager manager){
			manager_ = manager;
		}
		
		private void enregistrercliActionPerformed(ActionEvent evt) {
			/*Temporaire t;
			Date d = new Date();
			
			t = new Temporaire(1, "B004", "CP", "159", 1, 2013-08-02, "SUPARNAV", 2, 0);
			manager_.persist(temp);
			tx.commit();*/
		}

}
