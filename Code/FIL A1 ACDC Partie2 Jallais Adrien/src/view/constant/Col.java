/**
 * 
 */
package view.constant;

import javafx.scene.paint.Color;

/**
 * @author Adrien Jallais
 * @from https://i.pinimg.com/originals/62/26/29/6226296e76f9f3ad44e45fa0a4ac5a2d.jpg
 */

public enum Col {
	BADD("#1e3b39"), BADL("#416b6a"), GOODD("#345d3d"), GOODL("#6e9868"), INFOD("#ada785"), INFOL("#99b4a3"),
	BLACK("#202020"), WHITE("#ffffff");

	private String hexa;

	private Col(String hexa) {
		this.hexa = hexa;
	}

	public String getHexa() {
		return this.hexa;
	}

	public Color getColor() {
		return Color.web(this.hexa);
	}
}
