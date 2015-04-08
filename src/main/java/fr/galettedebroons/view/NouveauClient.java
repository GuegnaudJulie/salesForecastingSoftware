package fr.galettedebroons.view;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;

import fr.galettedebroons.main.Main;
import fr.galettedebroons.model.RecuperationDonnees;

/**
 * Formulaire de creation de nouveau client
 * @author poher
 *
 */
public class NouveauClient extends java.awt.Panel {

	    /** Creates new form NouveauClient */
	    public NouveauClient(Main main, VueGlobalNvClient vue, String cc, String nc, JList tournee) {
	    	main_ = main;
	    	rd_ = new RecuperationDonnees(main);
	    	vue_ = vue;
	        initComponents(cc, nc, tournee);
	    }
	    
	    /** This method is called from within the constructor to
	     * initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is
	     * always regenerated by the Form Editor.
	     */
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">
	    private void initComponents(String cc, String nc, JList tournee) {
				
		        jLabel1 = new javax.swing.JLabel();
		        jTextField1 = new javax.swing.JTextField();
		        jLabel2 = new javax.swing.JLabel();
		        jTextField2 = new javax.swing.JTextField();
		        jLabel3 = new javax.swing.JLabel();
		        jList1 = new javax.swing.JList();
		        jButton1 = new javax.swing.JButton();
		        
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
	
		        jList1 = tournee;
	
		        // bouton tournee
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
		                        .addComponent(jList1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(jButton1)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
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
		                    .addComponent(jList1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jButton1))
		                .addContainerGap(50, Short.MAX_VALUE))
		        );
	
		        jLabel3.getAccessibleContext().setAccessibleName("labelTournee");
		    }// </editor-fold>
	    

	    public javax.swing.JList getjComboBox1() {
			return jList1;
		}

		public void setjComboBox1(javax.swing.JList jComboBox1) {
			this.jList1 = jComboBox1;
		}

		public JFrame getJFrame(){
	    	return fenetre;
	    }
	    
	    public void fermetureTournee(){
	    	vue_.listTournee();
	    }
	    
	    public void majComboTournee(String[] list, Object select){
	    	jList1.removeAll();
	    	jList1.setListData(list);
	    	MAJComboG();
	    }
	    
	    public List<Object> getSelectTournee() {
	    	return jList1.getSelectedValuesList();
		}
	    
	    private void jButton1ActionPerformed(ActionEvent evt) {
	    	fenetre = new ajoutTournee(main_, this);
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
	    private javax.swing.JList jList1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JTextField jTextField2;
	    private JFrame fenetre;
	    private Main main_;
	    private RecuperationDonnees rd_;
	    private VueGlobalNvClient vue_;
	    // End of variables declaration
	    
	    
		public void MAJComboG() {
			this.jList1.repaint();
			this.jList1.validate();
		}
		
}
