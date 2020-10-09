package card;

public abstract class ACard {
	private int value ;

	public int getValue() {
		return value;
	}
	
	public ACard(int value) {
		this.value = value ;
	}
}
