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
	private CardComponent cardSelected = null;
	private boolean clikable = true;

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
					if (isClickable()) {
						if (card.isActive()) {
							card.switchActive();
							setCardSelected(null);
						} else {
							unSelectCard();
							card.setActive(true);
							setCardSelected(card);
						}
					}
				}
			});
		});
	}

	public void unSelectCard() {
		this.cardL.forEach(card -> {
			card.setActive(false);
		});
	}

	private void initHand(List<CardComponent> listCard) {
		this.cardL = listCard;
		this.stackL = new ArrayList<StackPane>();
		cardL.forEach(card -> {
			StackPane sp = card.makeSupported();
			this.stackL.add(sp);
		});
		stackL.forEach(stack -> {
			this.getChildren().add(stack);
		});
	}

	/**
	 * @return the cardL
	 */
	public List<CardComponent> getCardL() {
		return cardL;
	}

	protected boolean isClickable() {
		return clikable;
	}

	protected void setClickable(Boolean clickable) {
		this.clikable = clickable;
	}

	/**
	 * @return the cardSelected
	 */
	public CardComponent getCardSelected() {
		return cardSelected;
	}

	/**
	 * @param cardSelected the cardSelected to set
	 */
	public void setCardSelected(CardComponent cardSelected) {
		this.cardSelected = cardSelected;
	}

	/**
	 * @return the isOneCardSelected
	 */
	public Boolean isCardSelected() {
		return this.cardSelected != null;
	}

	public void removeCard(CardComponent card) {
		StackPane sp = card.makeSupported();
		this.stackL.remove(sp);
		cardL.remove(card);
	}
}
