package fr.galettedebroons.view;


import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import fr.galettedebroons.controller.LectureFichier;
import fr.galettedebroons.model.RecuperationDonnees;

/**
 *
 * @author  Julie Guegnaud
 * @author  Oumoul Sy
 * @since   16/03/2015
 */
public class FormulaireAjoutDonnees extends javax.swing.JPanel {

    /**
     * Creates new form FormulaireAjoutDonnees
     */
    public FormulaireAjoutDonnees() {
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

        Titre = new javax.swing.JLabel();
        labelInfo = new javax.swing.JLabel();
        valFichier = new javax.swing.JTextField();
        boutonParcourir = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JSeparator();
        bonLivraison = new javax.swing.JLabel();
        labelDate = new javax.swing.JLabel();
        valBonLivraison = new javax.swing.JTextField();
        codeClient = new javax.swing.JLabel();
        valCodeClient = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        boutonEnregistrer = new javax.swing.JButton();
        boutonAnnuler = new javax.swing.JButton();
        valDate = new com.toedter.calendar.JDateChooser();
        nomClient = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        Titre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titre.setText("Ajouter les dernières livraisons");
        Titre.setAlignmentY(0.0F);

        labelInfo.setText("Rentrer les informations ou renseigner un fichier");

        valFichier.setText("Fichier.xsl, .ods, .txt, .csv");

        boutonParcourir.setText("Parcourir");
        boutonParcourir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonParcourirActionPerformed(evt);
            }
        });

        bonLivraison.setText("Bon de livraison");

        labelDate.setText("Date de livraison");

        codeClient.setText("Code client");
        
        RecuperationDonnees rd = new RecuperationDonnees();
        client = rd.recuperationCodeClient();
        String[] code_client = new String[client.size()+1];
        code_client[0] = "selection";
        int indice = 1;
        for (Object[] cl : client){
        	code_client[indice] = cl[0].toString();
        	indice++;
        }
        
        valCodeClient.setModel(new javax.swing.DefaultComboBoxModel(code_client));
        valCodeClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valCodeClientActionPerformed(evt);
            }
        });

        boutonEnregistrer.setText("Enregistrer");
        boutonEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonEnregistrerActionPerformed(evt);
            }
        });

        boutonAnnuler.setText("Annuler");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nom article", "Code article", "Quantité livrée", "Quantité reprise"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
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
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(boutonEnregistrer)
                        .addGap(52, 52, 52)
                        .addComponent(boutonAnnuler))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bonLivraison)
                                    .addComponent(codeClient))
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(valCodeClient, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(nomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(valBonLivraison, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelDate)
                                .addGap(79, 79, 79)
                                .addComponent(valDate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(labelInfo)
                        .addGap(31, 31, 31)
                        .addComponent(valFichier, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boutonParcourir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(Titre)))
                .addGap(9, 123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titre)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInfo)
                    .addComponent(valFichier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boutonParcourir))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valBonLivraison, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bonLivraison))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(valDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDate))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codeClient)
                            .addComponent(valCodeClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boutonAnnuler)
                    .addComponent(boutonEnregistrer))
                .addGap(24, 24, 24))
        );
    }// </editor-fold>                        

    private void boutonParcourirActionPerformed(java.awt.event.ActionEvent evt) {                                                
        
        JFileChooser choix = new JFileChooser();
        int retour=choix.showOpenDialog(getParent());
        if(retour==JFileChooser.APPROVE_OPTION){
            System.out.println ("un fichier a été choisi (sortie par OK)");
            // nom du fichier  choisi 
            System.out.println( choix.getSelectedFile().getName());
            // chemin absolu du fichier choisi
            valFichier.setText(choix.getSelectedFile().getAbsolutePath());
            System.out.println(choix.getSelectedFile().getAbsolutePath());
        }
        // pas de fichier choisi
        else
            System.out.println("Aucun fichier choisi") ;
        
    }                                               

    private void boutonEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        
        LectureFichier lfe = new LectureFichier();
        try {
            lfe.ouverture_fichier(valFichier.getText());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        
    }                                                 

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void valCodeClientActionPerformed(java.awt.event.ActionEvent evt) {
    	String enseigne = "";
    	for (Object[] cl : client){
    		System.out.println(valCodeClient);
    		if (cl[0] == valCodeClient.getSelectedItem().toString()){
    			enseigne = cl[1].toString();
    		}
    	}
    	nomClient.setText(enseigne);
    }                                             

    private List<Object[]> client;

    // Variables declaration - do not modify                     
    private javax.swing.JLabel Titre;
    private javax.swing.JLabel bonLivraison;
    private javax.swing.JButton boutonAnnuler;
    private javax.swing.JButton boutonEnregistrer;
    private javax.swing.JToggleButton boutonParcourir;
    private javax.swing.JLabel codeClient;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JLabel nomClient;
    private javax.swing.JTextField valBonLivraison;
    private javax.swing.JComboBox valCodeClient;
    private com.toedter.calendar.JDateChooser valDate;
    private javax.swing.JTextField valFichier;
    // End of variables declaration                   
}