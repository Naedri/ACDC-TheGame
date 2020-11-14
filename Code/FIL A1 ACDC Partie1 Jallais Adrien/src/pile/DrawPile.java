package pile;

import java.util.Deque;
import java.util.NoSuchElementException;

import card.ICard;
import services.RulesService;

/**
 * ONLY ONE INSTANCE FOR THIS CLASS. Thus we use the singleton pattern.
 * 
 * @author Adrien Jallais
 *
 */
public class DrawPile implements IDrawPile {

	private Deque<ICard> pile;
	private Deque<ICard> pileComplement;
	private static DrawPile _instance = null;

	/**
	 * ONLY ONE INSTANCE FOR THIS CLASS
	 * 
	 * @param cardDrawPile
	 */
	private DrawPile(Deque<ICard> cardDrawPile) {
		this.pile = cardDrawPile;
		assert (this.isSizeValid());
	}

	@Override
	public boolean isSizeValid() {
		return this.pile.size() == RulesService.getDrawPileSize();
	}

	/**
	 * The instance method.
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
	public ICard draw() throws NoSuchElementException {
		ICard c = this.pile.pop();
		this.pileComplement.add(c);
		return c;
	}

	@Override
	public boolean isEmpty() {
		return this.pile.size() <= 0;
	}

	@Override
	public int getSize() {
		return pile.size();
	}

	@Override
	public boolean reset() {
		while (!this.pileComplement.isEmpty()) {
			ICard c = this.pileComplement.pop();
			this.pile.add(c);
		}
		return this.isSizeValid();
	}
}
