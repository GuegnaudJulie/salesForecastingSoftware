package fr.galettedebroons.view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JComboBox;

import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.ModificationDonnees;
import fr.galettedebroons.model.RecuperationDonnees;

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
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                        .addGroup(layout.createSequentialGroup()
	                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                .addGroup(layout.createSequentialGroup()
	                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                .addComponent(jLabel1))
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                .addGroup(layout.createSequentialGroup()
	                                    .addComponent(jLabel2)
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                    .addComponent(jLabel3))
	                                .addComponent(jButton2)
	                                .addComponent(jButton1))
	                            .addGap(24, 24, 24))
	                        .addComponent(jSeparator1))
	                    .addComponent(jLabel4))
	                .addContainerGap(641, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel4)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton1))
	                .addGap(30, 30, 30)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel3)
	                    .addComponent(jLabel2)
	                    .addComponent(jLabel1))
	                .addGap(30, 30, 30)
	                .addComponent(jButton2)
	                .addGap(18, 18, 18)
	                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(315, Short.MAX_VALUE))
	        );
	    }// </editor-fold>

	    /**
	     * recherche du taux de reprise en fonction du client et du produit
	     * @param evt
	     */
	    private void jButton1ActionPerformed(ActionEvent evt) {
	    	try{
	    		enseigne_client = jComboBox1.getSelectedItem().toString();
	    		codeProduit = jComboBox2.getSelectedItem().toString();
	    		
	    		jLabel1.setText("Taux de reprise actuel : ");
		    	double tauxreprise;
				try{
					tauxreprise = rd.recuperationTxReprise(codeProduit, enseigne_client);
				} catch (Exception e){
					tauxreprise = 0.15;
				}
				
				jLabel1.setText(jLabel1.getText() + " " + tauxreprise + " %");
				this.repaint();
				
				/*
				if(tauxreprise == 0.0){
					double nvTauxReprise = 0.0;
					try{
						nvTauxReprise = Integer.parseInt(jTextField1.getText());
						md.insertNvTxReprise(nvTauxReprise, enseigne_client, codeProduit, false);
						
						// réaffichage de mon label avec la nouvelle valeur 
						tauxreprise = rd.recuperationTxReprise(codeProduit, enseigne_client);
						jLabel1.setText(jLabel1.getText() + " " + tauxreprise + " %");
					}catch(NumberFormatException e){
						System.out.println("toto");
					}
				}
				else{
					jLabel1.setText(jLabel1.getText() + " " + tauxreprise + " %");
					this.repaint();
				}
				*/
	    		
	    	} catch (Exception e){}
	    	
		}
	    
	    /**
	     * enregistrement en base du nouveau taux de reprise
	     * update
	     * @param evt
	     */
	    private void jButton2ActionPerformed(ActionEvent evt) {
	    	try{
	    		enseigne_client = jComboBox1.getSelectedItem().toString();
	    		codeProduit = jComboBox2.getSelectedItem().toString();
		    	Double nvTauxReprise = Double.parseDouble(jTextField1.getText());
		    	
		    	try{
		    		md.updateTauxReprise(nvTauxReprise, enseigne_client, codeProduit);
		    	} catch (Exception e){
		    		md.insertNvTxReprise(nvTauxReprise, enseigne_client, codeProduit, false);
		    	}
		    	
	    	} catch (Exception e){}
	    	/*
	    	double pourcentage = 0.0;
	    	double tauxreprise = 0.0;
	    	jLabel1.setText("Taux de reprise actuel : ");
	    	String nomprofil = "";
	    	String nomproduit = "";
	    	
			pourcentage = Integer.parseInt(jTextField1.getText());
			nomprofil = (String) jComboBox1.getSelectedItem();
			nomproduit = (String) jComboBox2.getSelectedItem();
			
			md.updateTauxReprise(pourcentage, nomprofil, nomproduit);
			tauxreprise = rd.recuperationTxReprise(codeProduit, enseigne_client);
			jLabel1.setText(jLabel1.getText() + " " + tauxreprise + " %");
			*/
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

