package game;

import java.util.ArrayList;
import java.util.List;

import card.ICard;
import direction.Direction;
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
	private IHand hand;
	// private IHand handToShow;
	private int playerI;
	private boolean victory;
	private int score;

	public Game(IDrawPile _draw, List<IHand> _hands, List<ILayPile> _lays) {
		this.draw = _draw;
		this.hands = _hands;
		this.lays = _lays;
		this.victory = false;
		this.playerI = 0;
		this.hand = this.hands.get(this.playerI);
		this.score = this.getMinScore();
	}

	/**
	 * the lowest score you could ever have
	 * 
	 * @return the sum of the draw pile according the Rules
	 */
	private int getMinScore() {
		int n = RulesService.getDrawPileRange()[1];
		int sum = 0;

		int i = RulesService.getDrawPileRange()[0];
		while (i <= n) {
			sum += i;
			++i;
		}
		return sum;
	}

	@Override
	@Deprecated // we can change the player with get
	public int beginTurn() {
		this.playerI = 0;
		this.hand = this.hands.get(this.playerI);
		return this.playerI;
	}

	/**
	 * An internal class to be used in the list Information for one lay pile
	 * 
	 * @source https://fr.wikibooks.org/wiki/Programmation_Java/Classes_internes
	 * @author Adrien Jallais
	 *
	 */
	protected class LayInfo extends Object {
		private final int index;
		private final Direction direction;
		private final ICard card;

		public LayInfo(int _index, Direction _direction, ICard _card) {
			this.index = _index;
			this.direction = _direction;
			this.card = _card;
		}

		public String toString() {
			return ("| " + Integer.toString(this.index) + " | " + this.direction.toString() + " | "
					+ this.card.toString() + " |");
		}
	}

	@Override
	public List<LayInfo> readLaysInfo() {
		List<LayInfo> gameState = new ArrayList<LayInfo>();
		int i = 0;
		for (final ILayPile lay : this.lays) {
			gameState.add(new LayInfo(i, lay.getDirection(), lay.read()));
			++i;
		}
		return gameState;
	}

	@Override
	public List<ICard> readHand() {
		return this.hand.read();
	}

	@Override
	public boolean[] whereToLay(ICard card) {
		boolean[] layable = new boolean[RulesService.getNumberDescendingPile() + RulesService.getNumberAscendingPile()];
		int i = 0;
		for (final ILayPile lay : this.lays) {
			layable[i] = lay.isLayable(card);
			++i;
		}
		return layable;
	}

	@Override
	public boolean lay(int pileIndex, ICard card) {
		boolean laying = false;
		ILayPile lay = this.lays.get(pileIndex);
		if (isCardFromHand(card)) {
			laying = lay.lay(card);
			if (laying) {
				this.score -= card.getValue();
			}
		}
		return laying;
	}

	/**
	 * to avoid a short cut utilization of the Number constructor
	 * 
	 * @param card
	 * @return true if a card is well from the current hand player
	 */
	private boolean isCardFromHand(ICard card) {
		return false;
	}

	@Override
	public int endTurn() {
		int drawedCards = this.draw();
		if (drawedCards <= 0) {
			this.close();
		}
		return drawedCards;
	}

	/***
	 * take card from the draw pile until the hand full or the draw empty
	 * 
	 * @return
	 */
	private int draw() {
		int drawedCards = 0;
		while (!this.hand.isFull() && !this.draw.isEmpty()) {
			ICard c = draw.draw();
			this.hand.add(c);
			++drawedCards;
		}
		return drawedCards;
	}

	@Override
	public void restart() throws IllegalArgumentException {
		this.draw.reset();
		this.lays.forEach(lay -> {
			lay.reset();
		});
		this.hands.forEach(hand -> {
			hand.reset();
		});
		this.hand = this.hands.get(0);
		this.victory = false;
		this.score = this.getMinScore();
	};

	@Override
	public int close() {
		this.victory = isVictory();
		return this.getScore();
	}

	/**
	 * is the draw pile and the hand empty && there is no missed card
	 * 
	 * @return
	 */
	public boolean isVictory() {
		return (this.getScore() <= 0 && this.isGameComplete());
	}

	// should stay private
	private int getScore() {
		return this.score;
	}

	/**
	 * make sure the amount of card is correct
	 * 
	 * @return
	 */
	private boolean isGameComplete() {
		return (this.getSize() == RulesService.getSizeExpected());
	}

	/**
	 * Give the number of cards included in the game
	 * 
	 * @return
	 */
	private int getSize() {
		// forEach loop not allowed
		int size = this.draw.getSize();
		for (final IHand hand : this.hands) {
			size += hand.getSize();
		}
		for (final ILayPile lay : this.lays) {
			size += lay.getSize();
		}
		return size;
	}

	@Override
	public void quit() {
		this.cleanGame();
		this.victory = false;
		this.score = this.getMinScore();

	}

	/**
	 * 
	 * Clean the game
	 */
	private void cleanGame() {
		this.draw = null;
		this.hands = null;
		this.lays = null;
		this.hand = null;
	}

	/**
	 * Remove the card from a full pile with laying pile and hand. Than check the
	 * size is the same than the draw pile.
	 * 
	 * @return a set of ICard
	 */
	/*
	 * private boolean checkUnicity() { Set<ICard> drawCheck = makeDrawCheck();
	 * this.lays.forEach(lay -> { drawCheck.removeAll(lay); });
	 * this.hands.forEach(hand -> { drawCheck.removeAll(hand); }); return
	 * drawCheck.size() == this.draw.getSize(); }
	 */

	/**
	 * Produce a set of ICard with the same range than the pile. In order to check
	 * the conformity of the game then.
	 * 
	 * @return a set of ICard
	 */
	/*
	 * private Set<ICard> makeDrawCheck() { Set<ICard> draw = new HashSet<ICard>();
	 * for (int i = RulesService.getDrawPileRange()[0]; i <=
	 * RulesService.getDrawPileRange()[1]; i++) { ICard card = new Number(i);
	 * draw.add(card); } return draw; }
	 */

}
