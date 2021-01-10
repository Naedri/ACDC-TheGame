/**
 * 
 */
package view.button;

import application.Main;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import view.constant.BordW;
import view.constant.ColorApp;
import view.constant.RadiusApp;

/**
 * @author Adrien Jallais
 *
 */
public class ButtonQuit extends AButton {
	private static String label = Main.d.get("COMMON_exit");

	// initializers
	{
		this.setTextFill(ColorApp.BADD.getColor());
		this.setBorder(new Border(new BorderStroke(ColorApp.BADD.getColor(), BorderStrokeStyle.SOLID,
				new CornerRadii(RadiusApp.MEDIUM.getRadius()), new BorderWidths(BordW.HIGH.getWidth()))));
	}

	/**
	 * @param text
	 */
	public ButtonQuit(String text) {
		super(text);
	}

	public ButtonQuit() {
		super(label);

	}
}
