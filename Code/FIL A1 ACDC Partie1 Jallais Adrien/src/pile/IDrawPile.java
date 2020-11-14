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
	 * Add a card to the hand and consume it. The hand is not declared as there is
	 * only one hand
	 * 
	 * @return the poped ICard
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
