package pile;

import java.util.Deque;

import card.ICard;

public class DrawPile implements IDrawPile {
	private Deque<ICard> pile;

	public DrawPile(Deque<ICard> cardDrawPile) {
		this.pile = cardDrawPile;
	}

	@Override
	public ICard draw() {
		return this.pile.pop();
	}

	@Override
	public boolean isEmpty() {
		return this.pile.size() <= 0;
	}

	@Override
	public int getSize() {
		return pile.size();
	}
}
