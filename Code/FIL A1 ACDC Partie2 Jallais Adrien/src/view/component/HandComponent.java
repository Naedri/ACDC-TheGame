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
import javafx.scene.shape.Rectangle;
import view.constant.ColorApp;
import view.constant.RadiusApp;

/**
 * @author Adrien Jallais
 *
 */
public class HandComponent extends HBox implements IClickable {

	private List<CardComponent> cardL;
	private List<StackPane> stackL;
	private CardComponent cardSelected;
	private boolean clickable;
	private final int numberCardsBegin;

	public HandComponent(List<CardComponent> listCard, double spacing, int numberCardsBegin) {
		super(spacing);
		initHand(listCard);
		setStyle();
		setAction();
		this.cardSelected = null;
		this.clickable = true;
		this.numberCardsBegin = numberCardsBegin;
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
		for (int i = cardL.size(); i < this.numberCardsBegin; i++) {
			System.out.println("not enough cards in the hand of the player"); // TODO ERASE
			// not enough cards in the hand of the player
			Rectangle rect = cardL.get(0).makeSupport();
			StackPane sp = new StackPane();
			sp.getChildren().add(rect);
			stackL.add(sp);
		}
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
		this.setCardSelected(null);
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
		return clickable;
	}

	@Override
	public void setClickable(Boolean clickable) {
		this.clickable = clickable;
	}

}
