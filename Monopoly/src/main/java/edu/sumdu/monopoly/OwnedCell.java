package edu.sumdu.monopoly;

public abstract class OwnedCell extends Cell {

    protected abstract int calculateRent();

    @Override
    public void playAction() {
        if (!isAvailable()) {
            Player currentPlayer = GameMaster.instance().getCurrentPlayer();
            if (owner != currentPlayer) {
                currentPlayer.payRentTo(owner, calculateRent());
            }
        }
    }


}
