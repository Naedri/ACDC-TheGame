package pile;

import java.util.Collections;
import java.util.List;

import card.ICard;
import services.RulesService;

public class Hand implements IHand {
	private List<ICard> list; // should be sortable to help the human client to choose

	/**
	 * Hand can be null if there is no card left.
	 * 
	 * @param set of the cards
	 */
	public Hand(List<ICard> list) {
		assert (list.size() <= RulesService.getHandLength());
		this.list = list;
		this.sort();
	}

	/**
	 * Sort the List of ICard. From the min ICard to the Max ICard
	 */
	private void sort() {
		Collections.sort(this.list);
	}

	@Override
	public List<ICard> read() {
		this.sort();
		return Collections.unmodifiableList(list);
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public int getMaxSize() {
		return RulesService.getHandLength();
	}

	@Override
	public boolean isFull() {
		return this.getSize() >= RulesService.getHandLength();
	}

	@Override
	public boolean isEmpty() {
		return this.getSize() <= 0;
	}

	@Override
	public ICard getMin() {
		this.sort();
		return this.list.get(0);
	}

	@Override
	public ICard getMax() {
		this.sort();
		return this.list.get(this.getSize() - 1);
	}

	@Override
	public boolean add(ICard card) {
		return this.isFull() ? false : this.list.add(card);
	}

	@Override
	public boolean remove(ICard card) {
		return this.list.remove(card);
	}
}
