package edu.sumdu.monopoly;

public class UtilityCell extends OwnedCell {

	public static final String COLOR_GROUP = "UTILITY";
	private static int PRICE;

	public static void setPrice(int price) {
		UtilityCell.PRICE = price;
	}

	public int getPrice() {
		return UtilityCell.PRICE;
	}

	@Override
	protected int calculateRent() {
		int diceRoll = GameMaster.instance().getUtilDiceRoll();
		int utilitiesOwned = owner.numberOfUtil();

		return utilitiesOwned == 1 ? diceRoll * 4 : diceRoll * 10;
	}

	public int getRent(int diceRoll) {
		int utilitiesOwned = owner.numberOfUtil();
		return utilitiesOwned == 1 ? diceRoll * 4 : diceRoll * 10;
	}

}


