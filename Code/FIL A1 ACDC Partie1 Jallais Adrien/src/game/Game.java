package game;

import java.util.List;

import card.ICard;
import pile.IDrawPile;
import pile.IHand;
import pile.ILayPile;
import services.RulesService;

/**
 * Game will act as a Mediator of the card flow between class of the pile
 * package.
 * 
 * @author Adrien Jallais
 *
 */
public class Game implements IGame {

	private IDrawPile draw;
	private List<IHand> hands;
	private List<ILayPile> lays;
	private int cardByHand;
	private int hI; // handIndice

	public Game(IDrawPile _draw, List<IHand> _hands, List<ILayPile> _lays) {
		this.draw = _draw;
		this.hands = _hands;
		this.lays = _lays;
		this.cardByHand = RulesService.getHandLength();
		this.hI = 0; // to change if more than one player
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		IHand hand = this.hands.get(this.hI);
		if (hand.isFull()) {
			throw new RuntimeException("Hand is full.");
		}
		while (!hand.isFull()) {
			ICard c = draw.draw();
			hand.addCard(c);
		}
	}

	@Override
	public void layAP(int pileIndice, ICard c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void layDP(int pileIndice, ICard c) {
		// TODO Auto-generated method stub

	}
}
