package game;

/**
 * The purpose of this class is only to show the result from the
 * ServiceResolution.resolve() method
 * 
 * @author Adrien Jallais
 *
 */
public class Move extends Object {

	public Move(int layPile_index, int card_index) {
		this.LayPile_index = layPile_index;
		this.Card_index = card_index;
	}

	private final int LayPile_index;
	private final int Card_index;

	/**
	 * @return the layPile_index
	 */
	public int getLayPile_index() {
		return this.LayPile_index;
	}

	/**
	 * @return the card_index
	 */
	public int getCard_index() {
		return this.Card_index;
	}

	/**
	 * To be used with System.out.println("| LayPile_index | Card_index |");
	 */
	public String toString() {
		return ("| " + Integer.valueOf(getLayPile_index()) + " | " + Integer.valueOf(getCard_index()) + " |");
	}

	public void print() {
		System.out.println(this.toString());
	}
}