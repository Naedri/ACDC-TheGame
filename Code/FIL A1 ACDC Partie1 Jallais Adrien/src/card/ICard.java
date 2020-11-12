package card;

public interface ICard {

	/**
	 * 
	 * @return the int value of the card
	 */
	public int getValue();

	/**
	 * give the matching upper card allowing backwards trick
	 */
	public int getSuperUp();

	/**
	 * give the matching lower card allowing backwards trick
	 */
	public int getSuperDown();

	/**
	 * give the matching upper card allowing usual laying action
	 */
	public int getUp();

	/**
	 * give the matching upper card allowing usual laying action
	 */
	public int getDown();

	/**
	 * Overriding equals() to compare two Complex objects
	 */
	public boolean equals(Object o);
}
