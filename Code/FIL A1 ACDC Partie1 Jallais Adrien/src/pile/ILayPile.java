package pile;

import card.ICard;
import direction.Direction;

public interface ILayPile {

	/**
	 * Indicates the current card
	 * 
	 * @return the last card played or the card from the beginning
	 */
	public ICard read();

	/**
	 * Lay the given card
	 * 
	 * @param card
	 */
	public boolean lay(ICard card);

	/**
	 * Indicates if an associated backward ICard exists for the given card
	 * 
	 * @return true if the card >= 11 or card <= 90 according the direction up or
	 *         down respectively
	 */
	public boolean isBackwardsAllowed();

	/**
	 * Indicates the matching card to allow backWard tricks
	 * 
	 * @return card
	 */
	public ICard readBackwardsAllowed();

	/***
	 * get the number of the card included in the pile
	 * 
	 * @return
	 */
	public int getSize();

	/**
	 * Is the laying deck full ? (Does the last card match with readThresholdMax ?)
	 * 
	 * @return
	 */
	public boolean isFull();

	/**
	 * May we lay the given ICard
	 * 
	 * @param card
	 * @return
	 */
	public boolean isLayable(ICard card);

	/**
	 * Ho many card can we lay at maximum ?
	 * 
	 * @return
	 */
	public int getRemainCards();

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
	 * 
	 * @return true if the laying pile contains only its card from the beginning
	 */
	public boolean reset();

	/**
	 * is it an ascending pile or a descending one ?
	 * 
	 * @return
	 */
	public Direction getDirection();

	/**
	 * syso the lay info for Human console
	 * 
	 */
	public void print();

	/**
	 * 
	 * @return same value as print() but in string
	 * 
	 */
	public String toString();

}
