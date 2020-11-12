package pile;

import java.util.Collections;
import java.util.List;

import card.ICard;

public class Hand implements IHand {
	private List<ICard> list;

	/**
	 * Hand can be null if there is no card left
	 * 
	 * @param set of the cards
	 */
	public Hand(List<ICard> list) {
		Collections.sort(list);
		this.list = list;
	}

	/**
	 * to be use by the client
	 */
	public List<ICard> readImmuableHands() {
		return Collections.unmodifiableList(list);
	}

	/**
	 * to be used by the IDrawPile
	 * 
	 * @param c
	 */
	protected void addCard(ICard c) {

	}

	/**
	 * to be used by the ILayPile
	 * 
	 * @param c
	 */
	protected void removeCard(ICard c) {

	}
}
