package edu.sumdu.monopoly;

public class CardCell extends Cell {
    protected Player owner;
    private int type;
    private boolean available = true;

    public CardCell(int type, String name) {
        setName(name);
        this.type = type;
    }
    
    public void playAction() {
    }
    
    public int getType() {
        return type;
    }
}
