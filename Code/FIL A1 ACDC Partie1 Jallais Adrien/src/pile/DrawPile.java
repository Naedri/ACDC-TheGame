package pile;

import java.util.Deque;

import card.ICard;

public class DrawPile extends APile implements IDrawPile {
	// private Deque<ICard> cards;
	private Deque<ICard> pile;

	public DrawPile(Deque<ICard> cardDrawPile) {
		super(cardDrawPile);
		this.pile = cardDrawPile;
	}

	@Override
	public ICard draw() {
		// return ((Deque<ICard>) super.cards).pop();
		return this.pile.pop();
	}

}
