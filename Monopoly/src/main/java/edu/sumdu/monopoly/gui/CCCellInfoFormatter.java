package edu.sumdu.monopoly.gui;

import edu.sumdu.monopoly.Cell;
import edu.sumdu.monopoly.gui.CellInfoFormatter;

public class CCCellInfoFormatter implements CellInfoFormatter {
    public String format(Cell cell) {
        return "<html><font color='white'><b>" + cell.getName() + "</b></font></html>";
    }
}
