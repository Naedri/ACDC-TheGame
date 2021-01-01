/**
 * 
 */
package view.component;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import view.constant.ColorApp;
import view.constant.RadiusApp;

/**
 * @author Adrien Jallais
 *
 */
public class HandComponent extends HBox {
	private List<CardComponent> cardL;
	private List<StackPane> stackL;
	private Boolean isOneCardActivated = false;

	public HandComponent(List<CardComponent> listCard, double spacing) {
		super(spacing);
		initHand(listCard);
		setStyle();
		setAction();
	}

	public HandComponent(List<CardComponent> listCard) {
		super();
		initHand(listCard);
		setStyle();
		setAction();
	}

	private void setStyle() {
		this.setBackground(new Background(new BackgroundFill(ColorApp.INFOL.getColor(),
				new CornerRadii(RadiusApp.HIGH.getRadius()), Insets.EMPTY)));
	}

	private void setAction() {
		cardL.forEach(card -> {
			card.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (card.isActive()) {
						card.switchActive();
					} else {
						cardL.forEach(card -> {
							card.setActive(false);
						});
						card.setActive(true);
					}
				}
			});
		});
	}

	private void initHand(List<CardComponent> listCard) {
		this.cardL = listCard;
		this.stackL = new ArrayList<StackPane>();
		isOneCardActivated = false;

		cardL.forEach(card -> {
			StackPane sp = card.makeSupported();
			this.stackL.add(sp);
		});

		stackL.forEach(stack -> {
			this.getChildren().add(stack);
		});

	}
}
