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
	private final int numberCardsBegin;

	public HandComponent(List<CardComponent> listCard, double spacing, int numberCardsBegin) {
		super(spacing);
		initHand(listCard);
		setStyle();
		setAction();
		this.numberCardsBegin = numberCardsBegin;
	}

	public HandComponent(List<CardComponent> listCard, int numberCardsBegin) {
		super();
		initHand(listCard);
		setStyle();
		setAction();
		this.numberCardsBegin = numberCardsBegin;
	}

	/**
	 * Adding cardComponent with its support to Hand
	 * 
	 * @param listCard
	 */
	private void initHand(List<CardComponent> listCard) {
		this.cardL = listCard;
		// to prepare the blank place
//		if (cardL.size() < numberCardsBegin) {
//			CardComponent card = new CardComponent(new Carte(-6666));
//			int cardsToAdd = numberCardsBegin - cardL.size();
//			for (int i = 0; i < cardsToAdd; i++) {
//				this.cardL.add(card);
//			}
//		}
		this.stackL = new ArrayList<StackPane>();
		// CardComponent = a stack with the card and its support
		cardL.forEach(card -> {
			StackPane sp = card.makeSupported();
			this.stackL.add(sp);
		});
		// HBox = all the hand
		stackL.forEach(stack -> {
			this.getChildren().add(stack);
		});
		// to make the blank place
//		cardL.forEach(card -> {
//			if (card.getCardAPI().getValeur() == -6666) {
//				StackPane sp = card.makeSupported();
//				this.stackL.remove(sp);
//				cardL.remove(card);
//			}
//		});

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
							card.setActive(false);
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
//		this.setCardSelected(null);
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
