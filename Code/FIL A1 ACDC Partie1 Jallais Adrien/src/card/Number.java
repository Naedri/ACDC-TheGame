package card;

import direction.Direction;

public class Number implements ICard {
	int value;

	public Number(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public int getSuperUp() {
		return this.getValue() + Direction.SUPER_UP.getDRow();
	}

	public int getSuperDown() {
		return this.getValue() + Direction.SUPER_DOWN.getDRow();
	}

	public int getUp() {
		return this.getValue() + Direction.UP.getDRow();
	}

	public int getDown() {
		return this.getValue() + Direction.DOWN.getDRow();
	}

}
