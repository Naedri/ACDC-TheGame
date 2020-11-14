package pile;

import card.ICard;

public interface IDrawPile {
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

	/**
	 * allow to reset from the beginning a draw pile
	 * 
	 */
	public boolean reset();

	/**
	 * is the draw size is equals to the Service Rules Size
	 * 
	 * @return true if convenient
	 */
	public boolean isSizeValid();

}
