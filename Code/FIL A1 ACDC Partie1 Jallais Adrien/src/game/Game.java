package game;

import pile.*;

public class Game implements IGame {
	
	private IDrawPile draw;	
	private IHand[] hands;
	private ILayPile[] lays;
	
	public Game(IDrawPile _draw, IHand[] _hands, ILayPile[] _lays) {
		this.draw = _draw;
		this.hands = _hands;
		this.lays = _lays;
	}
}
