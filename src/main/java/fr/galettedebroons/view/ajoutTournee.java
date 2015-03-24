/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.galettedebroons.view;

import java.awt.event.ActionEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Tournee;

/**
 *
 * @author Oumoul
 */
public class ajoutTournee extends javax.swing.JPanel {
	
	private NouveauClient nc;
	private static EntityManager manager_;
	static EntityTransaction tx;
	EntityManagerFactory factory;
	EntityManager manager;
	Tournee nvGamme;

    /**
     * Creates new form ajoutTournée
     */
    public ajoutTournee() {
        initComponents();
    }
    
    public ajoutTournee(NouveauClient nc) {
    	this.nc = nc;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lun = new javax.swing.JCheckBox();
        mar = new javax.swing.JCheckBox();
        mer = new javax.swing.JCheckBox();
        ven = new javax.swing.JCheckBox();
        sam = new javax.swing.JCheckBox();
        dim = new javax.swing.JCheckBox();
        jeu = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        nmtourn = new javax.swing.JTextField();
        savet = new javax.swing.JButton();
        annult = new javax.swing.JButton();
        
        nomTournee = "";
        jourTournee = null;
        
        factory = Persistence.createEntityManagerFactory("majAnteros");
    	manager = factory.createEntityManager();
    	setManager(manager);
    	final EntityTransaction tx = manager_.getTransaction();
		tx.begin();

        jLabel1.setText("AJOUT TOURNEE");

        jLabel2.setText("NOM");

        jLabel3.setText("JOURS DE TOURNEE");

        lun.setText("Lundi");

        mar.setText("Mardi");

        mer.setText("Mercredi");

        ven.setText("Vendredi");

        sam.setText("Samedi");

        dim.setText("Dimanche");

        jeu.setText("Jeudi");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Profil Tournée");

        nmtourn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmtournActionPerformed(evt);
            }
        });

        savet.setText("Enregistrer");
        
        savet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }

			private void saveActionPerformed(ActionEvent evt) {
				
				nomTournee = nmtourn.getText();
				
				int i = 0;
				if(lun.isSelected()){
					jourTournee[i] = "lundi";
					i++;
				}if(mar.isSelected()){
					jourTournee[i] = "mardi";
					i++;
				}if(mer.isSelected()){
					jourTournee[i] = "mercredi";
					i++;
				}if(jeu.isSelected()){
					jourTournee[i] = "jeudi";
					i++;
				}if(ven.isSelected()){
					jourTournee[i] = "vendredi";
					i++;
				}if(sam.isSelected()){
					jourTournee[i] = "samedi";
					i++;
				}if(dim.isSelected()){
					jourTournee[i] = "dimanche";
					i++;
				}
				System.out.println("le nom de ma tournee : " +nomTournee);
				System.out.println("le nom de ma tournee : " +jourTournee);
				//nvGamme = new Tournee();
				//nvGamme.setJour_tournee(jourTournee);
				//nvGamme.setNom(nomTournee);
				nvGamme = new Tournee(nomTournee, jourTournee);
				manager_.persist(nvGamme);
				tx.commit();
				nc.methode();
			}
        });

        annult.setText("Annuler");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lun)
                                    .addComponent(mar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(annult, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jeu)
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sam)
                                    .addComponent(dim)
                                    .addComponent(ven))))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(savet)
                                .addGap(138, 138, 138))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nmtourn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(156, 156, 156))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(138, 138, 138))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(nmtourn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lun)
                            .addComponent(ven))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mar)
                            .addComponent(sam)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jeu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mer)
                            .addComponent(dim))
                        .addGap(58, 58, 58))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savet)
                    .addComponent(annult)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nmtournActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmtournActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmtournActionPerformed

    public EntityManager getManager(){
		return manager_;
	}
	public void setManager(EntityManager manager){
		manager_ = manager;
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annult;
    private javax.swing.JCheckBox dim;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JCheckBox jeu;
    private javax.swing.JCheckBox lun;
    private javax.swing.JCheckBox mar;
    private javax.swing.JCheckBox mer;
    private javax.swing.JTextField nmtourn;
    private javax.swing.JCheckBox sam;
    private javax.swing.JButton savet;
    private javax.swing.JCheckBox ven;
    private String nomTournee;
    private String[] jourTournee;
    // End of variables declaration//GEN-END:variables
}
