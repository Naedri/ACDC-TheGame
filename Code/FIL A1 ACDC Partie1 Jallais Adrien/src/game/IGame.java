package game;

import java.util.List;

import card.ICard;
import game.Game.LayInfo;

public interface IGame {

	/**
	 * 
	 * @return the index of the current player
	 */
	public int beginTurn();

	/**
	 * For the client : Allow to read information about the different lay piles : |
	 * LayPile_index | LayPile_direction | LayPile_Card |
	 * https://stackoverflow.com/questions/19602601/create-an-arraylist-with-multiple-object-types
	 * 
	 * @return List<Integer, Direction, ICard>
	 */
	public List<LayInfo> readLaysInfo();

	/**
	 * For the client : Allow to read information about the current playing hand
	 * 
	 * @return
	 */
	public List<ICard> readHand();

	/**
	 * return a table of the same size than the laying piles (ascending and
	 * descending ones) for each their capacity to lay the given card is evaluated
	 * 
	 * @param card to be evaluated
	 * @return a table of boolean, of which index match with the same pileIndex from
	 *         the list of ILay
	 */
	public boolean[] whereToLay(ICard c);

	/**
	 * laying a card on a ASCending OR DESCending Pile
	 * 
	 * @param pileIndex from 0 to size of the List of LayPile
	 * @param card      the card to be layed
	 * @return true if success, false if do not lay
	 */
	public boolean lay(int pileIndex, ICard card);

	/**
	 * full the hand until its maximum. Ends the turn. Ends the game : if no card
	 * where played OR if the draw pile is empty
	 * 
	 * @return the number of drawed cards, if 0 the game is closed (win or loose)
	 */
	public int endTurn();

	/**
	 * the lowest score you could ever have
	 * 
	 * @return
	 */
	// public int getMinScore();

	/**
	 * restart the game
	 */
	public void restart();

	/**
	 * Stop playing, but restart still possible
	 * 
	 * @return the number of points
	 */
	public int close();

	/**
	 * Did the player win ?
	 * 
	 * @return true if the player has played all cards from the draw pile and from
	 *         his hands.
	 */
	public boolean isVictory();

	/**
	 * Leave a game, at its end or during its play. Stop playing for ever, you can
	 * not restart the play.
	 */
	public void quit();

	/**
	 * Clean the game
	 */
	// public void cleanGame();

}
