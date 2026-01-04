package edu.sumdu.monopoly.gui.fx;

import edu.sumdu.monopoly.RespondDialog;
import edu.sumdu.monopoly.TradeDeal;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RespondDialogFx implements RespondDialog {

    private boolean response = false;

    public RespondDialogFx(Stage owner, TradeDeal deal) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Trade offer");

        TextArea message = new TextArea(deal.makeMessage());
        message.setWrapText(true);
        message.setEditable(false);
        message.setPrefSize(300, 200);

        Button btnYes = new Button("Yes");
        Button btnNo = new Button("No");

        btnYes.setOnAction(e -> {
            response = true;
            dialog.close();
        });

        btnNo.setOnAction(e -> {
            response = false;
            dialog.close();
        });

        HBox buttons = new HBox(10, btnYes, btnNo);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, message, buttons);
        root.setPadding(new Insets(10));

        dialog.setScene(new Scene(root));
        dialog.showAndWait();
    }

    @Override
    public boolean getResponse() {
        return response;
    }
}
