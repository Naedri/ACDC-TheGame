package card;

import direction.Direction;
import services.ServiceRules;

public class Number implements ICard {
	private final int value;

	public Number(int value) {
		if (value < ServiceRules.getCardRange()[0] || value > ServiceRules.getCardRange()[1]) {
			throw new IllegalArgumentException("Card value should be included in the range allowed");
		}
		this.value = value;
	}

	/**
	 * Copy constructors and defensive copying
	 * https://stackoverflow.com/questions/15020850/copy-constructors-and-defensive-copying
	 * 
	 * @param c card
	 * @return new card copied by defensive way
	 */
	public Number(ICard card) {
		// this(card.getValue());
		this.value = card.getValue();
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return Integer.toString(this.getValue());
	}

	@Override
	public int getSuperUp() {
		return this.getValue() + Direction.SUPER_UP.getDRow();
	}

	@Override
	public int getSuperDown() {
		return this.getValue() + Direction.SUPER_DOWN.getDRow();
	}

	@Override
	public int getUp() {
		return this.getValue() + Direction.UP.getDRow();
	}

	@Override
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
		// typecast o to Number n so that we can compare data members
		Number n = (Number) o;
		// Compare the data members and return accordingly
		return (this.getValue() == n.getValue());
	}

	@Override
	public int compareTo(ICard card) {
		if (card == null) {
			return (this.getValue());
		}
		return (this.getValue() - card.getValue());
	}
}
