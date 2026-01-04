package edu.sumdu.monopoly.gui.fx;

import edu.sumdu.monopoly.Card;
import edu.sumdu.monopoly.Cell;
import edu.sumdu.monopoly.GameMaster;
import edu.sumdu.monopoly.Player;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class PlayerPanelFx extends BorderPane {

    private final Player player;

    private Button btnBuyHouse;
    private Button btnDrawCard;
    private Button btnEndTurn;
    private Button btnGetOutOfJail;
    private Button btnPurchaseProperty;
    private Button btnRollDice;
    private Button btnTrade;

    private Label lblName;
    private Label lblMoney;

    private TextArea txtProperty;

    public PlayerPanelFx(Player player) {
        this.player = player;

        createInfoPanel();
        createActionPanel();
        initState();
        bindActions();
    }

    private void createInfoPanel() {
        VBox infoBox = new VBox(5);
        infoBox.setPadding(new Insets(5));

        lblName = new Label();
        lblName.setFont(Font.font(14));

        lblMoney = new Label();

        txtProperty = new TextArea();
        txtProperty.setEditable(false);
        txtProperty.setPrefRowCount(15);

        infoBox.getChildren().addAll(lblName, lblMoney, txtProperty);
        setCenter(infoBox);
    }

    private void createActionPanel() {
        GridPane actionGrid = new GridPane();
        actionGrid.setHgap(5);
        actionGrid.setVgap(5);
        actionGrid.setPadding(new Insets(5));

        btnBuyHouse = new Button("Buy House");
        btnRollDice = new Button("Roll Dice");
        btnPurchaseProperty = new Button("Purchase Property");
        btnGetOutOfJail = new Button("Get Out of Jail");
        btnEndTurn = new Button("End Turn");
        btnDrawCard = new Button("Draw Card");
        btnTrade = new Button("Trade");

        actionGrid.add(btnBuyHouse, 0, 0);
        actionGrid.add(btnRollDice, 1, 0);
        actionGrid.add(btnPurchaseProperty, 2, 0);

        actionGrid.add(btnGetOutOfJail, 0, 1);
        actionGrid.add(btnEndTurn, 1, 1);
        actionGrid.add(btnDrawCard, 2, 1);

        actionGrid.add(btnTrade, 0, 2);

        setBottom(actionGrid);
    }

    private void initState() {
        btnRollDice.setDisable(true);
        btnPurchaseProperty.setDisable(true);
        btnEndTurn.setDisable(true);
        btnBuyHouse.setDisable(true);
        btnGetOutOfJail.setDisable(true);
        btnDrawCard.setDisable(true);
        btnTrade.setDisable(true);
    }

    private void bindActions() {
        btnRollDice.setOnAction(e ->
                GameMaster.instance().btnRollDiceClicked());

        btnEndTurn.setOnAction(e ->
                GameMaster.instance().btnEndTurnClicked());

        btnPurchaseProperty.setOnAction(e ->
                GameMaster.instance().btnPurchasePropertyClicked());

        btnBuyHouse.setOnAction(e ->
                GameMaster.instance().btnBuyHouseClicked());

        btnGetOutOfJail.setOnAction(e ->
                GameMaster.instance().btnGetOutOfJailClicked());

        btnDrawCard.setOnAction(e -> {
            Card card = GameMaster.instance().btnDrawCardClicked();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(card.getLabel());
            alert.showAndWait();
            displayInfo();
        });

        btnTrade.setOnAction(e ->
                GameMaster.instance().btnTradeClicked());
    }

    public void displayInfo() {
        lblName.setText(player.getName());
        lblMoney.setText("$ " + player.getMoney());

        StringBuilder buf = new StringBuilder();
        Cell[] cells = player.getAllProperties();
        for (Cell cell : cells) {
            buf.append(cell).append("\n");
        }
        txtProperty.setText(buf.toString());
    }

    // ---- API для GameMaster

    public void setBuyHouseEnabled(boolean b) {
        btnBuyHouse.setDisable(!b);
    }

    public void setDrawCardEnabled(boolean b) {
        btnDrawCard.setDisable(!b);
    }

    public void setEndTurnEnabled(boolean b) {
        btnEndTurn.setDisable(!b);
    }

    public void setGetOutOfJailEnabled(boolean b) {
        btnGetOutOfJail.setDisable(!b);
    }

    public void setPurchasePropertyEnabled(boolean b) {
        btnPurchaseProperty.setDisable(!b);
    }

    public void setRollDiceEnabled(boolean b) {
        btnRollDice.setDisable(!b);
    }

    public void setTradeEnabled(boolean b) {
        btnTrade.setDisable(!b);
    }
}
