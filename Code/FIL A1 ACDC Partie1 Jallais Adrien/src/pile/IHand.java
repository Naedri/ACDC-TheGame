package pile;

import java.util.List;

import card.ICard;

public interface IHand {

	public List<ICard> readImmuableHands();

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
	public ICard getMinCard();

	/**
	 * What is the highest card from the List
	 * 
	 * @return
	 */
	public ICard getMaxCard();

	/**
	 * to be used by the IGame for the IDrawPile
	 * 
	 * @param c the card to be added
	 */
	public void addCard(ICard c);

	/**
	 * to be used by the IGame for the ILayPile
	 * 
	 * @param c the card to be removed
	 */
	public void removeCard(ICard c);
}