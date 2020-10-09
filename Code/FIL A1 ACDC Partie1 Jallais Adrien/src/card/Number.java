package card;

public class Number implements ICard {
	int value ;

	public Number(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
