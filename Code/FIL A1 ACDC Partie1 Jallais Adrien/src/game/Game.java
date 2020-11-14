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
	private IHand hand;
	private boolean victory;

	public Game(IDrawPile _draw, List<IHand> _hands, List<ILayPile> _lays) {
		this.draw = _draw;
		this.hands = _hands;
		this.lays = _lays;
		this.cardByHand = RulesService.getHandLength();
		this.victory = false;
		this.hand = this.hands.get(0);
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
	public String[][] readLayInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICard> readHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScore() {
		int score = 0;
		// TODO Auto-generated method stub
		return score;
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
	 * @return the number of point
	 */
	private int closeGame() {
		int score = this.getScore();
		this.victory = this.isVictory();
		this.draw = null;
		this.hands = null;
		this.lays = null;
		this.cardByHand = RulesService.getHandLength();
		this.hand = null;
		return score;
	}

	/**
	 * is the draw pile and the hand empty
	 * 
	 * @return
	 */
	private boolean isVictory() {
		return (this.getScore() <= 0 && this.isGameComplete());
	}

	/**
	 * make sure the totality of card is present
	 */
	private boolean isGameComplete() {
		// check size draw
	}

	@Override
	@Deprecated
	public boolean lay(int pileIndice, ICard card) {
		// check if card is from the hand
		ILayPile lay = this.lays.get(pileIndice);
		return lay.lay(card);
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}
}
