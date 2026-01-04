package edu.sumdu.monopoly;

public abstract class Cell {
	private String name;

	public String getName() {
		return name;
	}

	public Player getOwner() {
		if (this instanceof OwnedCell) {
			return ((OwnedCell) this).owner;
		}
		return null;
	}

	public int getPrice() {
		return 0;
	}

	public boolean isAvailable() {
		return false;
	}

	public abstract void playAction();

	
	void setName(String name) {
		this.name = name;
	}

	public void setOwner(Player owner) {
		if (this instanceof OwnedCell) {
			((OwnedCell) this).owner = owner;
		}
	}
    
    public String toString() {
        return name;
    }
}
