package edu.sumdu.monopoly.gui.fx;

import edu.sumdu.monopoly.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


public class GUICellFx extends StackPane {

    private final Cell cell;
    private final Label title;

    public GUICellFx(Cell cell) {
        this.cell = cell;
        this.title = new Label(cell.getName());

        setPrefSize(100, 100);
        setStyle("-fx-border-color: black;");

        getChildren().add(title);
    }

    public Cell getCell() {
        return cell;
    }
}
