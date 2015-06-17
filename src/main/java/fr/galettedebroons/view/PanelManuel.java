package fr.galettedebroons.view;

public class PanelManuel extends javax.swing.JPanel {

	private javax.swing.JLabel manuel;
	
    public PanelManuel() {
        initComponents();
    }
    
    public void initComponents(){
    	manuel = new javax.swing.JLabel();
    	
    	manuel.setText("Bienvenue dans le manuel de notre application");

    	javax.swing.GroupLayout panelManuelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(panelManuelLayout);
        panelManuelLayout.setHorizontalGroup(
            panelManuelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManuelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(manuel)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        panelManuelLayout.setVerticalGroup(
            panelManuelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManuelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(manuel)
                .addContainerGap(494, Short.MAX_VALUE))
        );
    }
	
}
