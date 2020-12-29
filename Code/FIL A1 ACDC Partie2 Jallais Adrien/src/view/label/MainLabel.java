/**
 * 
 */
package view.label;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.constant.Col;
import view.constant.FontS;

/**
 * @author Adrien Jallais
 *
 */
public class MainLabel extends Label {

	public MainLabel(String text) {
		super(text);
		this.setFont(Font.font("Arial", FontWeight.BOLD, FontS.LITTLE.getSize()));
		this.setTextFill(Col.WHITE.getColor());
		this.setAlignment(Pos.CENTER);
	}
}
