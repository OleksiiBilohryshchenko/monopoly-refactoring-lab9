package edu.sumdu.monopoly;

public class JailCell extends Cell {
	public static int BAIL = 50;
    protected Player owner;
    private boolean available = true;

    public JailCell() {
		setName("Jail");
	}
	
	public void playAction() {
		
	}
}
