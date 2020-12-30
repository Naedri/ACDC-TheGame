package view.button;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.constant.Col;
import view.constant.FontS;
import view.constant.Rad;

public abstract class AButton extends Button {

	public AButton(String text) {
		super(text);
		// this.setGraphic(new ImageView(new Image(new FileInputStream("images" +
		// File.separator + "TP1_logo.jpg"))));
		// this.setContentDisplay(ContentDisplay.LEFT);
		this.setPadding(new Insets(10, 30, 10, 30));
		this.setMaxWidth(Double.MAX_VALUE); // to expand at max
		this.setFont(Font.font("Arial", FontWeight.BOLD, FontS.MEDIUM.getSize()));

		// BACKGROUNG
		this.setBackground(new Background(
				new BackgroundFill(Col.INFOD.getColor(), new CornerRadii(Rad.MEDIUM.getRadius()), Insets.EMPTY)));

		// mouse event
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setBackground(new Background(new BackgroundFill(Col.INFOL.getColor(),
						new CornerRadii(Rad.MEDIUM.getRadius()), Insets.EMPTY)));

			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setBackground(new Background(new BackgroundFill(Col.INFOD.getColor(),
						new CornerRadii(Rad.MEDIUM.getRadius()), Insets.EMPTY)));

			}
		});
	}

}
