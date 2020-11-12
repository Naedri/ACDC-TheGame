package pile;

import java.util.List;

import card.ICard;

public interface IHand {

	public List<ICard> readImmuableHands();

	public int getNumberOfCard();

	public int getMaxCapacity();

	public boolean isFull();

	public boolean isEmpty();

	public ICard getMinCard();

	public ICard getMaxCard();
}
