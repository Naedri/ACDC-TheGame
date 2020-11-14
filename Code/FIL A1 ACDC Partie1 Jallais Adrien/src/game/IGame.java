package game;

import java.util.List;

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
	 * full the hand until its maximum. Ends the turn. Ends the game : if no card
	 * where played OR if the draw pile is empty
	 * 
	 * @return the number of drawed cards
	 */
	public int endTurn();

	/**
	 * Allow to read information about the lay piles
	 * 
	 * @return array | Indice | Pile Direction | Current Card |
	 */
	public String[][] readLayInfo();

	/**
	 * Allow to read information about the current playing hand
	 * 
	 * @return
	 */
	public List<ICard> readHand();

	/**
	 * laying a card on a ASCending OR DESCending Pile
	 * 
	 * @param pileIndice from 0 to size of the List of LayPile
	 * @param c          the card to be layed
	 * @return true if success, false if do not lay
	 */
	public boolean lay(int pileIndice, ICard card);

	/**
	 * the lowest score you could ever have
	 * 
	 * @return
	 */
	public int getMinScore();

	/**
	 * Did the player win ?
	 * 
	 * @ return true if the player has played all cards from the draw pile and from
	 * his hands.
	 */
	public boolean isVictory();

	/**
	 * Clean the game
	 */
	public void cleanGame();

}
