package services;

/**
 * @author Adrien Jallais
 * @source https://stackoverflow.com/questions/7486012/static-classes-in-java
 */
public final class RulesService {
	private final static int handLength = 5;
	private final static int playerNumber = 1;
	private final static int[] cardRange = new int[] { 1, 100 };
	private final static int numberDrawPile = 1; // there is also a singleton pattern
	private final static int numberDescendingPile = 2;
	private final static int numberAscendingPile = 2;

	/**
	 * @return the playerNumber
	 */
	public static int getPlayerNumber() {
		return RulesService.playerNumber;
	}

	/**
	 * @return the handLength number of card in the hand of ONE player
	 */
	public static int getHandLength() {
		return RulesService.handLength;
	}

	/**
	 * @return the cardRange [0] is the min, [1] is the max
	 */
	public static int[] getCardRange() {
		return RulesService.cardRange;
	}

	/**
	 * @return the drawPileRange
	 */
	public static int[] getDrawPileRange() {
		int[] drawPileRange = { RulesService.getCardRange()[0] + 1, RulesService.getCardRange()[1] + 1 };
		return drawPileRange;
	}

	/**
	 * @return the layascendingpilerange
	 */
	public static int[] getLayAscendingPileRange() {
		int[] layAscendingPileRange = { RulesService.getCardRange()[0], RulesService.getCardRange()[1] };
		return layAscendingPileRange;
	}

	/**
	 * @return the laydescendingpilerange
	 */
	public static int[] getLayDescendingPileRange() {
		int[] layDescendingPileRange = { RulesService.getCardRange()[1], RulesService.getCardRange()[0] };
		return layDescendingPileRange;
	}

	/**
	 * 
	 * @return the draw pile size allowed
	 */
	public static int getDrawPileSize() {
		// +1 for the element and not the interval
		// -2 cause the limits should be excluded
		return (-2 + 1 + RulesService.getDrawPileRange()[1] - RulesService.getDrawPileRange()[0]);
	}

	/**
	 * @return the numberDrawPile
	 */
	public static int getNumberDrawPile() {
		return RulesService.numberDrawPile;
	}

	/**
	 * @return the numberDescendingPile
	 */
	public static int getNumberDescendingPile() {
		return RulesService.numberDescendingPile;
	}

	/**
	 * @return the numberAscendingPile
	 */
	public static int getNumberAscendingPile() {
		return RulesService.numberAscendingPile;
	}

	/**
	 * 
	 * @return the number of card expected in a game
	 */
	public static int getSizeExpected() {
		// there is one card on each laying piles
		return (RulesService.getDrawPileSize() + RulesService.getNumberAscendingPile()
				+ RulesService.getNumberDescendingPile());
	}
}
