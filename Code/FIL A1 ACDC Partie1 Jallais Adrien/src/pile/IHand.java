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
	 * Is the List of Card empty
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
}