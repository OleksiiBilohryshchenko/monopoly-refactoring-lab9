package edu.sumdu.monopoly.gui.fx;

import edu.sumdu.monopoly.*;

import edu.sumdu.monopoly.gui.GameBoardUtil;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import edu.sumdu.monopoly.GameBoard;

public class MainWindowFx implements MonopolyGUI {

    private final Stage stage;
    private final BorderPane root = new BorderPane();
    private final List<GUICellFx> guiCells = new ArrayList<>();

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

    // ===== Player panels (CENTER) =====

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

    // ===== MonopolyGUI implementation (PARTIAL) =====

    @Override
    public void enableEndTurnBtn(int playerIndex) {
        playerPanels[playerIndex].setEndTurnEnabled(true);
    }

    @Override
    public void enablePlayerTurn(int playerIndex) {
        playerPanels[playerIndex].setRollDiceEnabled(true);
    }

    @Override
    public void enablePurchaseBtn(int playerIndex) {
        playerPanels[playerIndex].setPurchasePropertyEnabled(true);
    }

    @Override
    public void setBuyHouseEnabled(boolean b) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[idx].setBuyHouseEnabled(b);
    }

    @Override
    public void setDrawCardEnabled(boolean b) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[idx].setDrawCardEnabled(b);
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[idx].setEndTurnEnabled(enabled);
    }

    @Override
    public void setGetOutOfJailEnabled(boolean b) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[idx].setGetOutOfJailEnabled(b);
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[idx].setPurchasePropertyEnabled(enabled);
    }

    @Override
    public void setRollDiceEnabled(boolean b) {
        int idx = GameMaster.instance().getCurrentPlayerIndex();
        playerPanels[idx].setRollDiceEnabled(b);
    }

    @Override
    public void setTradeEnabled(int index, boolean b) {
        playerPanels[index].setTradeEnabled(b);
    }

    // ===== Not implemented yet (INTENTIONALLY) =====

    @Override
    public int[] getDiceRoll() {
        throw new UnsupportedOperationException("Not implemented in JavaFX yet");
    }

    @Override
    public void showBuyHouseDialog(Player currentPlayer) {
        throw new UnsupportedOperationException("Not implemented in JavaFX yet");
    }

    @Override
    public void showMessage(String msg) {
        throw new UnsupportedOperationException("Not implemented in JavaFX yet");
    }

    @Override
    public int showUtilDiceRoll() {
        throw new UnsupportedOperationException("Not implemented in JavaFX yet");
    }

    @Override
    public void startGame() {
        buildPlayerPanels();
    }

    @Override
    public void update() {
        for (PlayerPanelFx panel : playerPanels) {
            panel.displayInfo();
        }
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
    public void movePlayer(int index, int from, int to) {

    }

    @Override
    public RespondDialog openRespondDialog(TradeDeal deal) {
        return null;
    }

    @Override
    public TradeDialog openTradeDialog() {
        return null;
    }

    public void setupGameBoard(GameBoard board) {
        var dimension = GameBoardUtil.calculateDimension(board.getCellNumber());

        buildEdge(northGrid, GameBoardUtil.getNorthCells(board), true);
        buildEdge(southGrid, GameBoardUtil.getSouthCells(board), true);
        buildEdge(westGrid,  GameBoardUtil.getWestCells(board),  false);
        buildEdge(eastGrid,  GameBoardUtil.getEastCells(board),  false);

        root.setTop(northGrid);
        root.setBottom(southGrid);
        root.setLeft(westGrid);
        root.setRight(eastGrid);

        buildPlayerPanels();
    }

    private void buildEdge(GridPane grid, List<Cell> cells, boolean horizontal) {
        grid.getChildren().clear();

        for (int i = 0; i < cells.size(); i++) {
            GUICellFx view = new GUICellFx(cells.get(i));
            guiCells.add(view);

            if (horizontal) {
                grid.add(view, i, 0);
            } else {
                grid.add(view, 0, i);
            }
        }
    }


}
