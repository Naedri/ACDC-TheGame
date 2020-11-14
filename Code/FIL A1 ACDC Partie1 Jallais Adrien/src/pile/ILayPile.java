package pile;

import card.ICard;

public interface ILayPile {

	/**
	 * Indicates from which card you can lay
	 * 
	 * @return card
	 */
	public ICard readThresholdMin();

	/**
	 * Indicates until which card you can lay
	 * 
	 * @return card
	 */
	public ICard readThresholdMax();

	/**
	 * Indicates the matching card to allow backWard tricks
	 * 
	 * @return card
	 */
	public ICard readBackwardsAllowed();

	/**
	 * Indicates the current card
	 * 
	 * @return the last card played or the card from the beginning
	 */
	public ICard read();

	/**
	 * is it an ascending pile or a descending one ?
	 * 
	 * @return
	 */
//	public Direction getDirection();

	/**
	 * Indicates if we can lay a card or not
	 * 
	 * @param card
	 * @return
	 */
	public boolean isLayable(ICard card);

	/**
	 * Lay the given card
	 * 
	 * @param card
	 */
	public boolean lay(ICard card);

	/**
	 * Is the laying deck full ? (Does the last card match with readThresholdMax ?)
	 * 
	 * @return
	 */
	public boolean isFull();

}
