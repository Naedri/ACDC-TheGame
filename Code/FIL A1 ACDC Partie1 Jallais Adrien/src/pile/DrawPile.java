package pile;

import java.util.Deque;

import card.ICard;
import services.RulesService;

public class DrawPile implements IDrawPile {
	private Deque<ICard> pile;
	private int cardByHand;

	public DrawPile(Deque<ICard> cardDrawPile) {
		assert (cardDrawPile.size() == RulesService.getDrawPileSize());
		this.pile = cardDrawPile;
		this.cardByHand = RulesService.getHandLength();
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
