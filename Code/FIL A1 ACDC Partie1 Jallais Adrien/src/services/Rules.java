package services;

/**
 * @author Adrien Jallais
 * @source https://stackoverflow.com/questions/7486012/static-classes-in-java
 */
public final class Rules {

	private static int handLength = 5;
	private static int playerNumber = 1;
	private static int[] cardRange = new int[] { 1, 99 };
	private static int[] drawPileRange = Rules.cardRange;

	/*
	private Rules() {
		Rules.cardRange = new int[] { 1, 99 };
		Rules.playerNumber = 1;
		Rules.drawPileRange = Rules.cardRange;
		Rules.handLength = 5;
	}
	*/

	/**
	 * @return the playerNumber
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * @return the drawPileRange
	 */
	public static int[] getDrawPileRange() {
		return drawPileRange;
	}

	/**
	 * @return the handLength number of card in the hand of ONE player
	 */
	public int getHandLength() {
		return handLength;
	}

	/**
	 * @return the cardRange [0] is the min [1] is the max
	 */
	public static int[] getCardRange() {
		return cardRange;
	}

}
