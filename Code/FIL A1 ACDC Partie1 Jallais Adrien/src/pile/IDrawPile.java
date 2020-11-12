package pile;

import card.ICard;

public interface IDrawPile {

	/*
	 * public boolean isClean();
	 * 
	 * public boolean isComplete();
	 * 
	 * public boolean isRedundant();
	 * 
	 * public void shuffle();
	 * 
	 * public boolean contains(ICard card);
	 * 
	 * public boolean miss(ICard card);
	 */

	/**
	 * allow an user to consume a card by drawing it
	 * 
	 * @return a card to be consumed
	 */
	public ICard draw();

	/**
	 * allow an user to know if the pile is empty
	 * 
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * allow an user to know how many card are still in this collection of Pile
	 * 
	 * @return
	 */
	public int getSize();
}
