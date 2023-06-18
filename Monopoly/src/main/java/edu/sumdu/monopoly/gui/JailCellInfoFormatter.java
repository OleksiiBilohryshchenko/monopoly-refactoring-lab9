package edu.sumdu.monopoly.gui;

import edu.sumdu.monopoly.Cell;

public class JailCellInfoFormatter implements CellInfoFormatter {

    public static final String JAIL_CELL_LABEL = "<html><b>Jail</b></html>";

    public String format(Cell cell) {
		return JAIL_CELL_LABEL;
	}

}
