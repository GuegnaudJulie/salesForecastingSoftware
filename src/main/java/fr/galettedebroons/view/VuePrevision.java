package fr.galettedebroons.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.main.Main;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NouveauClient.java
 *
 * Created on 16 mars 2015, 16:18:47
 */

/**
 *
 * @author poher
 */
public class VuePrevision extends javax.swing.JPanel {
	
	private Main main_;

    /** Creates new form NouveauClient */
    public VuePrevision(Main main) {
    	this.main_ = main;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

    	//jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        
        rd = new RecuperationDonnees(main_);
        
        //recuperation tournee
        listeTournee = rd.recuperationTournee();
        jComboBox1 = new JComboBox(listeTournee);
        jComboBox1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		jComboBox1ActionPerformed(evt);
			}
        });

        
        /*listClient = rd.recuperationProfil();
		jComboBox1 = new JComboBox(listClient);*/
        
        

        //jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selectionner une tournée"}));

        reinitialiserJTable();
        
       /* jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String [] {
                    null, null, null, null, null, null
                }
            ));*/
        jTable1.setAutoCreateColumnsFromModel(false);
        
        
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Prévision");
        
        jLabel2.setText("Tournée");

        jLabel3.setText("Profil");

        jLabel4.setText("Date");

        jButton1.setText("Prévision");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		jButton1ActionPerformed(evt);
			}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, 0, 101, Short.MAX_VALUE)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(159, 159, 159)
                            .addComponent(jButton1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(622, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275))
        );
    }// </editor-fold>

    /**
     * Action clic bouton sur la combobox des tournee
     * @param evt
     */
    @SuppressWarnings("unchecked")
	private void jComboBox1ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
    	String profil = "";
    	profil = (String) jComboBox1.getSelectedItem();
    	System.out.println("jComboBox1.getSelectedIndex()" +jComboBox1.getSelectedItem());
    	listClient = rd.recuperationProfilTournee(profil);
    	jComboBox2.removeAllItems();
    	DefaultComboBoxModel model = new DefaultComboBoxModel(listClient);
    	jComboBox2.setModel(model);
    	jComboBox2.addItem("Tous");
    	
	}
    
    /**
     * action sur le bouton prevision pour voir afficher nos previsions dans la table
     * @param evt
     */
    private void jButton1ActionPerformed(ActionEvent evt) {
    	reinitialiserJTable();
    	
    	jTable1.repaint();
    	String nomTournee = "";
    	String nomProfil = "";
    	String date = "";
    	java.util.Date data = null;
    	
    	nomTournee = (String) jComboBox1.getSelectedItem();
    	System.out.println("nomTournee : " +nomTournee);
    	nomProfil = (String) jComboBox2.getSelectedItem();
    	System.out.println("nomProfil : " +nomProfil);
    	System.out.println(String.valueOf(jDateChooser1.getDate()));
    	
    	data = jDateChooser1.getDate();
    	System.out.println("data :  " +data);
    	date = String.valueOf(jDateChooser1.getDate());
    	System.out.println("ma date : " +date);
    	
    	// appel de recuperation des previsions
    	//rd.recuperationPrevision(data, nomTournee);
    	// si seulement tournee selectionnee
    	System.out.println("MA TOURNEEEEE : " +nomTournee);
    	
    	//si un profil est selectionné en particulier
    	if(!nomProfil.toString().contains("Tous") && data != null){
    		String profil = "";
    		String monProfil = "";
    		String monProduit = "";
    		int maQuantité = 0;
    		
        	profil = (String) jComboBox2.getSelectedItem();
        	List<Object[]> maliste = rd.recupuniqueProfil(data, profil);
        	System.out.println("la taille de ma liste" +maliste.size());
        	
        	jTable1.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Profil");
    		jTable1.repaint();
    		
    		jTable1.getTableHeader().repaint();
    		//jTable1.getModel().setValueAt(monProfil, 0, 0);
    		
    		
    		int indiceTitre = 1;
    		int colonneProduitQte = 1;
    		int ligneProduitQte = 0;
    		for (Object[] p : maliste){
    			System.out.println("Mon profil " +p[1]);
    			monProfil = (String) p[1];
    			System.out.println("Mon produit " +p[2]);
    			monProduit = (String) p[2];
    			System.out.println("Ma quantité " +p[0]);
    			maQuantité = (int) p[0];
    			
    			jTable1.getModel().setValueAt(monProfil, 0, 0);
    			jTable1.getTableHeader().getColumnModel().getColumn(indiceTitre).setHeaderValue(monProduit);
    			indiceTitre++;
    			jTable1.getModel().setValueAt(maQuantité, ligneProduitQte, colonneProduitQte);
    			colonneProduitQte++;
    		}
    	}
    	
    	// si on veut voir les prévisions pour tous les profil de la tournee
    	if(nomTournee != "" && nomProfil.toString().contains("Tous") && data != null){
    		List<Object[]> maliste = rd.recupPrevTournee(nomTournee, data);
    		String monProfilPrecedent = "";
    		String monProfil = "";
    		String monProduit = "";
    		int maQuantité = 0;
    		String[] mesTitre = new String[100];
    		int tabTitre = 0;
    		int ligne = 0;
    		int colonne = 0;
    		int ligneprofil = 0;
    		int colonneprofil = 0;
    		
    		jTable1.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Profil");
    		jTable1.repaint();
    		
    		jTable1.getTableHeader().repaint();
    		int k = 1;
    		for (Object[] p : maliste){
    			System.out.println("Mon profil " +p[1]);
    			monProfil = (String) p[1];
    			System.out.println("Mon produit " +p[2]);
    			monProduit = (String) p[2];
    			System.out.println("Ma quantité " +p[0]);
    			maQuantité = (int) p[0];
    			
    			// si meme profil alors ajout du produit après ancient produit ajouté
    			System.out.println("la valeur de mon profil : " +monProfilPrecedent);
    			System.out.println("la valeur de mon profil précédent : " +colonne);
    			if(monProfil.equals(monProfilPrecedent) || (jTable1.getModel().getValueAt(0, 0) == null)){
    				System.out.println("je rentre bien dans ma condition");
    				jTable1.getModel().setValueAt(monProfil, ligneprofil, colonneprofil);
    				jTable1.getTableHeader().getColumnModel().getColumn(k).setHeaderValue(monProduit);
    				mesTitre[tabTitre] = monProduit;
    				tabTitre++;
    				colonne++;
    				System.out.println("ma quantité : " +maQuantité);
    				jTable1.getModel().setValueAt(maQuantité, ligne, colonne);
    				monProfilPrecedent = monProfil;
    				
    				System.out.println("la valeur de ma colonne : " +colonne);
    				System.out.println("la valeur de ma ligne : " +ligne);
    				k++;
    			}
    			if(!monProfil.equals(monProfilPrecedent)){
    				System.out.println("mon profil différent : " +monProfil);
    				ligneprofil++;
    				jTable1.getModel().setValueAt(monProfil, ligneprofil, colonneprofil);
    				// si mon produit est différent d'un produit déjà existant alors on avance
    				k = 0;
    				System.out.println("c'est quoiii ? " +mesTitre[0]);
    				while(mesTitre[k] != null){
    					System.out.println("mamaaaaa");
    					if(monProduit.equals(mesTitre[k])){
    						System.out.println("monproduit : " +mesTitre[k] + "mon indice : " +k);
    						System.out.println("ligne profil : " +ligneprofil);
    						System.out.println("colonne profil : " +colonneprofil);
    						jTable1.getModel().setValueAt(maQuantité, ligneprofil, k+1);
    						ligne++;
    					}
    					k++;
    				}
    				
    				jTable1.getTableHeader().getColumnModel().getColumn(k+1).setHeaderValue(monProduit);
    				monProfilPrecedent = monProfil;
    				k++;
    			}
    		}
    	}
	}
    
    /**
     * reinitialisation de l'affichage de la table
     */
    public void reinitialiserJTable(){
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String [] {
                    null, null, null, null, null, null
                }
            ));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    RecuperationDonnees rd;
    String[] listClient;
    String[] listeTournee;
}
