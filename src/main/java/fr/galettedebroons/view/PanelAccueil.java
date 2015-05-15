package fr.galettedebroons.view;

public class PanelAccueil extends javax.swing.JPanel {

	private javax.swing.JLabel message;
	
    public PanelAccueil() {
        initComponents();
    }
    
    public void initComponents(){
    	message = new javax.swing.JLabel();
    	
    	message.setText("<html>"
        		+ "Bienvenue dans l'application SalesForcastingSoftware <br/><br/>"
        		+ "Cette application est la propriété de l'entreprise 'La galette de Broöns'. <br/><br/>"
        		+ "Elle a été créée par 4 étudiants de la MIAGE de Rennes en 2015 :"
        		+ "<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Moncef EL ARCH"
        		+ "<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Julie GUÉGNAUD"
        		+ "<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Mélissa POHER"
        		+ "<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Ndeye Oumoul Khairy SY"
        		+ "</html>");

        javax.swing.GroupLayout panelAccueilLayout = new javax.swing.GroupLayout(this);
        this.setLayout(panelAccueilLayout);
        panelAccueilLayout.setHorizontalGroup(
            panelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccueilLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(message)
                .addContainerGap(763, Short.MAX_VALUE))
        );
        panelAccueilLayout.setVerticalGroup(
            panelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccueilLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(message)
                .addContainerGap(475, Short.MAX_VALUE))
        );
    }
    
}
