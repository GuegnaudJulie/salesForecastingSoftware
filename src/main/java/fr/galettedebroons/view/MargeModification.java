package fr.galettedebroons.view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JComboBox;

import fr.galettedebroons.model.ModificationDonnees;
import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.test.Main;

/**
 *
 * @author poher
 */

public class MargeModification extends javax.swing.JPanel {
	
	    /** Creates new form MargeModification */
	   
		public MargeModification(Main main) {
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

	        jComboBox1 = new javax.swing.JComboBox();
	        jSeparator1 = new javax.swing.JSeparator();
	        jComboBox2 = new javax.swing.JComboBox();
	        jButton1 = new javax.swing.JButton();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();
	        jLabel3 = new javax.swing.JLabel();
	        jButton2 = new javax.swing.JButton();
	        jLabel4 = new javax.swing.JLabel();
	        
	        rd = new RecuperationDonnees(main_);
			md = new ModificationDonnees(main_);

			// recherche des clients en base et ajout dans combobox1
			listClient = rd.recuperationProfil();
			jComboBox1 = new JComboBox(listClient);
	        
	        //jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

	        // recherche des produits associes au client selectionne
			listProduit = rd.recuperationProduit();
			jComboBox2 = new JComboBox(listProduit);
	        //jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

	        jButton1.setText("Rechercher");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt);
	            }
	        });

	        // Recherche en base le taux de reprise actuel
	        // en fonction du client et du produit selectionne
	        jLabel1.setText("Taux de reprise actuel : ");

	        jLabel2.setText("Nouveau taux : ");

	        jTextField1.setText("");

	        jLabel3.setText("%");

	        jButton2.setText("Enregistrer");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton2ActionPerformed(evt);
	            }
	        });
	        
	        jLabel4.setText("Marge de reprise");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(29, 29, 29)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jLabel2)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jLabel3))
	                            .addComponent(jButton1)
	                            .addComponent(jButton2)))
	                    .addComponent(jLabel4))
	                .addContainerGap(80, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap(43, Short.MAX_VALUE)
	                .addComponent(jLabel4)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton1))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1)
	                    .addComponent(jLabel2)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3))
	                .addGap(91, 91, 91)
	                .addComponent(jButton2)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
	        );
	    }// </editor-fold>

	    /**
	     * recherche du taux de reprise en fonction du client et du produit
	     * @param evt
	     */
	    private void jButton1ActionPerformed(ActionEvent evt) {
	    	jLabel1.setText("Taux de reprise actuel : ");
	    	double tauxreprise = 0.0;
	    	this.enseigne_client = "";
	    	this.codeProduit = "";
	    	
	    	enseigne_client = (String) jComboBox1.getSelectedItem();
			codeProduit = (String) jComboBox2.getSelectedItem();
			
			tauxreprise = rd.recuperationTxReprise(codeProduit, enseigne_client);
			jLabel1.setText(jLabel1.getText() + " " + tauxreprise + " %");
			this.repaint();
			
		}
	    
	    /**
	     * enregistrement en base du nouveau taux de reprise
	     * update
	     * @param evt
	     */
	    private void jButton2ActionPerformed(ActionEvent evt) {
	    	double pourcentage = 0.0;
	    	String nomprofil = "";
	    	String nomproduit = "";
	    	
			pourcentage = Integer.parseInt(jTextField1.getText());
			nomprofil = (String) jComboBox1.getSelectedItem();
			nomproduit = (String) jComboBox2.getSelectedItem();
			System.out.println("mon pourcentaaaaa : " +pourcentage);
			System.out.println("mon pourcentaaaaa : " +nomprofil);
			System.out.println("mon pourcentaaaaa : " +nomprofil);
			
			md.updateTauxReprise(pourcentage, nomprofil, nomproduit);
		}

	    // Variables declaration - do not modify
	    private javax.swing.JButton jButton1;
	    private javax.swing.JButton jButton2;
	    private javax.swing.JComboBox jComboBox1;
	    private javax.swing.JComboBox jComboBox2;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JSeparator jSeparator1;
	    private javax.swing.JTextField jTextField1;
	    
	    Main main_;
		RecuperationDonnees rd;
		ModificationDonnees md;
		
		String[] listClient;
		String[] listProduit;
		
		String enseigne_client;
		String codeProduit;
		
	    // End of variables declaration

	}

