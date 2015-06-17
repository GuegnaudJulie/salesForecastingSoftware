package fr.galettedebroons.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class VuePrevisionCustomisation extends DefaultTableCellRenderer {
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        /**
         * Fixer la couleur de fond de la première colonne en jaune
         */
        if (column == 0) {
            Color clr = new Color(255, 134, 106);
            component.setBackground(clr);
        } else {
            Color clr = new Color(255, 255, 255);
            component.setBackground(clr);
        }
        /**
         * Colorier les cellules en orange si le montant est négatif
         */
        Object o = table.getValueAt(row, 3);
        if (o != null && component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if (label.getText().contains("-")) {
                Color clr = new Color(255, 0, 0);
                component.setBackground(clr);
            }
            /**
             * Center le texte pour la colonne 0 et aligner le texte à droite pour les autres colonnes
             */
            if (column == 0) {
                label.setHorizontalAlignment(CENTER);
            } else {
                label.setHorizontalAlignment(RIGHT);
            }
        }
        return component;
    }

}
