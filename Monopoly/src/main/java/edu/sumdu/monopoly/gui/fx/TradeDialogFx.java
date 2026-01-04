package edu.sumdu.monopoly.gui.fx;

import edu.sumdu.monopoly.*;
import edu.sumdu.monopoly.Cell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class TradeDialogFx implements TradeDialog {

    private TradeDeal deal;

    public TradeDialogFx(Stage owner) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Trade Property");

        ComboBox<Player> cboSellers = new ComboBox<>();
        ComboBox<Cell> cboProperties = new ComboBox<>();
        TextField txtAmount = new TextField();

        Button btnOk = new Button("OK");
        Button btnCancel = new Button("Cancel");

        btnOk.setDisable(true);

        buildSellers(cboSellers, cboProperties, btnOk);

        cboSellers.setOnAction(e ->
                updateProperties(cboSellers.getValue(), cboProperties, btnOk)
        );

        btnCancel.setOnAction(e -> dialog.close());

        btnOk.setOnAction(e -> {
            int amount;
            try {
                amount = Integer.parseInt(txtAmount.getText());
            } catch (NumberFormatException ex) {
                showError("Amount should be an integer");
                return;
            }

            Cell cell = cboProperties.getValue();
            Player seller = cboSellers.getValue();
            Player currentPlayer = GameMaster.instance().getCurrentPlayer();

            if (cell == null || seller == null) return;
            if (currentPlayer.getMoney() <= amount) return;

            deal = new TradeDeal();
            deal.setAmount(amount);
            deal.setPropertyName(cell.getName());
            deal.setSellerIndex(GameMaster.instance().getPlayerIndex(seller));

            dialog.close();
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Seller"), 0, 0);
        grid.add(cboSellers, 1, 0);
        grid.add(new Label("Property"), 0, 1);
        grid.add(cboProperties, 1, 1);
        grid.add(new Label("Amount"), 0, 2);
        grid.add(txtAmount, 1, 2);
        grid.add(btnOk, 0, 3);
        grid.add(btnCancel, 1, 3);

        GridPane.setHalignment(btnOk, Pos.CENTER.getHpos());
        GridPane.setHalignment(btnCancel, Pos.CENTER.getHpos());

        dialog.setScene(new Scene(grid));
        dialog.showAndWait();
    }

    private void buildSellers(
            ComboBox<Player> sellers,
            ComboBox<Cell> properties,
            Button btnOk
    ) {
        List<Player> list = GameMaster.instance().getSellerList();
        sellers.getItems().addAll(list);
        if (!list.isEmpty()) {
            sellers.getSelectionModel().select(0);
            updateProperties(list.get(0), properties, btnOk);
        }
    }

    private void updateProperties(
            Player player,
            ComboBox<Cell> properties,
            Button btnOk
    ) {
        properties.getItems().clear();
        if (player == null) {
            btnOk.setDisable(true);
            return;
        }
        Cell[] cells = player.getAllProperties();
        properties.getItems().addAll(cells);
        btnOk.setDisable(cells.length == 0);
        if (cells.length > 0) {
            properties.getSelectionModel().select(0);
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @Override
    public TradeDeal getTradeDeal() {
        return deal;
    }
}
