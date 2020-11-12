package pile;

import java.util.Collections;
import java.util.Set;

import card.ICard;

public class Hand implements IHand {
	Set<ICard> set;

	public Set<ICard> getImmuableHands() {
		return Collections.unmodifiableSet(set);
	}

	/**
	 * allow to sort by value the given card
	 */
	private void sortCard() {

	}
}
