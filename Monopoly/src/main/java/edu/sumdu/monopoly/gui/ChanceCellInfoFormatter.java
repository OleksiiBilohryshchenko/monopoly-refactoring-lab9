package edu.sumdu.monopoly.gui;

import edu.sumdu.monopoly.Cell;
import edu.sumdu.monopoly.gui.CellInfoFormatter;

public class ChanceCellInfoFormatter implements CellInfoFormatter {
    
    public static final String CHANCE_CELL_LABEL = "<html><font color='teal'><b>Chance</b></font></html>";
    
    public String format(Cell cell) {
        return CHANCE_CELL_LABEL;
    }
}
