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
	private IHand hand;
	private boolean victory;
	private int score;

	public Game(IDrawPile _draw, List<IHand> _hands, List<ILayPile> _lays) {
		this.draw = _draw;
		this.hands = _hands;
		this.lays = _lays;
		this.victory = false;
		this.hand = this.hands.get(0);
		this.score = this.getMinScore();
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
	public int endTurn() {
		int drawedCards = this.draw();
		if (drawedCards <= 0) {
			this.closeGame();
		}
		return drawedCards;
	}

	/**
	 * 
	 * Clean the game
	 */
	public void cleanGame() {
		this.draw = null;
		this.hands = null;
		this.lays = null;
		this.victory = false;
		this.hand = null;
		this.score = 0;

	}

	/**
	 * is the draw pile and the hand empty && there is no missed card
	 * 
	 * @return
	 */
	public boolean isVictory() {
		return (this.getScore() <= 0 && this.isGameComplete());
	}

	private int getScore() {
		return this.score;
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

	/**
	 * make sure the totality of card is present, and not redundant
	 * 
	 * @return
	 */
	private boolean isGameComplete() {
		return (this.getSize() == RulesService.getSizeExpected());
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

	@Override
	@Deprecated
	public boolean lay(int pileIndice, ICard card) {
		// check if card is from the hand
		ILayPile lay = this.lays.get(pileIndice);
		boolean successLaying = lay.lay(card);
		if (successLaying) {
			this.score -= card.getValue();
		}
		return successLaying;
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
	};
}
