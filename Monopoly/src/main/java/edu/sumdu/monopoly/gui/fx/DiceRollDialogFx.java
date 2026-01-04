package edu.sumdu.monopoly.gui.fx;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class DiceRollDialogFx {

    public static int[] show() {
        Dialog<int[]> dialog = new Dialog<>();
        dialog.setTitle("Roll Dice");

        ButtonType rollBtn = new ButtonType("Roll", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(rollBtn, ButtonType.CANCEL);

        Spinner<Integer> d1 = new Spinner<>(1, 6, 1);
        Spinner<Integer> d2 = new Spinner<>(1, 6, 1);

        dialog.getDialogPane().setContent(new HBox(10, d1, d2));

        dialog.setResultConverter(bt -> {
            if (bt == rollBtn) {
                return new int[]{ d1.getValue(), d2.getValue() };
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }
}
