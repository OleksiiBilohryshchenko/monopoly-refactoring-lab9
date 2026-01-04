package edu.sumdu.monopoly.gui.fx;

import edu.sumdu.monopoly.GameMaster;
import javafx.scene.layout.GridPane;

public class InfoPanelFx extends GridPane {

    public void displayInfo() {
        GameMaster master = GameMaster.instance();

        getChildren().clear();

        int players = master.getNumberOfPlayers();
        for (int i = 0; i < players; i++) {
            PlayerPanelFx panel = new PlayerPanelFx(master.getPlayer(i));
            add(panel, i, 0);
            panel.displayInfo();
        }
    }
}
