package game;

import card.ICard;

public interface IGame {

	/**
	 * Leave a game, at its end or during its play
	 */
	public void quit();

	/**
	 * restart the game
	 */
	public void restart();

	/**
	 * full the hand until its maximum
	 */
	public void draw();

	/**
	 * laying a card on a ASCending Pile
	 * 
	 * @param pileIndice from 1
	 * @param c          the card to be layed
	 */
	public void layAP(int pileIndice, ICard c);

	/**
	 * laying a card on a DESCending Pile
	 * 
	 * @param pileIndice from 1
	 * @param c          the card to be layed
	 */
	public void layDP(int pileIndice, ICard c);

}
