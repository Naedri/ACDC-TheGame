/**
 * 
 */
package pile;

import java.util.Deque;
import java.util.LinkedList;

import card.ICard;
import card.Number;
import direction.Direction;
import services.RulesService;

/**
 * @author Adrien Jallais
 *
 */
public abstract class CALayPile implements ILayPile {

	private Deque<ICard> deque;
	private Direction direction;

	public CALayPile(Direction direction) {
		this.direction = direction;
		this.deque = new LinkedList<ICard>();
		switch (this.direction) {
		case DOWN: {
			// Descending Pile
			this.add(new Number(RulesService.getLayDescendingPileRange()[0]));
		}
		case UP: {
			// Ascending Pile
			this.add(new Number(RulesService.getLayAscendingPileRange()[0]));
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.direction);
		}
	}

	// shortcut
	private ICard get() {
		return this.deque.getFirst();
	}

	// AC Method
	/**
	 * adding card to the deque
	 * 
	 * @param c
	 * @return
	 */
	private boolean add(ICard card) {
		assert (card != null);
		return this.deque.add(card);
	}

	/**
	 * should return new in order to avoid access
	 */
	@Override
	public ICard read() {
		return new Number(this.get().getValue());
	}

	@Override
	public boolean lay(ICard card) {
		return isLayable(card) ? this.add(card) : false;
	}

	// CA Method
	@Override
	public ICard readBackwardsAllowed() throws IllegalArgumentException {
		switch (this.direction) {
		case DOWN: {
			// Descending Pile
			return new Number(this.get().getSuperUp());
		}
		case UP: {
			// Ascending Pile
			return new Number(this.get().getSuperDown());
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.direction);
		}
	}

	@Override
	public boolean isFull() {
		return this.get().getValue() >= this.readThresholdMax().getValue();
	}

	@Override
	public ICard readThresholdMin() {
		if (this.isFull()) {
			return new Number(this.get().getValue());
		} else {
			return new Number(this.get().getValue() + this.direction.getDRow());
		}
	}

	@Override
	public ICard readThresholdMax() throws IllegalArgumentException {
		switch (this.direction) {
		case DOWN: {
			// Descending Pile
			return (new Number(RulesService.getLayDescendingPileRange()[1]));
		}
		case UP: {
			// Ascending Pile
			return (new Number(RulesService.getLayAscendingPileRange()[1]));
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.direction);
		}
	}

	@Override
	public boolean isLayable(ICard card) {
		assert (card != null);
		switch (this.direction) {
		case DOWN: {
			// Descending Pile
			// current - card > 0
			return this.get().compareTo(card) > 0;
		}
		case UP: {
			// Ascending Pile
			// current - card > 0
			return this.get().compareTo(card) < 0;
		}
		default:
			return false;
		}
	}

}
