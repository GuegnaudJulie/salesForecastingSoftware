package fr.galettedebroons.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.domain.Tournee;
import fr.galettedebroons.model.ModificationDonnees;
import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.model.selectBase.RecupProduit;
import fr.galettedebroons.model.selectBase.RecupTournee;
import fr.galettedebroons.main.Main;

/*
 * NouveauClient.java
 *
 * Created on 16 mars 2015, 16:18:47
 */

/**
 *
 * @author poher
 */
public class VuePrevision2 extends javax.swing.JPanel {
	
    public VuePrevision2(Main main) {
    	this.main_ = main;
    	rt_ = new RecupTournee(main_);
    	rp_ = new RecupProduit(main_);
    	rd_ = new RecuperationDonnees(main_);
    	md_ = new ModificationDonnees(main_);
        initComponents();
    }
    
    private void initComponents() {
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
        
        //recuperation tournee
        listeTournee_ = rt_.listTournee();
        String[] tournee = new String[listeTournee_.size()+1];
        tournee[0] = "Aucune sélection";
        int i = 1;
        for (Tournee t : listeTournee_){
        	tournee[i] = t.getNom();
        	i++;
        }
        
        jComboBox1 = new JComboBox(tournee);
        jComboBox1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		actionSelectionTournee(evt);
			}
        });
        
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selectionner une tournée"}));
        jComboBox2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		actionSelectionProfil(evt);
			}
        });
        
        initialiserJTable1();
        initialiserJTable2();
        
        //jTable1.setAutoCreateColumnsFromModel(false);
        //jTable2.setAutoCreateColumnsFromModel(false);
        
        jTable2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTable2.setAutoCreateColumnsFromModel(true);
        jTable2.setAutoCreateRowSorter(true);
        jTable2.setAutoResizeMode(HEIGHT);
        jTable2.setAutoscrolls(true);
        //jTable2.setPreferredSize(new Dimension(700, 277));
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
				Date date = null;
				int ligneModif = jTable2.getSelectedRow();
				int colonneModif = jTable2.getSelectedColumn();
				int nouvellePrev = 0;
				nouvellePrev = Integer.valueOf((String) jTable2.getModel().getValueAt(ligneModif, colonneModif));
				String produit = jTable2.getColumnName(colonneModif);
				//int profil = (int) jTable2.getModel().getValueAt(ligneModif, 0);
				
				String produit1 = titre_[colonneModif];
				String profil1 = code_client[ligneModif];
				System.out.println("PROFIL 111 : " +profil1);
				System.out.println("PRODUIT 111 : " +produit1);
				
				date = new Date(jDateChooser1.getDate().getTime());
				//System.out.println("mon profil : " +profil);
				System.out.println("ma row : " +ligneModif + 
						"ma colonne : " +colonneModif);
				System.out.println(jTable2.getModel().getValueAt(ligneModif, colonneModif));
				
				// enregistrement de la nouvelle valeur :
				md_.updateMargeLivraisonManuelle(date, nouvellePrev, produit1, profil1);
			}
        });
        
        
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Prévision");
        
        jLabel2.setText("Tournée");

        jLabel3.setText("Profil");

        jLabel4.setText("Date");
        
        jLabel5.setText("Prévision + Marge de reprise/Marge de rupture");

        jScrollPane2.setViewportView(jTable2);

        jButton1.setText("Prévision");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		affichagePrevision(evt);
			}
        });
        
        jButton2.setText("Qté Totale produit");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		affichageTotaux(evt);
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
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>
    
    private String selectionTournee(){
    	return jComboBox1.getSelectedItem().toString();
    }
    
    private String selectionProfil(){
    	return jComboBox2.getSelectedItem().toString();
    }

    private Date selectionDate(){
    	Date d = null;
    	try{
    		d = new Date(jDateChooser1.getDate().getTime());
    	} catch (Exception e){
    		System.out.println("Aucune date choisi");
    	}
    	
    	return d;
    }
    
    private void actionSelectionTournee(ActionEvent evt){
    	String tournee = selectionTournee();
    	
    	if (!tournee.contains("Aucune sélection")){
	    	listClient_ = rt_.recuperationProfilTournee(tournee);
	    	
	    	code_client = new String[listClient_.size()+1];
	        code_client[0] = "Tous";
	        int indice = 1;
	        for (Profil cl : listClient_){
	            code_client[indice] = cl.getCode_client();
	            indice++;
	        }
	        
	    	jComboBox2.removeAllItems();
	    	DefaultComboBoxModel model = new DefaultComboBoxModel(code_client);
	    	jComboBox2.setModel(model);
    	}
    }
    
    private void actionSelectionProfil(ActionEvent evt) {
    	try{
	    	String profil = selectionProfil();
			if (profil.contains("Tous"))
				listClient_ = rt_.recuperationProfilTournee(selectionTournee());
			else{
				listClient_ = new ArrayList<Profil>();
				listClient_.add(rd_.recupProfil(profil));
			}
    	} catch (Exception e) {}
	}
    
    private void affichagePrevision(ActionEvent evt){
    	if (selectionDate() != null){
	    	initialiserJTable1();
	    	initialiserJTable2();
	    	
	    	jTable2.removeAll();
	    	
	    	//Definition de la taille du tableau
	    	final int[] taille = definitionTailleTableau();
	    	data_ = new Object[taille[0]][taille[1]];
	    	titre_ = new String[taille[1]];
	    	
	    	if (selectionTournee().contains("Aucune sélection")){
	    		remplissageEnteteMultiple(titre_);
	    		remplissageTableauMultiple(data_);
	    	}
	    	else{
	    		remplissageEnteteSimple(titre_);
	    		remplissageTableauSimple(data_);
	    	}
	    	
	    	jTable2.setModel(new javax.swing.table.DefaultTableModel(data_, titre_) {
				Class[] types_ = classTable(taille[1]);
	
	            public Class getColumnClass(int columnIndex) {
	                return types_ [columnIndex];
	            }
	        });
	    	
	    	jTable2.setModel(new javax.swing.table.DefaultTableModel(data_, titre_));
	    	
	    	jTable2.repaint();
	    	jTable2.validate();
    	}
    }
    
    private int[] definitionTailleTableau(){
    	int horizonProduit = 0;
    	int vertiProfil = 0;
    	
    	//recuperation du nombre de profil
    	if(selectionTournee().contains("Aucune sélection")){
    		int i = 0;
    		
    		List<Tournee> listTournee = rt_.listTournee();
    		int nbProfil = 0;
    		List<Profil> listProfil = null;
    		
    		for (Tournee t : listTournee){
    			listProfil = t.getProfil_tournee();
    			nbProfil += listProfil.size();
    		}
    		vertiProfil = nbProfil + listTournee.size();
    		code_client = new String[vertiProfil];
    		
    		for (Tournee t : listTournee){
    			int indice = 0;
    			for (Profil p : listProfil){
    				if(indice == 0){
    					code_client[i] = "";
    					indice ++;
    					i++;
    				}
    				code_client[i] = p.getCode_client();
    				i++;
    			}
    		}
    		
    		
    	}
    	else if(selectionProfil().contains("Tous"))
    		vertiProfil = listClient_.size()+1;
    	else
    		vertiProfil = 1;
    	
    	//recuperation des produits
    	listProduit_ = rp_.listTousProduit();
    	if(selectionTournee().contains("Aucune sélection"))
    		horizonProduit = listProduit_.size() + 3;
    	else
    		horizonProduit = listProduit_.size() + 2;
    	
    	return (new int[] {vertiProfil, horizonProduit});
    }
    
    private void remplissageEnteteSimple(String[] titre) {
    	titre[0] = "";
    	int indice = 1;
    	for (Produit produit : listProduit_){
    		titre[indice] = produit.getCode_produit();
    		indice++;
    	}
    	titre[indice] = "Total";
	}

	private void remplissageTableauSimple(Object[][] data) {
    	int ligne = 0;
    	int colonne = 0;
    	
    	System.out.println("la liste de client est de taille : " + listClient_.size());
    	
    	for (Profil profil : listClient_){
    		colonne = 0;
    		data[ligne][colonne] = profil.getCode_client();
    		colonne ++;
    		
    		//récupération de la liste des previsions
    		List<Object[]> maliste = rd_.recupuniqueProfil(selectionDate(), profil.getCode_client());
    		
    		//On parcourt les colonnes et on insert
    		for (int i = 1; i<titre_.length-1 ; i++){
    			boolean trouve = false;
    			for (Object[] p : maliste){
    				if(titre_[i].equals(p[2])){
    					data[ligne][colonne] = p[0]; // +margelivraison
    					trouve = true;
    				}
    			}
    			if (!trouve)
    				data[ligne][colonne] = 0;
    			
    			colonne ++;
    		}
    		
    		ligne ++;
    	}
	}

	private void remplissageEnteteMultiple(String[] titre) {
		titre[0] = "Tournée";
		titre[1] = "Profil";
    	int indice = 2;
    	for (Produit produit : listProduit_){
    		titre[indice] = produit.getCode_produit();
    		indice++;
    	}
    	titre[indice] = "Total";
	}

	private void remplissageTableauMultiple(Object[][] data) {
		int ligne = 0;
    	int colonne = 0;
    	
    	for (Tournee t : listeTournee_){
    		colonne = 0;
    		data[ligne][colonne] = t.getNom();
    		colonne ++;
    		ligne ++;
    		
    		//Recuperation des profils de la tournee
    		List<Profil> lp = rt_.recuperationProfilTournee(t.getNom());
	    	for (Profil profil : lp){
	    		colonne = 1;
	    		data[ligne][colonne] = profil.getCode_client();
	    		colonne ++;
	    		
	    		//récupération de la liste des previsions
	    		List<Object[]> maliste = rd_.recupuniqueProfil(selectionDate(), profil.getCode_client());
	    		
	    		//On parcourt les colonnes et on insert
	    		for (int i = 1; i<titre_.length-1 ; i++){
	    			boolean trouve = false;
	    			for (Object[] p : maliste){
	    				if(titre_[i].equals(p[2])){
	    					data[ligne][colonne] = p[0]; // +margelivraison
	    					trouve = true;
	    				}
	    			}
	    			if (!trouve)
	    				data[ligne][colonne] = 0;
	    			
	    			colonne ++;
	    		}
	    		
	    		ligne ++;
	    	}
    	}
	}
	
	private Class[] classTable(int taille) {
		Class[] cl = new Class[taille];
		if (selectionTournee().contains("Aucune sélection")){
			for (int i = 0; i<taille; i++){
				if (i < 2)
					cl[i] = String.class;
				else
					cl[i] = Integer.class;
			}
		}
		else{
			for (int i = 0; i<taille; i++){
				if (i == 0)
					cl[i] = String.class;
				else
					cl[i] = Integer.class;
			}
		}
		
		return cl;
	}
    
	/**
     * reinitialisation de l'affichage de la table 1
     */
    private void initialiserJTable1(){
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
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String [] {
                    null, null, null, null, null, null
                }
            ));
    }
    
    /**
     * reinitialisation de l'affichage de la table 2
     */
    private void initialiserJTable2(){
    	jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Tournée", "Profil", "Produit", "Total"
                }
            ));
    }
    
	/**
     * Affichage d'un panel avec la quantité totale des produits
     * @param evt
     */
    private void affichageTotaux(ActionEvent evt) {
    	QteALivrerTotal qteaLivrer = new fr.galettedebroons.view.QteALivrerTotal(main_);
    	JFrame fenetre = new JFrame();
    	fenetre.setSize(500, 500);
    	fenetre.add(qteaLivrer);
    	fenetre.setVisible(true);
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

	private Main main_;
    private RecupTournee rt_;
    private RecupProduit rp_;
    private RecuperationDonnees rd_;
    private ModificationDonnees md_;
    private List<Tournee> listeTournee_;
    private List<Profil> listClient_;
    private List<Produit>  listProduit_;
    private String[] code_prod;
    private String[] code_client;
    private String[] titre_;
    private Object[][] data_;
    private Class[] types_;
    /*private String[] listProfilEditable;
    
    private String[] title;*/
}
