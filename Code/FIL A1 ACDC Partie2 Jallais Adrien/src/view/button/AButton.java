package view.button;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.Col;

public abstract class AButton extends Button {

	public AButton(String text) {
		super(text);
		// this.setGraphic(new ImageView(new Image(new FileInputStream("images" +
		// File.separator + "TP1_logo.jpg"))));
		// this.setContentDisplay(ContentDisplay.LEFT);
		this.setPadding(new Insets(10, 60, 10, 60));
		this.setMaxWidth(Double.MAX_VALUE); // to expand at max
		this.setFont(Font.font("Arial", FontWeight.BOLD, 11.0));
		this.setTextFill(Col.GOODD);
		this.setBorder(new Border(
				new BorderStroke(Col.GOODD, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3.0))));
		this.setBackground(new Background(new BackgroundFill(Col.INFOD, CornerRadii.EMPTY, Insets.EMPTY)));
	}

}
