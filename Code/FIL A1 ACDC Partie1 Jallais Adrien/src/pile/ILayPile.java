package pile;

import card.ICard;
import direction.Direction;

public interface ILayPile {

	/**
	 * Indicates from which card you can lay
	 * 
	 * @return card
	 */
	public ICard getThresholdMin();

	/**
	 * Indicates until which card you can lay
	 * 
	 * @return card
	 */
	public ICard getThresholdMax();

	/**
	 * Indicates the matching card to allow backWard tricks
	 * 
	 * @return card
	 */
	public ICard getBackwards();

	/**
	 * Indicates the current card
	 * 
	 * @return
	 */
	public ICard getCard();

	public Direction getDirection();

	/**
	 * Indicates if we can lay a card or not
	 * 
	 * @param card
	 * @return
	 */
	public boolean isValid(ICard card);
}
