package fr.galettedebroons.model;
import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MonCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 226531220534131041L;	
	private List<int[]> caseRouge_;
	
	public MonCellRenderer(List<int[]> caseAvecRupture) {
		caseRouge_ = caseAvecRupture;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
		
		String str = null;
		
		for (int[] cr : caseRouge_){
			if (cr[0] == row && cr[1] == column)
				str = "R";
		}
		
		if (str == "R"){
			cell.setBackground(Color.RED);
		}
		else
			cell.setBackground(Color.WHITE);
		
		return cell;
	}
}