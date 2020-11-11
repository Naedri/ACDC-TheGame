package pile;

import java.util.Collections;
import java.util.Set;

import card.ICard;

public class Hand extends APile implements IHand {
	Set<ICard> set;

	public Set<ICard> getImmuableHands() {
		return Collections.unmodifiableSet(set);
	}

	private void sortCard();
}
