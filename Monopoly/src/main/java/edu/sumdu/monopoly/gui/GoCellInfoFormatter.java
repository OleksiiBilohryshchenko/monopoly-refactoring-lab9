package edu.sumdu.monopoly.gui;

import edu.sumdu.monopoly.Cell;

public class GoCellInfoFormatter implements edu.sumdu.monopoly.gui.CellInfoFormatter {
    
    public static final String GO_CELL_LABEL = "<html><b>Go</b></html>";
    
    public String format(Cell cell) {
        return GO_CELL_LABEL;
    }
}
