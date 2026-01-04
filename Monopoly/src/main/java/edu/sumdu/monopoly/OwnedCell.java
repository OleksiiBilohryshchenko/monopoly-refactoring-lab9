package edu.sumdu.monopoly;

public abstract class OwnedCell extends Cell {

    protected Player owner;
    private boolean available = true;

    protected abstract int calculateRent();

    public int getRent() {
        return calculateRent();
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

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
