package pile;

import java.util.Collection;

import card.ICard;

public abstract class APile {
	protected Collection<ICard> cards;

	protected APile(Collection<ICard> cards) {
		this.cards = cards;
	}

	/**
	 * allow an user to know how many card are still in this collection of Pile
	 * 
	 * @return
	 */
	public int getSize() {
		return cards.size();
	}

	/**
	 * allow an user to know if the pile is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.getSize() <= 0;
	}
}
