package pile;

import java.util.Deque;

import card.ICard;
import services.RulesService;

/**
 * Only one instance of this class. Thus we use the singleton pattern.
 * 
 * @author Adrien Jallais
 *
 */
public class DrawPile implements IDrawPile {
	private Deque<ICard> pile;
	private static DrawPile _instance = null;

	private DrawPile(Deque<ICard> cardDrawPile) {
		assert (cardDrawPile.size() == RulesService.getDrawPileSize());
		this.pile = cardDrawPile;
	}

	/**
	 * The instance method
	 * 
	 * @param cardDrawPile
	 * @return
	 */
	public static DrawPile Instance(Deque<ICard> cardDrawPile) {
		if (_instance == null) {
			_instance = new DrawPile(cardDrawPile);
		}
		return _instance;
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
