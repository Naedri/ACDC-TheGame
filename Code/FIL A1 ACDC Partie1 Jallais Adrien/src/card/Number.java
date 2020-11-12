package card;

import direction.Direction;
import services.Rules;

public class Number implements ICard {
	final int value;

	public Number(int value) {
		if (value < Rules.getCardRange()[0] || value > Rules.getCardRange()[1]) {
			throw new IllegalArgumentException("Card value should be included in the range allowed");
		}
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public String toString() {
		return Integer.toString(this.value);
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

	@Override
	public boolean equals(Object o) {
		// https://www.geeksforgeeks.org/overriding-equals-method-in-java/
		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Complex or not "null instanceof [type]" also
		 * returns false
		 */
		if (!(o instanceof Number)) {
			return false;
		}

		// typecast o to Number so that we can compare data members
		Number n = (Number) o;

		// Compare the data members and return accordingly
		return Double.compare(this.getValue(), n.getValue()) == 0;
	}
}
