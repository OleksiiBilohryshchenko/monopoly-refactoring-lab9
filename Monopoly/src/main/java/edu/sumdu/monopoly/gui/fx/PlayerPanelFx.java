package edu.sumdu.monopoly.gui.fx;

import edu.sumdu.monopoly.Player;
import javafx.scene.layout.VBox;

public class PlayerPanelFx extends VBox {

    private final Player player;

    public PlayerPanelFx(Player player) {
        this.player = player;
    }

    public void displayInfo() {
        // intentionally empty (stub)
    }
}
