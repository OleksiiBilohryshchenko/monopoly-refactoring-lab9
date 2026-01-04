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
		String[] monopolies = owner.getMonopolies();
		int railroadsOwned = 0;

		for (String monopoly : monopolies) {
			if (COLOR_GROUP.equals(monopoly)) {
				railroadsOwned++;
			}
		}

		if (railroadsOwned == 0) {
			return baseRent;
		}

		return baseRent * (int) Math.pow(2, railroadsOwned - 1);
	}


}
