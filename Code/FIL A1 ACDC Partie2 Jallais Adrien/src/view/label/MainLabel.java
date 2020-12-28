/**
 * 
 */
package view.label;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.Col;

/**
 * @author Adrien Jallais
 *
 */
public class MainLabel extends Label {

	public MainLabel(String text) {
		super(text);
		this.setFont(Font.font("Arial", FontWeight.BOLD, 11.0));
		this.setTextFill(Col.WHITE);
		this.setAlignment(Pos.CENTER);
	}
}
