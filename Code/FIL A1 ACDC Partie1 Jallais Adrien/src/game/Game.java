package game;

import java.util.List;

import pile.IDrawPile;
import pile.IHand;
import pile.ILayPile;

public class Game implements IGame {

	private IDrawPile draw;
	private List<IHand> hands;
	private List<ILayPile> lays;

	public Game(IDrawPile _draw, List<IHand> _hands, List<ILayPile> _lays) {
		this.draw = _draw;
		this.hands = _hands;
		this.lays = _lays;
	}
}
