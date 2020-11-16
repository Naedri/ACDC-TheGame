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
	 * syso the readLaysInfo for Human console
	 */
	public void printLays();

	/**
	 * syso the current Hand for Human console
	 */
	public void printHand();

	/**
	 * syso the printLays + printHand, for Human console
	 */
	public void print();

	/**
	 * For the client : Allow to read information about the current playing hand
	 * 
	 * @return
	 */
	public List<ICard> readHand();

	/**
	 * 
	 * @return true if there is at least one card from the hand which can be layed
	 *         on at least one laying pile
	 */
	public boolean canHandLay();

	/**
	 * return a table of the same size than the laying piles (ascending and
	 * descending ones) for each their capacity to lay the given card is evaluated
	 * 
	 * @param card to be evaluated
	 * @return a table of boolean, of which index match with the same pileIndex from
	 *         the list of ILay
	 */
	public boolean[] mayBeLay(ICard c);

	/**
	 * give int list where to lay card
	 * 
	 * @param card
	 * @return a list of index from the list of laying pile, where to lay the given
	 *         card
	 */
	public List<Integer> whereToLay(ICard card);

	/**
	 * laying a card on a ASCending OR DESCending Pile. Should check if card is from
	 * Hand
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
	 * To use at the beginning or ending of a turn
	 * 
	 * @return true if the game is blocked (winner or looser) ; false if the player
	 *         can still continue to play
	 */
	public boolean isHandBlocked();

	/**
	 * restart the game
	 */
	public void restart();

	/**
	 * Stop playing after Winning or Loosing. Restart still possible
	 * 
	 */
	public void close();

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
	 * To organize the succession as a mediator of the functions from the Game
	 * Class.
	 * 
	 * @return
	 */
	public void play();
}
