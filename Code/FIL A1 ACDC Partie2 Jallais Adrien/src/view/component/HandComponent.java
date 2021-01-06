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
public class HandComponent extends HBox implements IClickable {

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

	/**
	 * Adding cardComponent with its support to Hand
	 * 
	 * @param listCard
	 */
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
	 * Adding specific style to the component
	 */
	private void setStyle() {
		this.setBackground(new Background(new BackgroundFill(ColorApp.INFOL.getColor(),
				new CornerRadii(RadiusApp.HIGH.getRadius()), Insets.EMPTY)));
	}

	/**
	 * adding action to each cardComponent from the hand
	 */
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
							unSelectCards();
							card.setActive(true);
							setCardSelected(card);
						}
					}
				}
			});
		});
	}

	/**
	 * allow to unselect all cards added to the hand
	 */
	public void unSelectCards() {
		this.cardL.forEach(card -> {
			card.setActive(false);
		});
	}

	/**
	 * @return the cardL List of Card component
	 */
	public List<CardComponent> getCardL() {
		return cardL;
	}

	/**
	 * remove the given card from the hand
	 * 
	 * @param card
	 */
	public void removeCard(CardComponent card) {
		StackPane sp = card.makeSupported();
		this.stackL.remove(sp);
		cardL.remove(card);
	}

	/**
	 * @return true if cardSelected != null, if not false
	 */
	public Boolean isCardSelected() {
		return this.cardSelected != null;
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

	@Override
	public boolean isClickable() {
		return clikable;
	}

	@Override
	public void setClickable(Boolean clickable) {
		this.clikable = clickable;
	}

}
