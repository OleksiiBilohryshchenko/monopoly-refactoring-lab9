package edu.sumdu.monopoly.gui.fx;

import edu.sumdu.monopoly.*;
import edu.sumdu.monopoly.gui.GameBoardUtil;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainWindowFx implements MonopolyGUI {

    private final Stage stage;
    private final BorderPane root = new BorderPane();

    private final List<GUICellFx> guiCells = new ArrayList<>();
    private GUICellFx[] boardCells;
    private Circle[] playerTokens;

    private final GridPane northGrid = new GridPane();
    private final GridPane southGrid = new GridPane();
    private final GridPane westGrid  = new GridPane();
    private final GridPane eastGrid  = new GridPane();

    private PlayerPanelFx[] playerPanels;

    public MainWindowFx(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(root, 1200, 800);
        stage.setTitle("Monopoly (JavaFX)");
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

    // ========= Game board =========

    public void setupGameBoard(GameBoard board) {
        Platform.runLater(() -> {
            buildEdge(northGrid, GameBoardUtil.getNorthCells(board), true);
            buildEdge(southGrid, GameBoardUtil.getSouthCells(board), true);
            buildEdge(westGrid,  GameBoardUtil.getWestCells(board),  false);
            buildEdge(eastGrid,  GameBoardUtil.getEastCells(board),  false);

            root.setTop(northGrid);
            root.setBottom(southGrid);
            root.setLeft(westGrid);
            root.setRight(eastGrid);

            boardCells = guiCells.toArray(new GUICellFx[0]);

            initPlayerTokens();
            buildPlayerPanels();
        });
    }

    private void buildEdge(GridPane grid, List<Cell> cells, boolean horizontal) {
        grid.getChildren().clear();

        for (int i = 0; i < cells.size(); i++) {
            GUICellFx cellFx = new GUICellFx(cells.get(i));
            guiCells.add(cellFx);

            if (horizontal) {
                grid.add(cellFx, i, 0);
            } else {
                grid.add(cellFx, 0, i);
            }
        }
    }

    private void initPlayerTokens() {
        int players = GameMaster.instance().getNumberOfPlayers();
        playerTokens = new Circle[players];

        for (int i = 0; i < players; i++) {
            Circle token = new Circle(8);
            playerTokens[i] = token;
            boardCells[0].getChildren().add(token); // стартова клітинка
        }
    }

    // ========= Player panels =========

    private void buildPlayerPanels() {
        GameMaster master = GameMaster.instance();
        int players = master.getNumberOfPlayers();

        GridPane infoGrid = new GridPane();
        root.setCenter(infoGrid);

        playerPanels = new PlayerPanelFx[players];

        for (int i = 0; i < players; i++) {
            playerPanels[i] = new PlayerPanelFx(master.getPlayer(i));
            infoGrid.add(playerPanels[i], i % 2, i / 2);
            playerPanels[i].displayInfo();
        }
    }

    // ========= MonopolyGUI =========

    @Override
    public void movePlayer(int index, int from, int to) {
        Platform.runLater(() -> {
            Circle token = playerTokens[index];
            boardCells[from].getChildren().remove(token);
            boardCells[to].getChildren().add(token);
        });
    }

    @Override
    public void enableEndTurnBtn(int playerIndex) {
        Platform.runLater(() ->
                playerPanels[playerIndex].setEndTurnEnabled(true)
        );
    }

    @Override
    public void enablePlayerTurn(int playerIndex) {
        Platform.runLater(() ->
                playerPanels[playerIndex].setRollDiceEnabled(true)
        );
    }

    @Override
    public void enablePurchaseBtn(int playerIndex) {
        Platform.runLater(() ->
                playerPanels[playerIndex].setPurchasePropertyEnabled(true)
        );
    }

    @Override
    public void setBuyHouseEnabled(boolean b) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        Platform.runLater(() ->
                playerPanels[idx].setBuyHouseEnabled(b)
        );
    }

    @Override
    public void setDrawCardEnabled(boolean b) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        Platform.runLater(() ->
                playerPanels[idx].setDrawCardEnabled(b)
        );
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        Platform.runLater(() ->
                playerPanels[idx].setEndTurnEnabled(enabled)
        );
    }

    @Override
    public void setGetOutOfJailEnabled(boolean b) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        Platform.runLater(() ->
                playerPanels[idx].setGetOutOfJailEnabled(b)
        );
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        Platform.runLater(() ->
                playerPanels[idx].setPurchasePropertyEnabled(enabled)
        );
    }

    @Override
    public void setRollDiceEnabled(boolean b) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        Platform.runLater(() ->
                playerPanels[idx].setRollDiceEnabled(b)
        );
    }

    @Override
    public void setTradeEnabled(int index, boolean b) {
        Platform.runLater(() ->
                playerPanels[index].setTradeEnabled(b)
        );
    }

    // ========= Stubs =========

    @Override
    public int[] getDiceRoll() {
        return DiceRollDialogFx.show();
    }

    @Override
    public RespondDialog openRespondDialog(TradeDeal deal) {
        return null;
    }

    @Override
    public TradeDialog openTradeDialog() {
        return null;
    }

    @Override
    public void showBuyHouseDialog(Player currentPlayer) {
        throw new UnsupportedOperationException("Not implemented in JavaFX yet");
    }

    @Override
    public void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Monopoly");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }


    @Override
    public int showUtilDiceRoll() {
        throw new UnsupportedOperationException("Not implemented in JavaFX yet");
    }

    @Override
    public boolean isDrawCardButtonEnabled() {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels[idx].isDrawCardButtonEnabled();
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels[idx].isEndTurnButtonEnabled();
    }

    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        return playerPanels[idx].isGetOutOfJailButtonEnabled();
    }

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return playerPanels[i].isTradeButtonEnabled();
    }

    @Override
    public void startGame() {
        // FX init already done in setupGameBoard
    }

    @Override
    public void update() {
        Platform.runLater(() -> {
            for (PlayerPanelFx panel : playerPanels) {
                panel.displayInfo();
            }
        });
    }
}
