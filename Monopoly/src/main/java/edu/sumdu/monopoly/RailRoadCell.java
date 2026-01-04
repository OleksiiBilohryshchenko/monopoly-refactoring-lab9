package edu.sumdu.monopoly;

public class RailRoadCell extends OwnedCell {
	static private int baseRent;
	static public String COLOR_GROUP = "RAILROAD";
	static private int price;

	public static void setBaseRent(int baseRent) {
		RailRoadCell.baseRent = baseRent;
	}

	public static void setPrice(int price) {
		RailRoadCell.price = price;
	}
	
	public int getPrice() {
		return RailRoadCell.price;
	}

	@Override
	protected int calculateRent() {
		int railroadsOwned = owner.numberOfRR();

		if (railroadsOwned <= 1) {
			return baseRent;
		}

		return baseRent * (int) Math.pow(2, railroadsOwned - 1);
	}


}
