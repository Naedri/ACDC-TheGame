package view.constant;

import javafx.geometry.Insets;

public enum InsetsApp {

	/*
	 * new Insets(InsetsApp.MEDIUM.getTop(), InsetsApp.MEDIUM.getRight(),
	 * InsetsApp.MEDIUM.getBot(), InsetsApp.MEDIUM.getLeft());
	 */

	/* InsetsApp.MEDIUM.getInsets() */

	LITTLE(new Insets(10, 20, 10, 20)), MEDIUM(new Insets(10, 30, 10, 30)), HIGH(new Insets(10, 60, 10, 60));

	private Insets insets;

	private InsetsApp(Insets insets) {
		this.insets = insets;
	}

	public Insets getInsets() {
		return insets;
	}

	public double getTop() {
		return this.getInsets().getTop();
	}

	public double getBot() {
		return this.getInsets().getBottom();
	}

	public double getRight() {
		return this.getInsets().getRight();
	}

	public double getLeft() {
		return this.getInsets().getLeft();
	}
}