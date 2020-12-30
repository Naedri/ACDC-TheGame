package view.constant;

public enum Spacing {
	LITTLE(8), MEDIUM(16), HIGH(32);

	private int space;

	private Spacing(int size) {
		this.space = size;
	}

	public double getSpace() {
		return this.space;
	}
}
