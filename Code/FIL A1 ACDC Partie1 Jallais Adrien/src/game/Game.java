package game;

import java.util.ArrayList;
import java.util.List;

import card.ICard;
import direction.Direction;
import pile.Hand;
import pile.IDrawPile;
import pile.IHand;
import pile.ILayPile;
import services.RulesService;
import services.ServiceUser;

/**
 * 
 * 
 * @author Adrien Jallais
 *
 */
public class Game implements IGame {

	private IDrawPile draw;
	private List<IHand> hands;
	private List<ILayPile> lays;
	private IHand hand;
	private IHand handCheck;
	private int playerI;
	private int score;
	private boolean stop;
	int choiceQuitTurn = 1;
	int choiceQuitGame = 1;

	public Game(IDrawPile _draw, List<IHand> _hands, List<ILayPile> _lays) {
		this.draw = _draw;
		this.hands = _hands;
		this.lays = _lays;
		this.firstDraw();
		this.playerI = 0;
		this.hand = this.hands.get(this.playerI);
		this.score = this.getMinScore();
		this.stop = false;
	}

	/**
	 * fill all the hands with the conventional hand lenght from the draw
	 * 
	 * @return
	 */
	private void firstDraw() {
		for (int i = 0; i < this.hands.size(); i++) {
			this.hand = this.hands.get(i);
			int nc = this.draw(); // draw for this.draw
			assert (nc == RulesService.getHandLength());
		}
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
	public int beginTurn() {
		this.playerI = 0;
		this.hand = this.hands.get(this.playerI);
		if (isHandBlocked() && RulesService.getPlayerNumber() == 1) {
			this.close();
		}
		this.writeHandCheck();
		return this.playerI;
	}

	/**
	 * over write handCheck with a shallow copy of the current hand. The copy is
	 * only shallow and not made with serialization
	 * https://stackoverflow.com/questions/1387954/how-to-serialize-a-list-in-java
	 */
	@Deprecated
	private void writeHandCheck() {
		/*
		 * for (ICard card : this.hand.read()) { this.handCheck.add((ICard) new
		 * Number(card)); }
		 */
		this.handCheck = new Hand(this.hand);
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
	public void printLays() {
		String colNames = "| LayPile_index | LayPile_direction | LayPile_Card |";
		System.out.println(colNames);
		List<LayInfo> layInfo = readLaysInfo();
		for (int i = 0; i < layInfo.size(); i++) {
			System.out.println(layInfo.get(i).toString());
		}
	}

	@Override
	public List<ICard> readHand() {
		return this.hand.read();
	}

	@Override
	public boolean canHandLay() {
		for (final ICard card : this.hand.read()) {
			List<Integer> pileLayable = this.whereToLay(card);
			if (pileLayable.size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean[] mayBeLay(ICard card) {
		boolean[] layable = new boolean[RulesService.getNumberDescendingPile() + RulesService.getNumberAscendingPile()];
		int i = 0;
		for (final ILayPile lay : this.lays) {
			layable[i] = lay.isLayable(card);
			++i;
		}
		return layable;
	}

	@Override
	public List<Integer> whereToLay(ICard card) {
		List<Integer> layable = new ArrayList<Integer>();
		for (int i = 0; i < lays.size(); i++) {
			ILayPile lay = lays.get(i);
			if (lay.isLayable(card)) {
				layable.add(Integer.valueOf(i));
			}
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
		return this.handCheck.contains(card);
	}

	@Override
	public int endTurn() {
		int drawedCards = this.draw();
		if (isHandBlocked() && RulesService.getPlayerNumber() == 1) {
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
	public boolean isHandBlocked() {
		if (this.draw.isEmpty()) {
			if (this.hand.isEmpty()) {
				// hand has won
				return false;
			} else {
				// hand has to play
				return this.canHandLay();
			}
		} else {
			if (!this.hand.isFull()) {
				// hand has to draw
				return false;
			} else {
				// hand have to play
				return this.canHandLay();
			}
		}
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
		this.score = this.getMinScore();
		this.stop = false;
	};

	@Override
	public void close() {
		this.stop = true;
	}

	/**
	 * is the draw pile and the hand empty && there is no missed card && the score
	 * is the highest possible
	 * 
	 * @return
	 */
	public boolean isVictory() {
		return (this.getScore() <= 0 && this.isGameComplete() && this.allHandsEmpty() && this.draw.isEmpty());
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

	/**
	 * Useful for several players all the hands are empty OR is there at least one
	 * hand not empty
	 * 
	 * @return
	 */
	private boolean allHandsEmpty() {
		for (final IHand hand : this.hands) {
			if (!hand.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void quit() {
		System.out.println("Bye");
		this.cleanGame();
		this.score = this.getMinScore();
		this.stop = true;
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
		this.stop = false;
	}

	@Override
	public boolean play() {
		int choiceCard = 0;
		int choiceLayPile = 0;
		int choiceTurn = 0;
		ICard cardTemp;
		while (!this.stop) {
			// whole game
			beginTurn();
			while (!this.stop) {
				cardTemp = null;
				// whole turn
				// state of the game
				System.out.println("Ci dessous l'état du plateau :");
				printLays();
				System.out.println("Ci dessous l'état de votre main:");
				this.hand.print();
				// choice of the card
				System.out.println("Choisissez l'indice de la carte à jouer.");
				printChoiceQuit(this.hand.getSize());
				choiceCard = ServiceUser.setChoice(0, this.hand.getSize() + 1);
				if (choiceCard == choiceQuitTurn || choiceCard == choiceQuitGame) {
					if (choiceCard == choiceQuitGame) {
						this.quit();
					}
					break;
				}
				// choice of the laying pile
				System.out.println("Choisissez l'indice de la pile sur laquelle déposer la carte choisie.");
				printChoiceQuit(RulesService.getNumberLayingPile());
				choiceLayPile = ServiceUser.setChoice(0, RulesService.getNumberLayingPile() + 1);
				if (choiceLayPile == choiceQuitTurn || choiceLayPile == choiceQuitGame) {
					if (choiceLayPile == choiceQuitGame) {
						this.quit();
					}
					break;
				}
				// laying card
				cardTemp = this.hand.read().get(choiceCard);
				if (this.lay(choiceLayPile, cardTemp)) {
					System.out.println("Si vous souhaitez continuer à poser des cartes, tapez 0 ; sinon tapez 1.");
					choiceTurn = ServiceUser.setChoice(0, 1);
					if (choiceTurn == 1) {
						break;
					}
				} else {
					System.out.println("La carte " + cardTemp.toString() + " ne semble pas compatible avec la pile :");
					this.lays.get(choiceLayPile).toString();
					System.out.println("Nous allons vous rappeler l'état du jeu...");
				}
			}
			endTurn();
		}
		if (isVictory()) {
			System.out.println("You win !");
		} else {
			System.out.println("The game won.");
		}
		System.out.println("Your score is " + this.getScore() / this.getMinScore() + " .");
		return isVictory();
	}

	private void printChoiceQuit(int startChoice) {
		this.choiceQuitTurn = startChoice;
		this.choiceQuitGame = startChoice + 1;
		System.out.println("Ou tapez '" + this.choiceQuitTurn + "' pour terminer votre tour.");
		System.out.println("Ou tapez '" + this.choiceQuitGame + "' pour quitter.");
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
