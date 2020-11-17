package factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import card.ICard;
import game.Game;
import game.IGame;
import pile.AscendingPile;
import pile.DescendingPile;
import pile.Hand;
import pile.IDrawPile;
import pile.IHand;
import pile.ILayPile;
import services.ServiceRules;
import services.ServiceUser;

public class GameFactory {

	private DrawPileFactory drawF;
	private IDrawPile draw;

	private ILayPile layTemp;
	private List<ILayPile> laysList;

	private IHand handTemp;
	private List<IHand> handsList;

	private IGame game;

	// rules number
	private int players;
	private int ascPile;
	private int dscPile;

	public GameFactory() {
		this.drawF = new DrawPileFactory();
		this.laysList = new ArrayList<ILayPile>();
		this.handsList = new ArrayList<IHand>();
		this.players = ServiceRules.getPlayerNumber();
		this.ascPile = ServiceRules.getNumberAscendingPile();
		this.dscPile = ServiceRules.getNumberDescendingPile();
	}

	public IGame getGame(String path) throws IOException {
		if (path != null && !ServiceUser.isPathValid(path)) {
			throw new IllegalArgumentException("The given file path seems to be incorrect.");
		}
		if (this.game != null) {
			this.resetGameFactory();
		}
		makeGame(path);
		return this.game;
	}

	/**
	 * reset the attributes of the factory
	 */
	private void resetGameFactory() {
		this.drawF = new DrawPileFactory();
		this.draw = null;
		this.layTemp = null;
		this.handTemp = null;
		this.handsList = new ArrayList<IHand>();
		this.laysList = new ArrayList<ILayPile>();
		this.game = null;
	}

	private void makeGame(String path) throws IOException {
		this.draw = this.drawF.getDrawPile(path);
		this.fillHands();
		this.fillLays();
		this.game = new Game(this.draw, this.handsList, this.laysList);
	};

	/**
	 * fill the laysList : descending and ascending ones
	 */
	private void fillLays() {
		this.fillDscPile();
		this.fillAscPile();
	}

	/**
	 * fill the Descending Pile
	 */
	private void fillDscPile() {
		for (int i = 0; i < this.dscPile; i++) {
			this.layTemp = new DescendingPile();
			this.laysList.add(this.layTemp);
		}
	}

	/**
	 * fill the Ascending Pile
	 */
	private void fillAscPile() {
		for (int i = this.dscPile; i < (this.dscPile + this.ascPile); i++) {
			this.layTemp = new AscendingPile();
			this.laysList.add(this.layTemp);
		}
	}

	/**
	 * fill the handsList hands one by one hand using the fillHandTemp
	 */
	private void fillHands() {
		for (int i = 0; i < this.players; i++) {
			this.handTemp = new Hand(new ArrayList<ICard>());
			this.handsList.add(this.handTemp);
		}
	}
}
