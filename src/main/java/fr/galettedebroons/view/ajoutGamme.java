/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.galettedebroons.view;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.selectBase.RecupGamme;

/**
 *
 * @author	Julie Guegnaud
 * @author	Melissa Poher
 * @since	01/04/2013
 */
public class ajoutGamme extends javax.swing.JFrame {

    /**
     * Creates new form AjoutGamme
     */
    public ajoutGamme(Main main, NouveauProduit np, NouveauClient nc) {
    	this.np_ = np;
    	this.nc_ = nc;
    	this.main_ = main;
    	this.rg_ = new RecupGamme(main);
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

        jPanel1 = new javax.swing.JPanel();
        labelTitre = new javax.swing.JLabel();
        labelCodeGamme = new javax.swing.JLabel();
        labelErreur = new javax.swing.JLabel();
        labelDureeGamme = new javax.swing.JLabel();
        boutonEnregistrer = new javax.swing.JButton();
        boutonAnnuler = new javax.swing.JButton();
        valCode = new javax.swing.JTextField();
        valDuree = new javax.swing.JTextField();
        labelUnite = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Ajout gamme");
        setResizable(false);

        labelTitre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitre.setText("Ajouter une gamme");

        labelCodeGamme.setText("Code de la gamme :");

        labelErreur.setForeground(new java.awt.Color(204, 0, 0));
        labelErreur.setText("");

        labelDureeGamme.setText("Durée entre une livraison et une reprise :");

        boutonEnregistrer.setText("Enregistrer");
        boutonEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonEnregistrerActionPerformed(evt);
            }
        });

        boutonAnnuler.setText("Annuler");
        boutonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonAnnulerActionPerformed(evt);
            }
        });

        labelUnite.setText("( jours )");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCodeGamme)
                    .addComponent(labelDureeGamme)
                    .addComponent(boutonEnregistrer))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(valCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(valDuree, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelUnite))
                            .addComponent(boutonAnnuler))
                        .addContainerGap(152, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(labelErreur))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(labelTitre)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitre)
                .addGap(41, 41, 41)
                .addComponent(labelErreur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodeGamme)
                    .addComponent(valCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDureeGamme)
                    .addComponent(valDuree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUnite))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boutonEnregistrer)
                    .addComponent(boutonAnnuler))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void boutonEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {                                                  
    	//Vérification d'erreur
    	String messErreur = "";
		if (valCode.getText().isEmpty())
			messErreur += "un code gamme doit être renseigné";
		
		else if (rg_.gammePresent(valCode.getText()))
			messErreur += "le code gamme existe déjà";
		
		int dureeConserv = 0;
		try{
			dureeConserv = Integer.parseInt(valDuree.getText());
		} catch (Exception e){
			if(!valDuree.getText().isEmpty()){
				if (messErreur != "")
					messErreur += " et veillez renseigner un entier pour la durée";
				else
					messErreur += "veillez renseigner un entier pour la durée";
			}
		}
		
		if (messErreur == "") {
			setVisible(false);
			
			labelErreur.setText("");
			main_.getTransaction().begin();
			
			Gamme nvGamme = new Gamme(valCode.getText());
			
			if (!valCode.getText().isEmpty())
				nvGamme.setDuree_conservation(dureeConserv);
			
			main_.getManager().persist(nvGamme);
			main_.getTransaction().commit();
			
			if (np_ != null)
				np_.methode();
			else if (nc_ != null)
				nc_.fermetureGamme();
		}
		else{
			labelErreur.setText("Erreur : " + messErreur);
		}
    }                                       

    private void boutonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	valCode.setText("");
    	valDuree.setText("");
        this.setVisible(false);
    }

    private RecupGamme rg_;
    private Main main_;
	private NouveauProduit np_;
	private NouveauClient nc_;
    // Variables declaration - do not modify                     
    private javax.swing.JButton boutonAnnuler;
    private javax.swing.JButton boutonEnregistrer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelCodeGamme;
    private javax.swing.JLabel labelDureeGamme;
    private javax.swing.JLabel labelErreur;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JLabel labelUnite;
    private javax.swing.JTextField valCode;
    private javax.swing.JTextField valDuree;
    // End of variables declaration                   
}
