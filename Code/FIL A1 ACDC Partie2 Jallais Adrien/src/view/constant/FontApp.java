package view.constant;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public enum FontApp {
	LITTLE(11), MEDIUM(13), HIGH(15), SUBTITLE(25), TITLE(45);

	private double size;

	private FontApp(double size) {
		this.size = size;
	}

	public double getSize() {
		return this.size;
	}

	public Font getFont() {
		return Font.font("Arial", FontWeight.BOLD, this.getSize());
	}
}
