package pile;

import card.ICard;

public interface IHand {

	public void addCard(ICard card);

	public ICard[] getCard();

	public int getNumberOfCard();

	public int getMaxCapacity();

	public boolean isFull();

	public ICard getMinCard();

	public ICard getMaxCard();

}
