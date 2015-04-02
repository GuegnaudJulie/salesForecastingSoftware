package fr.galettedebroons.view;

import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.RecuperationDonnees;

/**
 * Formulaire de creation de nouveau client
 * @author poher
 *
 */
public class NouveauClient extends java.awt.Panel {

	    /** Creates new form NouveauClient */
	    public NouveauClient(Main main, VueGlobalNvClient vue, String cc, String nc, JComboBox tournee, JComboBox gamme) {
	    	main_ = main;
	    	rd_ = new RecuperationDonnees(main);
	    	vue_ = vue;
	        initComponents(cc, nc, tournee, gamme);
	    }
	    
	    /** This method is called from within the constructor to
	     * initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is
	     * always regenerated by the Form Editor.
	     */
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">
	    private void initComponents(String cc, String nc, JComboBox tournee, JComboBox gamme) {
				
		        jLabel1 = new javax.swing.JLabel();
		        jTextField1 = new javax.swing.JTextField();
		        jLabel2 = new javax.swing.JLabel();
		        jTextField2 = new javax.swing.JTextField();
		        jLabel3 = new javax.swing.JLabel();
		        jComboBox1 = new javax.swing.JComboBox();
		        jButton1 = new javax.swing.JButton();
		        jLabel4 = new javax.swing.JLabel();
		        jComboBox2 = new javax.swing.JComboBox();
		        jButton2 = new javax.swing.JButton();
		        
		        fenetre = new JFrame();
	
		        jLabel1.setText("Nom Client");
	
		        jTextField1.setText(nc);
		        if (nc != null)
		        	jTextField1.setEditable(false);
		        
		        jLabel2.setText("code Client");
	
		        jTextField2.setText(cc);
		        if (cc != null)
		        	jTextField2.setEditable(false);
		        
		        jLabel3.setText("Tournée");
	
		        jComboBox1 = tournee;
	
		        // bouton tournee
		        jButton1.setText("+");
		        jButton1.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		                jButton1ActionPerformed(evt);
		            }
		        });
	
		        jLabel4.setText("Gamme");
	
		        jComboBox2 = gamme;
	
		        // bouton gamme
		        jButton2.setText("+");
		        jButton2.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		                jButton2ActionPerformed(evt);
		            }
		        });
		        
	
		        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		        this.setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(jLabel1)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(26, 26, 26)
		                        .addComponent(jLabel2)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(jLabel3)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(jButton1)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(jLabel4)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(jButton2)))
		                .addContainerGap(62, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(30, 30, 30)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jLabel1)
		                    .addComponent(jTextField1,  javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jLabel2)
		                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(18, 18, 18)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jLabel3)
		                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jButton1)
		                    .addComponent(jLabel4)
		                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jButton2))
		                .addContainerGap(50, Short.MAX_VALUE))
		        );
	
		        jLabel3.getAccessibleContext().setAccessibleName("labelTournee");
		    }// </editor-fold>
	    

	    public javax.swing.JComboBox getjComboBox1() {
			return jComboBox1;
		}

		public void setjComboBox1(javax.swing.JComboBox jComboBox1) {
			this.jComboBox1 = jComboBox1;
		}

		public javax.swing.JComboBox getjComboBox2() {
			return jComboBox2;
		}

		public void setjComboBox2(javax.swing.JComboBox jComboBox2) {
			this.jComboBox2 = jComboBox2;
		}

		public JFrame getJFrame(){
	    	return fenetre;
	    }
	    
	    public void fermetureGamme(){
	    	vue_.listGamme();
		}
	    
	    public void fermetureTournee(){
	    	vue_.listTournee();
	    }
	    
	    public void majComboTournee(javax.swing.JComboBox combo, Object select){
	    	jComboBox1.removeAllItems();
	    	jComboBox1.setModel(combo.getModel());
	    	
	    	jComboBox1.setSelectedItem(select);
	    	
	    }
	   
	    
	    public void majComboGamme(javax.swing.JComboBox combo, Object select){
	    	jComboBox2.removeAllItems();
	    	jComboBox2.setModel(combo.getModel());
	    	
	    	jComboBox2.setSelectedItem(select);
	    }
	    
	    public Object getSelectTournee() {
	    	return jComboBox1.getSelectedItem();
		}
	    
	    public Object getSelectGamme() {
	    	return jComboBox2.getSelectedItem();
		}
	    
	    private void jButton1ActionPerformed(ActionEvent evt) {
	    	fenetre = new ajoutTournee(main_, this);
	    	fenetre.setLocationRelativeTo(null);
	    	fenetre.setVisible(true);	    	
		}
	    
	    private void jButton2ActionPerformed(ActionEvent evt) {
	    	fenetre = new ajoutGamme(main_, null, this);
	    	fenetre.setLocationRelativeTo(null);
	    	fenetre.setVisible(true);
		}
	    
	    public javax.swing.JTextField getTextFieldNC(){
	    	return jTextField1;
	    }
	    
	    public javax.swing.JTextField getTextFieldCC(){
	    	return jTextField2;
	    }
	    
	    // Variables declaration
	    private javax.swing.JButton jButton1;
	    private javax.swing.JButton jButton2;
	    private javax.swing.JComboBox jComboBox1;
	    private javax.swing.JComboBox jComboBox2;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JTextField jTextField2;
	    private JFrame fenetre;
	    private Main main_;
	    private RecuperationDonnees rd_;
	    private VueGlobalNvClient vue_;
	    // End of variables declaration
	    
	    
		public void MAJComboG() {
			this.jComboBox1.repaint();
			this.jComboBox1.validate();
		}
		
}
