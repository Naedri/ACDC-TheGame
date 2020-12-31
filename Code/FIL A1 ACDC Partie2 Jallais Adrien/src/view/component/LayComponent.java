package view.component;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LayComponent extends CardComponent implements ICardView {

	Color col;

	public LayComponent(int value, Color color) {
		super(value);
		this.col = color;
	}

	public Rectangle makeSupport() {
		Rectangle rect = new Rectangle(this.getPrefWidth() * 1.2, this.getPrefHeight() * 1.2, this.col);
		rect.setArcHeight(10);
		rect.setArcWidth(100);
		return rect;
	}

	public StackPane makeLaySupported() {
		StackPane sp = new StackPane();
		sp.getChildren().addAll(this.makeSupport(), this);
		// TODO Erase
		sp.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return sp;
	}
}
