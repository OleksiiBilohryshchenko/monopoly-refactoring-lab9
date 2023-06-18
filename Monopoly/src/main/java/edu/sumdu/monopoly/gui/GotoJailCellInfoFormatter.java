package edu.sumdu.monopoly.gui;

import edu.sumdu.monopoly.Cell;
import edu.sumdu.monopoly.gui.CellInfoFormatter;

public class GotoJailCellInfoFormatter implements CellInfoFormatter {

    public static final String GOTO_JAIL_LABEL = "<html><b>Go to Jail</b></html>";

    public String format(Cell cell) {
    	return GOTO_JAIL_LABEL;
	}
}
