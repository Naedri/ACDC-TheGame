/**
 * 
 */
package view.constant;

import javafx.scene.paint.Color;

/**
 * @author Adrien Jallais
 * @from https://i.pinimg.com/originals/62/26/29/6226296e76f9f3ad44e45fa0a4ac5a2d.jpg
 */

public enum ColorApp {
	BADD("#1e3b39"), BADL("#416b6a"), GOODD("#345d3d"), GOODL("#6e9868"), INFOD("#ada785"), INFOL("#99b4a3"),
	BLACK("#202020"), WHITE("#ffffff");

	private String hexa;

	private ColorApp(String hexa) {
		this.hexa = hexa;
	}

	public String getHexa() {
		return this.hexa;
	}

	public Color getColor() {
		return Color.web(this.hexa);
	}

	/**
	 * @source https://stackoverflow.com/questions/4672271/reverse-opposing-colors
	 * @return ContrastColor in black and white
	 */
	public Color getContrast() {
		Color color = this.getColor();
		double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
		return y >= 128 ? Color.BLACK : Color.WHITE;
	}

	public Color getInvert() {
		Color color = this.getColor();
		return color.invert();
	}
}
