package edu.sumdu.monopoly.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import edu.sumdu.monopoly.*;
import edu.sumdu.monopoly.gui.fx.MainWindowFx;
import javafx.application.Platform;
import javafx.scene.control.TextInputDialog;



public class JavaFxMain extends Application {

    @Override
    public void start(Stage stage) {

        GameMaster master = GameMaster.instance();

        // 1. Game board
        GameBoard gameBoard = new GameBoardFull();
        master.setGameBoard(gameBoard);

        // 2. Ask number of players
        TextInputDialog dlgPlayers = new TextInputDialog("2");
        dlgPlayers.setTitle("Monopoly");
        dlgPlayers.setHeaderText("Number of players");
        dlgPlayers.setContentText("Enter number (1–8):");

        int numPlayers;
        try {
            String res = dlgPlayers.showAndWait().orElseThrow();
            numPlayers = Integer.parseInt(res);
            if (numPlayers <= 0 || numPlayers > GameMaster.MAX_PLAYER) {
                Platform.exit();
                return;
            }
        } catch (Exception e) {
            Platform.exit();
            return;
        }

        master.setNumberOfPlayers(numPlayers);

        // 3. Ask player names
        for (int i = 0; i < numPlayers; i++) {
            TextInputDialog dlgName =
                    new TextInputDialog("Player " + (i + 1));
            dlgName.setTitle("Player name");
            dlgName.setHeaderText(null);
            dlgName.setContentText("Name for player " + (i + 1));

            String name = dlgName.showAndWait().orElse("Player " + (i + 1));
            master.getPlayer(i).setName(name);
        }

        // 4. Create FX window
        MainWindowFx window = new MainWindowFx(stage);
        window.setupGameBoard(gameBoard);

        master.setGUI(window);

        window.show();
        master.startGame();
    }

}
