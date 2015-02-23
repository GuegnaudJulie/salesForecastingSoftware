package fr.galettedebroons.view;

import java.io.File;

import javax.swing.JFileChooser;

/**
*
* @author Oumoul
*/
public class FormulaireGalette extends javax.swing.JFrame {

   /**
    * Creates new form FormulaireGalette
    */
	
	
   public FormulaireGalette() {
       initComponents();
   }

   public static void main(String[] args){
   	new FormulaireGalette().setVisible(true);
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
       jLabel4 = new javax.swing.JLabel();
       jLabel5 = new javax.swing.JLabel();
       jLabel6 = new javax.swing.JLabel();
       jLabel7 = new javax.swing.JLabel();
       Parcou = new javax.swing.JButton();
       save = new javax.swing.JButton();
       reset = new javax.swing.JButton();
       numdoc = new javax.swing.JTextField();
       fexcel = new javax.swing.JTextField();
       jLabel8 = new javax.swing.JLabel();
       NmClient = new javax.swing.JComboBox();
       codecli = new javax.swing.JComboBox();
       CdArt = new javax.swing.JComboBox();
       NmArt = new javax.swing.JComboBox();
       TyArt = new javax.swing.JComboBox();
       fichier = new javax.swing.JLabel();
       jLabel10 = new javax.swing.JLabel();
       jLabel11 = new javax.swing.JLabel();
       date = new com.toedter.calendar.JDateChooser();

       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

       jLabel1.setText("Formulaire");

       jLabel2.setText("Num�ro du document");

       jLabel3.setText("Date");

       jLabel4.setText("Code Client");

       jLabel5.setText("Nom du client");

       jLabel6.setText("Code Article");

       jLabel7.setText("Nom Article");

       Parcou.setText("Parcourir");
       Parcou.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               ParcouActionPerformed(evt);
               JFileChooser choix = new JFileChooser();
               int retour=choix.showOpenDialog(getParent());
                if(retour==JFileChooser.APPROVE_OPTION){
            	System.out.println ("un fichier a été choisi (sortie par OK)");
            	   // nom du fichier  choisi 
            	  System.out.println( choix.getSelectedFile().getName());
            	   // chemin absolu du fichier choisi
            	  fexcel. setText(choix.getSelectedFile().getAbsolutePath());
            	}
            // pas de fichier choisi
               else System.out.println("Aucun fichier choisi") ;   
             
               
           }
       });

       save.setText("Enregistrer");
       save.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               saveActionPerformed(evt);
           }
       });

       reset.setText("Annuler");

       fexcel.setText("Votre fichier Excel à télécharger");
       fexcel.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               fexcelActionPerformed(evt);
           }
       });

       jLabel8.setText("Type Article");

       NmClient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

       codecli.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

       CdArt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

       NmArt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
       NmArt.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               NmArtActionPerformed(evt);
           }
       });

       TyArt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

       fichier.setText("Télécharger un fichier Excel");

       jLabel10.setText("Veuillez renseigner les informations sur les articles");

       jLabel11.setText("Veuillez renseigner les informations sur le Client");

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                   .addGroup(layout.createSequentialGroup()
                       .addGap(0, 456, Short.MAX_VALUE)
                       .addComponent(save)
                       .addGap(49, 49, 49)
                       .addComponent(reset))
                   .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                           .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                               .addGap(20, 20, 20)
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jLabel3)
                                   .addComponent(jLabel2)
                                   .addComponent(jLabel4)
                                   .addComponent(jLabel6)
                                   .addComponent(jLabel7)
                                   .addComponent(jLabel8)
                                   .addComponent(jLabel5))
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                       .addGroup(layout.createSequentialGroup()
                                           .addGap(178, 178, 178)
                                           .addComponent(numdoc, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                           .addGap(9, 9, 9))
                                       .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                           .addGap(177, 177, 177)
                                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                               .addComponent(codecli, 0, 119, Short.MAX_VALUE)
                                               .addComponent(NmClient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                               .addComponent(NmArt, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                               .addComponent(CdArt, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                               .addComponent(TyArt, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                   .addGroup(layout.createSequentialGroup()
                                       .addGap(178, 178, 178)
                                       .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                           .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                               .addGap(91, 91, 91)
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jLabel11)
                                   .addComponent(jLabel10)))
                           .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                               .addGap(115, 115, 115)
                               .addComponent(fichier)))
                       .addGap(0, 0, Short.MAX_VALUE)))
               .addGap(52, 52, 52))
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
               .addGap(0, 0, Short.MAX_VALUE)
               .addComponent(jLabel1)
               .addGap(292, 292, 292))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(layout.createSequentialGroup()
                       .addGap(40, 40, 40)
                       .addComponent(jLabel2)
                       .addGap(27, 27, 27))
                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                       .addContainerGap()
                       .addComponent(jLabel1)
                       .addGap(16, 16, 16)
                       .addComponent(numdoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(layout.createSequentialGroup()
                       .addGap(41, 41, 41)
                       .addComponent(jLabel11))
                   .addComponent(jLabel3))
               .addGap(3, 3, 3)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel4)
                   .addComponent(codecli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(4, 4, 4)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel5)
                   .addComponent(NmClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(8, 8, 8)
               .addComponent(jLabel10)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel6)
                   .addComponent(CdArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(27, 27, 27)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel7)
                   .addComponent(NmArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(18, 18, 18)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel8)
                   .addComponent(TyArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(44, 44, 44)
               .addComponent(fichier)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGap(29, 29, 29)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(reset)
                   .addComponent(save)))
       );

       pack();
   }// </editor-fold>//GEN-END:initComponents

   private void fexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fexcelActionPerformed
     
   }//GEN-LAST:event_fexcelActionPerformed

   private void NmArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmArtActionPerformed
       // TODO add your handling code here:
   }//GEN-LAST:event_NmArtActionPerformed

   private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
               // TODO add your handling code here:
     
   }//GEN-LAST:event_saveActionPerformed

   private void ParcouActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ParcouActionPerformed
      //System.out.println("ceci est un test");
   }//GEN-LAST:event_ParcouActionPerformed

   /**
    * @param args the command line arguments
    */
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JComboBox CdArt;
   private javax.swing.JComboBox NmArt;
   private javax.swing.JComboBox NmClient;
   private javax.swing.JButton Parcou;
   private javax.swing.JComboBox TyArt;
   private javax.swing.JComboBox codecli;
   private com.toedter.calendar.JDateChooser date;
   private javax.swing.JTextField fexcel;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel10;
   private javax.swing.JLabel jLabel11;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JLabel jLabel7;
   private javax.swing.JLabel jLabel8;
   private javax.swing.JLabel fichier;
   private javax.swing.JTextField numdoc;
   private javax.swing.JButton reset;
   private javax.swing.JButton save;
   // End of variables declaration//GEN-END:variables
}