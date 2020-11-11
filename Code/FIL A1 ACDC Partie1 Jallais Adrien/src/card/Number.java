package card;

import direction.Direction;

public class Number implements ICard {
	final int value;

	public Number(int value) {
		if (value < 1 || value > 99) {
			throw new IllegalArgumentException("Card value should be between 1 and 99 included");
		}
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public String toString() {
		return Integer.toString(this.value);
	}

	/**
	 * give the matching upper card allowing backwards trick
	 */
	public int getSuperUp() {
		return this.getValue() + Direction.SUPER_UP.getDRow();
	}

	/**
	 * give the matching lower card allowing backwards trick
	 */
	public int getSuperDown() {
		return this.getValue() + Direction.SUPER_DOWN.getDRow();
	}

	/**
	 * give the matching upper card allowing usual laying action
	 */
	public int getUp() {
		return this.getValue() + Direction.UP.getDRow();
	}

	/**
	 * give the matching upper card allowing usual laying action
	 */
	public int getDown() {
		return this.getValue() + Direction.DOWN.getDRow();
	}
}
