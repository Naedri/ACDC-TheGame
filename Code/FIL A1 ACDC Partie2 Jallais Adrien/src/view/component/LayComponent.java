package view.component;

import api.Tas;
import api.TasAscendant;
import api.TasDescendant;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.constant.ColorApp;

public class LayComponent extends CardComponent {

	Color col;
	Color colShadow;
	private int index;

	public LayComponent(Tas tas, int indexFromGame) {
		super(tas.getDerniereCarte());
		this.index = indexFromGame;
		this.col = initColorShadow(tas);
		this.colShadow = initColorShadow(tas);
	}

	/**
	 * Allow to get the color according the given tas, in order to be used fot the
	 * Drop Shadow
	 * 
	 * @param tas
	 * @return
	 */
	private Color initColorShadow(Tas tas) {
		if (tas instanceof TasAscendant) {
			return ColorApp.BADD.getColor();
		} else if (tas instanceof TasDescendant) {
			return ColorApp.BADL.getColor();
		} else {
			return ColorApp.BLACK.getColor();
		}
	}

	@Override
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
		return sp;
	}

	/**
	 * Creating shadow for lay components
	 * 
	 * @source https://www.tutorialspoint.com/javafx/drop_shadow_effect.htm
	 * @return DropShadow
	 */
	public DropShadow makingShadow() {
		DropShadow dropShadow = new DropShadow();
		// setting the type of blur for the shadow
		dropShadow.setBlurType(BlurType.GAUSSIAN);
		// Setting color for the shadow
		dropShadow.setColor(this.colShadow);
		dropShadow.setHeight(5);
		dropShadow.setWidth(5);
		dropShadow.setRadius(5);
		// setting the offset of the shadow
		dropShadow.setOffsetX(3);
		dropShadow.setOffsetY(2);
		// Setting the spread of the shadow
		dropShadow.setSpread(12);
		return dropShadow;
	}

	/**
	 * To be used in the collaboration of the API
	 * 
	 * @return the index defined at creation
	 */
	public int getIndex() {
		return index;
	}
}
