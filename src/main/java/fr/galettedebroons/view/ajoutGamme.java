/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.galettedebroons.view;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.test.Main;

/**
 *
 * @author Oumoul
 */
public class ajoutGamme extends javax.swing.JPanel {
	private Main main_;
	NouveauProduit np;
	NouveauClient nc;
	
	static String codeGamme;
	static int dureeConserv;

    /**
     * Creates new form ajoutGamme
     */
    public ajoutGamme(Main main, NouveauProduit np) {
    	this.np = np;
    	main_ = main;
        initComponents();
    }
    
    public ajoutGamme(Main main, NouveauClient nc) {
    	this.nc = nc;
    	main_ = main;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
    	
    	codeGamme = "";
    	dureeConserv = -1;

        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        saveg = new javax.swing.JButton();
        annulg = new javax.swing.JButton();
        codgam = new javax.swing.JTextField();
        dconsgam = new javax.swing.JTextField();
        
        jTextField2.setText("jTextField2");

        jLabel1.setText("AJOUT GAMME");

        jLabel2.setText("Code Gamme ");

        jLabel3.setText("Durée de Conservation");

        saveg.setText("Enregistrer");

        annulg.setText("Annuler");

        saveg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }

			private void saveActionPerformed(ActionEvent evt) {
				codeGamme = codgam.getText();
				dureeConserv = Integer.parseInt(dconsgam.getText());
				Gamme nvGamme = new Gamme(codeGamme, dureeConserv, null, null);
				main_.getManager().persist(nvGamme);
				main_.getTransaction().commit();
				np.methode();
			}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(saveg)
                        .addGap(35, 35, 35)
                        .addComponent(annulg)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addContainerGap(279, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(83, 83, 83)
                                    .addComponent(jLabel1)
                                    .addGap(0, 169, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(dconsgam, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(codgam, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(57, 57, 57)))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(codgam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dconsgam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annulg)
                    .addComponent(saveg)))
        );
    }// </editor-fold>

    private void codgamActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    // Variables declaration - do not modify
    private javax.swing.JButton annulg;
    private javax.swing.JTextField codgam;
    private javax.swing.JTextField dconsgam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton saveg;
    // End of variables declaration
}