package services;

/**
 * @author Adrien Jallais
 * @source https://stackoverflow.com/questions/7486012/static-classes-in-java
 */
public final class ServiceRules {
	private final static int playerNumber = 1;
	// private final static int[] cardRange = new int[] { 10, 20 };
	private final static int[] cardRange = new int[] { 1, 100 };
	private final static int numberDrawPile = 1; // there is also a singleton pattern
	private final static int numberDescendingPile = 2;
	private final static int numberAscendingPile = 2;
	private final static int numberOfCardByTurn = 2;

	/**
	 * @return the playerNumber
	 */
	public static int getPlayerNumber() {
		return ServiceRules.playerNumber;
	}

	/**
	 * @return the handLength number of card in the hand of ONE player
	 */
	public static int getHandLength() {
		int nbCardMax;

		switch (ServiceRules.getPlayerNumber()) {
		case 1:
			nbCardMax = 8;
			break;
		case 2:
			nbCardMax = 7;
			break;
		default:
			nbCardMax = 6;
			break;
		}
		return nbCardMax;
	}

	/**
	 * @return the cardRange [0] is the min, [1] is the max
	 */
	public static int[] getCardRange() {
		return ServiceRules.cardRange;
	}

	/**
	 * @return the drawPileRange
	 */
	public static int[] getDrawPileRange() {
		int[] drawPileRange = { ServiceRules.getCardRange()[0] + 1, ServiceRules.getCardRange()[1] - 1 };
		return drawPileRange;
	}

	/**
	 * @return the layascendingpilerange
	 */
	public static int[] getLayAscendingPileRange() {
		int[] layAscendingPileRange = { ServiceRules.getCardRange()[0], ServiceRules.getCardRange()[1] - 1 };
		return layAscendingPileRange;
	}

	/**
	 * @return the laydescendingpilerange
	 */
	public static int[] getLayDescendingPileRange() {
		int[] layDescendingPileRange = { ServiceRules.getCardRange()[1], ServiceRules.getCardRange()[0] + 1 };
		return layDescendingPileRange;
	}

	/**
	 * 
	 * @return the draw pile size allowed
	 */
	public static int getDrawPileSize() {
		// +1 for the element and not the interval
		return (1 + ServiceRules.getDrawPileRange()[1] - ServiceRules.getDrawPileRange()[0]);
	}

	/**
	 * @return the numberDrawPile
	 */
	public static int getNumberDrawPile() {
		return ServiceRules.numberDrawPile;
	}

	public static int getNumberLayingPile() {
		return (ServiceRules.numberDescendingPile + ServiceRules.numberAscendingPile);
	}

	/**
	 * @return the numberDescendingPile
	 */
	public static int getNumberDescendingPile() {
		return ServiceRules.numberDescendingPile;
	}

	/**
	 * @return the numberAscendingPile
	 */
	public static int getNumberAscendingPile() {
		return ServiceRules.numberAscendingPile;
	}

	/**
	 * 
	 * @return the number of card expected in a game
	 */
	public static int getSizeExpected() {
		// there is one card on each laying piles
		return (ServiceRules.getDrawPileSize() + ServiceRules.getNumberAscendingPile()
				+ ServiceRules.getNumberDescendingPile());
	}

	/**
	 * @return the numberofcardbyturn
	 */
	public static int getNumberOfCardByTurn() {
		return numberOfCardByTurn;
	}
}
