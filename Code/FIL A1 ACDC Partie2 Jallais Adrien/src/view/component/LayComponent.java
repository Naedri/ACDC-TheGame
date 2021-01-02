package view.component;

import api.Tas;
import api.TasAscendant;
import api.TasDescendant;
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
	private int index;
	private Tas tas;

	public LayComponent(Tas tas, int indexFromGame) {
		super(tas.getDerniereCarte());
		this.tas = tas;
		this.index = indexFromGame;
		this.col = getInitColor(tas);
		this.colShadow = getInitColor(tas);

	}

	private Color getInitColor(Tas tas) {
		if (tas instanceof TasAscendant) {
			return ColorApp.BADD.getColor();
		} else if (tas instanceof TasDescendant) {
			return ColorApp.BADL.getColor();
		} else {
			return ColorApp.BLACK.getColor();
		}
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
		// TODO erase if event listener added
		this.setText(String.valueOf(selectedCard.getCardAPI().getValeur()));
		this.setCardAPI(selectedCard.getCardAPI());
	}

	/**
	 * @return the index defined at creation
	 */
	public int getIndex() {
		return index;
	}
}
