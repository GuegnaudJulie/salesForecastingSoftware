package fr.galettedebroons.view;

import java.util.List;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.DefaultCellEditor;

import fr.galettedebroons.controller.LectureFichier;
import fr.galettedebroons.model.RangerDonneeTemporaire;
import fr.galettedebroons.model.RecuperationDonnees;
import fr.galettedebroons.model.TraitementDonneesTemporaire;
import fr.galettedebroons.test.Main;

/**
 * @author  Julie Guegnaud
 * @author  Oumoul Sy
 * @since   18/03/2015
 */
public class PanelEdition extends javax.swing.JPanel {
	
    public PanelEdition(Main main) {
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

    	rd = new RecuperationDonnees(main_);
    	nouveauClient = null;
    	nouveauProduit = null;
        panelEdition = new javax.swing.JPanel();
        labelTitre = new javax.swing.JLabel();
        messageErreur = new javax.swing.JLabel();
        labelInfo = new javax.swing.JLabel();
        valFichier = new javax.swing.JTextField();
        boutonParcourir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelBonLivraison = new javax.swing.JLabel();
        valBonLivraison = new javax.swing.JTextField();
        labelDate = new javax.swing.JLabel();
        valDate = new com.toedter.calendar.JDateChooser();
        labelClient = new javax.swing.JLabel();
        valListeClient = new javax.swing.JComboBox();
        boutonAjoutClient = new javax.swing.JButton();
        valNomClient = new javax.swing.JLabel();
        scrollLivraison = new javax.swing.JScrollPane();
        valListeProduit = new javax.swing.JComboBox();
        tableauLivraison = new javax.swing.JTable();
        boutonAjoutLigne = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        boutonEnregistrer = new javax.swing.JButton();
        boutonAnnuler = new javax.swing.JButton();
        boutonAjoutArticle = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1038, 580));

        panelEdition.setAutoscrolls(true);

        labelTitre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelTitre.setText("Ajouter les dernières livraisons");

        messageErreur.setForeground(new java.awt.Color(204, 0, 0));
        messageErreur.setText(" ");

        labelInfo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelInfo.setText("Renseigner les informations dans les champs ci-dessous ou indiquer le fichier");

        boutonParcourir.setText("Parcourir");
        boutonParcourir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonParcourirActionPerformed(evt);
            }
        });

        labelBonLivraison.setText("Bon de livraison");

        labelDate.setText("Date de livraison");

        labelClient.setText("Code client");
        
        listeClient();

        boutonAjoutClient.setBackground(new java.awt.Color(0, 153, 102));
        boutonAjoutClient.setText("+");
        boutonAjoutClient.setMargin(new java.awt.Insets(2, 4, 2, 4));
        boutonAjoutClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonAjoutClientActionPerformed(evt);
            }
        });

        valNomClient.setText(" ");

        listeProduit();
        
        tableauLivraison.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        data = new Object [][] {
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null}
        };

        title = new String[] {"Article", "Quantité livrée", "Quantité reprise"};
        tableauLivraison.setModel(new javax.swing.table.DefaultTableModel(data, title) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableauLivraison.getColumn("Article").setCellEditor(new DefaultCellEditor(valListeProduit));
        scrollLivraison.setViewportView(tableauLivraison);

        boutonAjoutLigne.setBackground(new java.awt.Color(0, 153, 102));
        boutonAjoutLigne.setText("+");
        boutonAjoutLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonAjoutLigneActionPerformed(evt);
            }
        });

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

        boutonAjoutArticle.setText("Ajouter un article");
        boutonAjoutArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonAjoutArticleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEditionLayout = new javax.swing.GroupLayout(panelEdition);
        panelEdition.setLayout(panelEditionLayout);
        panelEditionLayout.setHorizontalGroup(
            panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditionLayout.createSequentialGroup()
                .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditionLayout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(labelTitre)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelEditionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
            .addGroup(panelEditionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2))
            .addGroup(panelEditionLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditionLayout.createSequentialGroup()
                        .addComponent(labelInfo)
                        .addGap(158, 158, 158)
                        .addComponent(valFichier, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boutonParcourir))
                    .addComponent(messageErreur, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 91, Short.MAX_VALUE))
            .addGroup(panelEditionLayout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(boutonAjoutArticle)
                .addGap(18, 18, 18)
                .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditionLayout.createSequentialGroup()
                        .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelEditionLayout.createSequentialGroup()
                                .addComponent(labelBonLivraison, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(5, 5, 5))
                            .addGroup(panelEditionLayout.createSequentialGroup()
                                .addComponent(labelClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(27, 27, 27)))
                        .addGap(47, 47, 47)
                        .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valNomClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelEditionLayout.createSequentialGroup()
                                .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(valBonLivraison, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(valDate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelEditionLayout.createSequentialGroup()
                                        .addComponent(valListeClient, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(boutonAjoutClient, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 5, Short.MAX_VALUE)))
                        .addGap(445, 445, 445))
                    .addGroup(panelEditionLayout.createSequentialGroup()
                        .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEditionLayout.createSequentialGroup()
                                .addComponent(scrollLivraison, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boutonAjoutLigne))
                            .addGroup(panelEditionLayout.createSequentialGroup()
                                .addGap(368, 368, 368)
                                .addComponent(boutonEnregistrer)
                                .addGap(18, 18, 18)
                                .addComponent(boutonAnnuler)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelEditionLayout.setVerticalGroup(
            panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditionLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelTitre)
                .addGap(18, 18, 18)
                .addComponent(messageErreur, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInfo)
                    .addComponent(valFichier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boutonParcourir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBonLivraison)
                    .addComponent(valBonLivraison, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelClient)
                    .addComponent(valListeClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boutonAjoutClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valNomClient)
                .addGap(18, 18, 18)
                .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditionLayout.createSequentialGroup()
                        .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollLivraison, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boutonAjoutLigne, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEditionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boutonAnnuler)
                            .addComponent(boutonEnregistrer)))
                    .addComponent(boutonAjoutArticle))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEdition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEdition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        

    private void listeClient() {
    	client = rd.recuperationCodeClient();
        String[] code_client = new String[client.size()+1];
        code_client[0] = "selectionner";
        int indice = 1;
        for (Object[] cl : client){
            code_client[indice] = cl[0].toString();
            indice++;
        }
    	
    	valListeClient.removeAllItems();
        valListeClient.setModel(new javax.swing.DefaultComboBoxModel(code_client));
        valListeClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valListeClientActionPerformed(evt);
            }
        });
	}
    
    private void listeProduit(){
    	produit = rd.recuperationCodeProduit();
        String[] code_produit = new String[produit.size()+1];
        code_produit[0] = "";
        int indice = 1;
        for (Object[] pr : produit){
            code_produit[indice] = pr[0].toString() + " - " + pr[1];
            indice++;
        }
        
        valListeProduit.removeAllItems();
        valListeProduit.setModel(new javax.swing.DefaultComboBoxModel(code_produit));
    }

	private void boutonParcourirActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser choix = new JFileChooser();
        int retour=choix.showOpenDialog(getParent());
        if(retour==JFileChooser.APPROVE_OPTION){
            // chemin absolu du fichier choisi
            valFichier.setText(choix.getSelectedFile().getAbsolutePath());
        }
        // pas de fichier choisi
        else
            System.out.println("Aucun fichier choisi") ;
    }                                               

    private void valListeClientActionPerformed(java.awt.event.ActionEvent evt) {                                               
        String enseigne = "";
    	for (Object[] cl : client){
    		if (cl[0] == valListeClient.getSelectedItem().toString()){
    			enseigne = cl[1].toString();
    		}
    	}
    	valNomClient.setText(enseigne);
    }                                              

    private void boutonAjoutClientActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        nouveauClient = new VueGlobalNvClient(main_, this, null);
    }     
    
    void terminerAjoutClient(){
    	listeClient();
    	this.repaint();
    }

    private void boutonAjoutLigneActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // ajouter une ligne au tableau de livraison
    	int taille = tableauLivraison.getRowCount();
   
    	Object[][] dataNew = new Object[data.length+1][data[0].length];
    	for (int ligne = 0; ligne < data.length; ligne++){
    		for (int colonne = 0; colonne < data[ligne].length; colonne++){
    			dataNew[ligne][colonne] = tableauLivraison.getValueAt(ligne, colonne);
        	}
    	}
    	dataNew[data.length][0] = null;
		dataNew[data.length][1] = null;
		dataNew[data.length][2] = null;
    	data = dataNew;
    	taille = tableauLivraison.getRowCount();
    	
    	tableauLivraison.setModel(
        		new javax.swing.table.DefaultTableModel(data, title)
        		{
		            Class[] types = new Class [] {
		                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
		        };
		        
	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }
        });
    	
    	tableauLivraison.getColumn("Article").setCellEditor(new DefaultCellEditor(valListeProduit));
    }                                                

    private void boutonEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {
    	messageErreur.setText("");
    	
        //Traitement d'un fichier
    	if ( !valFichier.getText().isEmpty() && !valFichier.getText().equalsIgnoreCase("Fichier .xslx, .ods, .txt, .csv"))
        	traitementFichier();
    	
    	//Traitement manuel
        else{
        	String DebutMessErreur = "Erreur : Vous n'avez pas renseigner le/les champs : ";
        	String contenuErreur = verifManuelle();
        	
        	//Si un champs est vide
        	if (contenuErreur != "")
        		messageErreur.setText(DebutMessErreur + contenuErreur);
        	
        	//Si tous les champs sont rempli
        	else
        		traitementManuel();
        }
    	if (messageErreur.getText() == ""){
    		TraitementDonneesTemporaire tdt = new TraitementDonneesTemporaire(main_, this);
    		tdt.insertionDonnee();
    	}
    }

    private void traitementManuel() {
    	String[] donnee = new String[6];
    	RangerDonneeTemporaire rdf = new RangerDonneeTemporaire(main_);
		
		donnee[0] = valBonLivraison.getText();
		donnee[1] = String.valueOf(valDate.getDate().getTime());
		donnee[2] = valListeClient.getSelectedItem().toString();
		donnee[3] = valNomClient.getText();
		
		for (int ligne = 0; ligne<tableauLivraison.getRowCount(); ligne++){
			//Si un article a été rentré
			if (tableauLivraison.getValueAt(ligne, 0) != null){
				donnee[4] = tableauLivraison.getValueAt(ligne, 0).toString();
				int indice = donnee[4].indexOf(",");
				donnee[4] = donnee[4].substring(0, indice-1);
				
				//Quantite Livree
				if (tableauLivraison.getValueAt(ligne, 1) != null){
					donnee[5] = tableauLivraison.getValueAt(ligne, 1).toString();
					
					if (donnee[5].contains("-"))
						donnee[5].replace("-", "");
					
					rdf.ajout(donnee, "");
				}
				
				//Quantite Reprise
				if (tableauLivraison.getValueAt(ligne, 2) != null){
					donnee[5] = tableauLivraison.getValueAt(ligne, 2).toString();
					
					if (donnee[5].contains("-"))
						donnee[5].replace("-", "");
					
					rdf.ajout(donnee, "");
				}
			}
		} //Fin lecture de la JTable
	}

	private String verifManuelle() {
    	String contenuErreur = "";
    	if (valBonLivraison.getText().isEmpty())
    		contenuErreur += "bon de livraison";
    	if (valListeClient.getSelectedItem().equals("selectionner")){
    		if (contenuErreur != "")
    			contenuErreur += ", client";
    		else
    			contenuErreur += "client";
    	}
    	if (valDate.getDate() == null){
    		if (contenuErreur != "")
    			contenuErreur += ", date de livraison";
    		else
    			contenuErreur += "date de livraison";
    	}
		return contenuErreur;
	}

	private void traitementFichier() {
    	//Vérifier l'extension du fichier
    	String extention = "^.+\\.(xlsx|ods|txt|csv)$";
    	if (valFichier.getText().matches(extention)){
    		LectureFichier lfe = new LectureFichier(main_);
            try {
                lfe.ouverture_fichier(valFichier.getText());
            } catch (IOException e) {
                messageErreur.setText("Erreur : le fichier renseigné n'a pas un bon format de données");
            } catch (InvalidFormatException e) {
                messageErreur.setText("Erreur : le fichier renseigné n'a pas été trouvé");
            }
    	}
    	else{
    		messageErreur.setText("Merci de renseigner un ficher avec une extention .xls, .ods, .csv ou .txt");
    	}
	}

	private void boutonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // Remettre tout les champs à null !
    	messageErreur.setText("");
    	valFichier.setText("Fichier .xslx, .ods, .txt, .csv");
    	valBonLivraison.setText("");
    	valNomClient.setText("");
    	valListeClient.setSelectedIndex(0);
    	valDate.setDate(null);
    	for(int ligne = 0 ; ligne < tableauLivraison.getRowCount(); ligne++){
    		for (int colonne = 0; colonne < tableauLivraison.getColumnCount(); colonne++)
    			tableauLivraison.setValueAt(null, ligne, colonne);
    	}
    	data = new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
        	};
    	tableauLivraison.removeRowSelectionInterval(9, tableauLivraison.getRowCount()-1);
    	tableauLivraison.getColumn("Article").setCellEditor(new DefaultCellEditor(valListeProduit));
    	
    	this.repaint();
    }                                             

    private void boutonAjoutArticleActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	nouveauProduit = new VueGlobalNvProduit(main_, this, null);
    }
    
    void terminerAjoutProduit(){
    	listeProduit();
    	this.repaint();
    }

    private RecuperationDonnees rd;
    private Object[][] data;
    private String[] title = {"Article", "Quantité livrée", "Quantité reprise"};
    private List<Object[]> produit;
    private List<Object[]> client;
    private Main main_;
    private javax.swing.JComboBox valListeProduit;
    private VueGlobalNvClient nouveauClient;
    private VueGlobalNvProduit nouveauProduit;
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton boutonAjoutArticle;
    private javax.swing.JButton boutonAjoutClient;
    private javax.swing.JButton boutonAjoutLigne;
    private javax.swing.JButton boutonAnnuler;
    private javax.swing.JButton boutonEnregistrer;
    private javax.swing.JButton boutonParcourir;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelBonLivraison;
    private javax.swing.JLabel labelClient;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JLabel messageErreur;
    private javax.swing.JPanel panelEdition;
    private javax.swing.JScrollPane scrollLivraison;
    private javax.swing.JTable tableauLivraison;
    private javax.swing.JTextField valBonLivraison;
    private com.toedter.calendar.JDateChooser valDate;
    private javax.swing.JTextField valFichier;
    private javax.swing.JComboBox valListeClient;
    private javax.swing.JLabel valNomClient;
    // End of variables declaration                   
}