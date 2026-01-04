package edu.sumdu.monopoly;

public class FreeParkingCell extends Cell {

    protected Player owner;
    private boolean available = true;

    public FreeParkingCell() {
		setName("Free Parking");
	}

	public void playAction() {
		return;
	}
}
