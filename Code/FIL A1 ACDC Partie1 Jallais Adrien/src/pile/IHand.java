package pile;

import java.util.List;

import card.ICard;

public interface IHand {

	/**
	 * Allow to read list of ICard.
	 */
	public List<ICard> read();

	/**
	 * get the number of Card from the List
	 * 
	 * @return
	 */
	public int getSize();

	/**
	 * get the max card allowed by RulesService
	 * 
	 * @return
	 */
	public int getMaxSize();

	/**
	 * according the size of its List Card and RulesService
	 * 
	 * @return
	 */
	public boolean isFull();

	/**
	 * Is the List of Card empty. Or is there at least one ICard in the hand ?
	 * 
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * What is the lowest card from the List
	 * 
	 * @return
	 */
	public ICard getMin();

	/**
	 * What is the highest card from the List
	 * 
	 * @return
	 */
	public ICard getMax();

	/**
	 * to be used by the IGame for the IDrawPile
	 * 
	 * @param c the card to be added
	 * @return true if card added, false if not
	 */
	public boolean add(ICard card);

	/**
	 * to be used by the IGame for the ILayPile
	 * 
	 * @param c the card to be removed
	 * @return true if card removed, false if not
	 */
	public boolean remove(ICard card);

	/**
	 * remove all the card from a hand
	 * 
	 * @return true if successful
	 */
	public boolean reset();

	/**
	 * 
	 * @return a string array of the value of card
	 */
	public String[] toArray();

	/**
	 * syso the card info for Human console
	 * 
	 */
	public void print();

	/**
	 * 
	 * @param card
	 * @return true if the given card is contained in this hand
	 */
	public boolean contains(ICard card);

}