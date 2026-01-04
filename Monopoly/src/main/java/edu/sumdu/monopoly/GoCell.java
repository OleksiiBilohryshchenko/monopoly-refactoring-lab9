package edu.sumdu.monopoly;

public class GoCell extends Cell {
    protected Player owner;

    public GoCell() {
		super.setName("Go");
		setAvailable(false);
	}

	public void playAction() {
	}
	
	void setName(String name) {
	}
}
