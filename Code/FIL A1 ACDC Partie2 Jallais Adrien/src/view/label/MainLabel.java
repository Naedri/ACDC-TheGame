/**
 * 
 */
package view.label;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import view.constant.ColorApp;
import view.constant.FontApp;

/**
 * @author Adrien Jallais
 *
 */
public class MainLabel extends Label {

	public MainLabel(String text) {
		super(text);
		this.setFont(FontApp.MEDIUM.getFont());
		this.setTextFill(ColorApp.WHITE.getColor());
		this.setAlignment(Pos.CENTER);
	}
}
