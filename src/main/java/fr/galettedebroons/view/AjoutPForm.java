package fr.galettedebroons.view;

/**
*
* @author Oumoul
*/
public class AjoutPForm extends javax.swing.JFrame {

	private static final long serialVersionUID = 7360078324094620060L;
	
	/**
    * Creates new form ajoutPForm
    */
   public AjoutPForm(String code_produit) {
       initComponents(code_produit);
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents(String code_produit) {

       jLabel1 = new javax.swing.JLabel();
       jLabel2 = new javax.swing.JLabel();
       jLabel3 = new javax.swing.JLabel();
       jLabel4 = new javax.swing.JLabel();
       nmprod = new javax.swing.JTextField();
       typprod = new javax.swing.JTextField();
       durprod = new javax.swing.JTextField();
       save = new javax.swing.JButton();
       drop = new javax.swing.JButton();

       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

       jLabel1.setText("AJOUT PRODUIT");

       jLabel2.setText("Nom Produit");

       jLabel3.setText("Type Produit");

       jLabel4.setText("Dur�e de validit�");

       save.setText("Enregistrer");

       drop.setText("Annuler");
       
       nmprod.setText(code_produit);

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addGap(170, 170, 170)
               .addComponent(jLabel1)
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
           .addGroup(layout.createSequentialGroup()
               .addGap(37, 37, 37)
               .addComponent(save)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
               .addComponent(drop)
               .addGap(77, 77, 77))
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(jLabel2)
                   .addComponent(jLabel3)
                   .addComponent(jLabel4))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                   .addComponent(durprod, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                       .addComponent(typprod, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                       .addComponent(nmprod)))
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jLabel1)
               .addGap(26, 26, 26)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel2)
                   .addComponent(nmprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(30, 30, 30)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel3)
                   .addComponent(typprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(30, 30, 30)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel4)
                   .addComponent(durprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(46, 46, 46)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(save)
                   .addComponent(drop))
               .addContainerGap(60, Short.MAX_VALUE))
       );

       pack();
   }// </editor-fold>//GEN-END:initComponents

   /**
    * @param args the command line arguments
    */
  

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton drop;
   private javax.swing.JTextField durprod;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JTextField nmprod;
   private javax.swing.JButton save;
   private javax.swing.JTextField typprod;
   // End of variables declaration//GEN-END:variables
}