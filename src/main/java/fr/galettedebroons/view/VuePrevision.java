package fr.galettedebroons.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import fr.galettedebroons.model.ModificationDonnees;
import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.main.Main;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NouveauClient.java
 *
 * Created on 16 mars 2015, 16:18:47
 */

/**
 *
 * @author poher
 */
public class VuePrevision extends javax.swing.JPanel implements TableCellRenderer {
	
	private Main main_;

    /** Creates new form NouveauClient */
    public VuePrevision(Main main) {
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

    	//jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        
        rd = new RecuperationDonnees(main_);
        md = new ModificationDonnees(main_);
        
        //recuperation tournee
        listeTournee = rd.recuperationTournee();
        jComboBox1 = new JComboBox(listeTournee);
        jComboBox1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		jComboBox1ActionPerformed(evt);
			}
        });

        
        /*listClient = rd.recuperationProfil();
		jComboBox1 = new JComboBox(listClient);*/
        
        

        //jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selectionner une tournée"}));

        reinitialiserJTable1();
        
        reinitialiserJTable2();
        
       /* jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String [] {
                    null, null, null, null, null, null
                }
            ));*/
        jTable1.setAutoCreateColumnsFromModel(false);
        jTable2.setAutoCreateColumnsFromModel(false);
        
        jTable2.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
               System.out.println("COUCOUUUUUU");
            }
          });
        
        jTable2.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

			@Override
			public void editingStopped(ChangeEvent arg0) {
				java.util.Date data = null;
				int ligneModif = jTable2.getSelectedRow();
				int colonneModif = jTable2.getSelectedColumn();
				int nouvellePrev = 0;
				nouvellePrev = Integer.valueOf((String) jTable2.getModel().getValueAt(ligneModif, colonneModif));
				String produit = jTable2.getColumnName(colonneModif);
				//int profil = (int) jTable2.getModel().getValueAt(ligneModif, 0);
				
				String produit1 = listeProduit[colonneModif-1];
				String profil1 = listProfilEditable[ligneModif+1];
				System.out.println("PROFIL 111 : " +profil1);
				System.out.println("PRODUIT 111 : " +produit1);
				
				data = jDateChooser1.getDate();
				//System.out.println("mon profil : " +profil);
				System.out.println("ma row : " +ligneModif + 
						"ma colonne : " +colonneModif);
				System.out.println(jTable2.getModel().getValueAt(ligneModif, colonneModif));
				// enregistrement de la nouvelle valeur :
				md.updateMargeLivraisonManuelle(data, nouvellePrev, produit1, profil1);
			}
        });
        
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Prévision");
        
        jLabel2.setText("Tournée");

        jLabel3.setText("Profil");

        jLabel4.setText("Date");
        
        jLabel5.setText("Prévision + Marge de reprise");

        /*jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));*/
        jScrollPane2.setViewportView(jTable2);

        jButton1.setText("Prévision");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		jButton1ActionPerformed(evt);
			}
        });
        
        jButton2.setText("Qté Totale produit");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
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
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jLabel1))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>

    /**
     * Action clic bouton sur la combobox des tournee
     * @param evt
     */
    @SuppressWarnings("unchecked")
	private void jComboBox1ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
    	String profil = "";
    	profil = (String) jComboBox1.getSelectedItem();
    	System.out.println("jComboBox1.getSelectedIndex()" +jComboBox1.getSelectedItem());
    	listClient = rd.recuperationProfilTournee(profil);
    	jComboBox2.removeAllItems();
    	DefaultComboBoxModel model = new DefaultComboBoxModel(listClient);
    	jComboBox2.setModel(model);
    	jComboBox2.addItem("Tous");
    	
	}
    
    /**
     * action sur le bouton prevision pour voir afficher nos previsions :
     * dans la table 1 seulement les prévisions en fonction des clients et des produits
     * dans la table 2 prévisions + marge de livraison en fonction des clients et des produits
     * @param evt
     */
    private void jButton1ActionPerformed(ActionEvent evt) {
    	// remise à zéro de mes 2 tables
    	reinitialiserJTable1();
    	reinitialiserJTable2();
    	jTable1.repaint();
    	jTable2.repaint();
    	
    	String nomTournee = "";
    	String nomProfil = "";
    	String date = "";
    	java.util.Date data = null;
    	
    	nomTournee = (String) jComboBox1.getSelectedItem();
    	System.out.println("nomTournee : " +nomTournee);
    	nomProfil = (String) jComboBox2.getSelectedItem();
    	System.out.println("nomProfil : " +nomProfil);
    	System.out.println(String.valueOf(jDateChooser1.getDate()));
    	
    	data = jDateChooser1.getDate();
    	System.out.println("data :  " +data);
    	date = String.valueOf(jDateChooser1.getDate());
    	System.out.println("ma date : " +date);
    	
    	//si un profil est selectionné en particulier
    	if(!nomProfil.toString().contains("Tous") && data != null){
    		String profil = "";
    		String monProfil = "";
    		String monProduit = "";
    		int maQuantité = 0;
    		
        	profil = (String) jComboBox2.getSelectedItem();
        	List<Object[]> maliste = rd.recupuniqueProfil(data, profil);
        	System.out.println("la taille de ma liste" +maliste.size());
        	
        	jTable1.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Profil");
    		jTable1.repaint();
    		jTable1.getTableHeader().repaint();
    		//jTable1.getModel().setValueAt(monProfil, 0, 0);
    		
    		jTable2.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Profil");
    		jTable2.repaint();
    		jTable2.getTableHeader().repaint();
    		
    		
    		int indiceTitre = 1;
    		int colonneProduitQte = 1;
    		// table 2
    		int indiceTitreTable2 = 1;
    		int colonneProduitQteTable2 = 1;
    		
    		int ligneProduitQte = 0;
    		for (Object[] p : maliste){
    			System.out.println("Mon profil " +p[1]);
    			monProfil = (String) p[1];
    			System.out.println("Mon produit " +p[2]);
    			monProduit = (String) p[2];
    			System.out.println("Ma quantité " +p[0]);
    			maQuantité = (int) p[0];
    			
    			jTable1.getModel().setValueAt(monProfil, 0, 0);
    			jTable1.getTableHeader().getColumnModel().getColumn(indiceTitre).setHeaderValue(monProduit);
    			indiceTitre++;
    			jTable1.getModel().setValueAt(maQuantité, ligneProduitQte, colonneProduitQte);
    			colonneProduitQte++;
    			
    			// jtable2 :
    			jTable2.getModel().setValueAt(monProfil, 0, 0);
    			jTable2.getTableHeader().getColumnModel().getColumn(indiceTitre).setHeaderValue(monProduit);
    			indiceTitreTable2++;
    			jTable2.getModel().setValueAt(maQuantité, ligneProduitQte, colonneProduitQte);
    			colonneProduitQteTable2++;
    		}
    	}
    	
    	// si on veut voir les prévisions pour TOUS les profils de la tournee
    	if(nomTournee != "" && nomProfil.toString().contains("Tous") && data != null){
    		//jTable2.getModel().setValueAt(Color.RED, 1, 1);
    		//jTable2.setValueAt(Color.red, 1, 1);
    		
    		
    		/**
    		 * Change la couleur de toute la table :
    		 */
    		Component comp = jTable2.getComponentAt(1, 1);
    		System.out.println("mon composent : " +comp);
    		//Component comp = (Component) jTable2.getModel().getValueAt(1,1);
    		Color clr = new Color(255, 226, 198);
    		comp.setBackground(clr);
    		jTable2.repaint();
    		
    		List<Object[]> maliste = rd.recupPrevTournee(nomTournee, data);
    		String monProfilPrecedent = "";
    		String monProfil = "";
    		String monProduit = "";
    		int maQuantite = 0;
    		String[] mesTitre = new String[100];
    		int tabTitre = 0;
    		int ligne = 0;
    		int colonne = 0;
    		int ligneprofil = 0;
    		int colonneprofil = 0;
    		
    		jTable1.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Profil");
    		jTable1.repaint();
    		
    		jTable1.getTableHeader().repaint();
    		
    		// table 2
    		jTable2.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Profil");
    		jTable2.repaint();
    		jTable2.getTableHeader().repaint();
    		int k = 1;
    		
    		// insère les produits dans ma liste !!!
    		this.listeProduit = new String[maliste.size()];
    		this.listProfilEditable = new String[maliste.size()];
    		int indiceListeProduit = 0;
    		int indiceProfilEditable = 0;
    		
    		for (Object[] p : maliste){
    			System.out.println("Mon profil " +p[1]);
    			monProfil = (String) p[1];
    			System.out.println("Mon produit " +p[2]);
    			monProduit = (String) p[2];
    			System.out.println("Ma quantité " +p[0]);
    			maQuantite = (int) p[0];
    			
    			// si meme profil alors ajout du produit après ancient produit ajouté
    			System.out.println("la valeur de mon profil : " +monProfilPrecedent);
    			System.out.println("la valeur de mon profil précédent : " +colonne);
    			if(monProfil.equals(monProfilPrecedent) || (jTable1.getModel().getValueAt(0, 0) == null)){
    				System.out.println("je rentre bien dans ma condition");
    				jTable1.getModel().setValueAt(monProfil, ligneprofil, colonneprofil);
    				jTable1.getTableHeader().getColumnModel().getColumn(k).setHeaderValue(monProduit);
    				
    				
    				// table 2
    				jTable2.getModel().setValueAt(monProfil, ligneprofil, colonneprofil);
    				listProfilEditable[indiceProfilEditable] = monProfil;
    				indiceProfilEditable++;
    				jTable2.getTableHeader().getColumnModel().getColumn(k).setHeaderValue(monProduit);
    				listeProduit[indiceListeProduit] = monProduit;
    				indiceListeProduit++;
    				
    				mesTitre[tabTitre] = monProduit;
    				tabTitre++;
    				colonne++;
    				System.out.println("ma quantité : " +maQuantite);
    				
    				// maQuantite + 25% :
    				// maQuantite = maQuantite + 25*maQuantite/100
    				jTable1.getModel().setValueAt(maQuantite, ligne, colonne);
    				// table 2
    				jTable2.getModel().setValueAt(maQuantite, ligne, colonne);
    				monProfilPrecedent = monProfil;
    				
    				System.out.println("la valeur de ma colonne : " +colonne);
    				System.out.println("la valeur de ma ligne : " +ligne);
    				k++;
    			}
    			if(!monProfil.equals(monProfilPrecedent)){
    				System.out.println("mon profil différent : " +monProfil);
    				ligneprofil++;
    				jTable1.getModel().setValueAt(monProfil, ligneprofil, colonneprofil);
    				// table 2
    				jTable2.getModel().setValueAt(monProfil, ligneprofil, colonneprofil);
    				listProfilEditable[indiceProfilEditable] = monProfil;
    				indiceProfilEditable++;
    				
    				
    				// si mon produit est différent d'un produit déjà existant alors on avance
    				k = 0;
    				System.out.println("c'est quoiii ? " +mesTitre[0]);
    				while(mesTitre[k] != null){
    					System.out.println("mamaaaaa");
    					if(monProduit.equals(mesTitre[k])){
    						System.out.println("monproduit : " +mesTitre[k] + "mon indice : " +k);
    						System.out.println("ligne profil : " +ligneprofil);
    						System.out.println("colonne profil : " +colonneprofil);
    						jTable1.getModel().setValueAt(maQuantite, ligneprofil, k+1);
    						// table 2
    						// maQuantite = maQuantite + 25%
    						jTable2.getModel().setValueAt(maQuantite, ligneprofil, k+1);
    						
    						ligne++;
    					}
    					k++;
    				}
    				
    				jTable1.getTableHeader().getColumnModel().getColumn(k+1).setHeaderValue(monProduit);
    				// table 2
    				jTable2.getTableHeader().getColumnModel().getColumn(k+1).setHeaderValue(monProduit);
    				listeProduit[indiceListeProduit] = monProduit;
    				indiceListeProduit++;
    				monProfilPrecedent = monProfil;
    				k++;
    			}
    		}
    	}
   	}
    
    
    private void jButton2ActionPerformed(ActionEvent evt) {
    	QteALivrerTotal qteaLivrer = new fr.galettedebroons.view.QteALivrerTotal(main_);
    	JFrame fenetre = new JFrame();
    	fenetre.setSize(500, 500);
    	fenetre.add(qteaLivrer);
    	fenetre.setVisible(true);
    }
    
    /**
     * reinitialisation de l'affichage de la table
     */
    public void reinitialiserJTable1(){
    	jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String [] {
                    null, null, null, null, null, null
                }
            ));
    }
    
    public void reinitialiserJTable2(){
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String [] {
                    null, null, null, null, null, null
                }
            ));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;

    RecuperationDonnees rd;
    ModificationDonnees md;
    String[] listClient;
    String[] listeTournee;
    String[] listeProduit;
    String[] listProfilEditable;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		Component comp = getTableCellRendererComponent(
		        table, value, isSelected, hasFocus, row, column);
		// TODO Auto-generated method stub
		return null;
	}
}
