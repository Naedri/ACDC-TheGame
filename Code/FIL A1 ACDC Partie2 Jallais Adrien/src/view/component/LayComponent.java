package view.component;

import javafx.geometry.Insets;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.constant.ColorApp;

public class LayComponent extends CardComponent {

	Color col;
	Color colShadow;
	Boolean ascending;

	public LayComponent(int value, Color color, Boolean ascending) {
		super(value);
		this.col = color;
		this.colShadow = color;
		this.ascending = ascending;
	}

	public LayComponent(int value, ColorApp color, Boolean ascending) {
		super(value);
		this.col = color.getColor();
		this.colShadow = color.getColor();

	}

	public Rectangle makeSupport() {
		Rectangle rect = new Rectangle(this.getPrefWidth() * 1.2, this.getPrefHeight() * 1.2, this.col);
		rect.setArcHeight(10);
		rect.setArcWidth(100);
		rect.setEffect(makingShadow());
		return rect;
	}

	@Override
	public StackPane makeSupported() {
		StackPane sp = new StackPane();
		sp.getChildren().addAll(this.makeSupport(), this);
		// TODO Erase
		sp.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return sp;
	}

	/**
	 * creating shadow for lay components
	 * 
	 * @source https://www.tutorialspoint.com/javafx/drop_shadow_effect.htm
	 * @return DropShadow
	 */
	public DropShadow makingShadow() {
		// Instantiating the Shadow class
		DropShadow dropShadow = new DropShadow();
		// setting the type of blur for the shadow
		dropShadow.setBlurType(BlurType.GAUSSIAN);
		// Setting color for the shadow
		dropShadow.setColor(this.colShadow);
		// Setting the height of the shadow
		dropShadow.setHeight(5);
		// Setting the width of the shadow
		dropShadow.setWidth(5);
		// Setting the radius of the shadow
		dropShadow.setRadius(5);
		// setting the offset of the shadow
		dropShadow.setOffsetX(3);
		dropShadow.setOffsetY(2);
		// Setting the spread of the shadow
		dropShadow.setSpread(12);

		return dropShadow;
	}

	public void addingCard(CardComponent selectedCard) {
		// TODO Auto-generated method stub

	}
}
