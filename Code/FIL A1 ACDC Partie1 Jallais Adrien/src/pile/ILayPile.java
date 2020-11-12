package pile;

import card.ICard;
import direction.Direction;

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
	 * @return the last card played in readOnlyState or the card from the beginning
	 */
	public ICard readCard();

	/**
	 * is it an ascending pile or a descending one ?
	 * 
	 * @return
	 */
	public Direction getDirection();

	/**
	 * Indicates if we can lay a card or not
	 * 
	 * @param card
	 * @return
	 */
	public boolean isLayable(ICard card);
}
