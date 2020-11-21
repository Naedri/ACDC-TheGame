package services;

import java.util.ArrayList;
import java.util.List;

import card.ICard;
import direction.Direction;
import game.IGame;
import game.Move;
import pile.ILayPile;

/**
 *
 * Il faut minimiser au maximum les coups A chaque coup on associe un poids et
 * on prendre le coup au poids le plus faible
 * 
 * @author Adrien Jallais
 *
 */
public class ServiceResolution {

	/**
	 * 
	 * @param hand
	 * @return
	 */
	public static boolean isCombination(List<ICard> hand) {
		ICard c;
		ICard ctemp;
		for (int i = 0; i < (hand.size() - 1); i++) {
			c = hand.get(i);
			for (int j = (i + 1); j < hand.size(); j++) {
				ctemp = hand.get(j);
				int diff = ctemp.getValue() - c.getValue();
				if (diff == Direction.SUPER_UP.getDRow() || diff == Direction.SUPER_DOWN.getDRow()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param hand
	 * @return the first
	 */
	public static int[] getCombination(List<ICard> hand) {
		int[] combi = new int[2];

		if (hand.size() >= 2) {
			ICard c;
			ICard ctemp;

			for (int i = 0; i < (hand.size() - 1); i++) {
				c = hand.get(i);
				for (int j = (i + 1); j < hand.size(); j++) {
					ctemp = hand.get(j);
					int diff = ctemp.getValue() - c.getValue();
					if (diff == Direction.SUPER_UP.getDRow() || diff == Direction.SUPER_DOWN.getDRow()) {
						combi[0] = i;
						combi[1] = j;
						return combi;
					}
				}
			}

		}
		return combi;
	}

	/**
	 * 
	 * @param dir direction of the draw pile
	 * @param c1
	 * @param c2
	 * @return the card associated with the lightest move
	 */
	public static ICard getMinCard(Direction dir, ICard c1, ICard c2) {
		assert (c1 != null && c2 != null && dir.getDRow() != 0);
		int d = dir.getDRow() * (c1.getValue() - c2.getValue());
		if (d < 0) {
			return c1;
		} else {
			return c2;
		}
	}

	/**
	 * allow to know the cost of a move ; the less it is, the better is
	 * 
	 * @param lays from game.readLays()
	 * @param card from game.readHand()
	 * @return
	 */
	public static int evalCardLay(ILayPile lay, ICard card) {
		assert (lay != null && card != null);
		if (!lay.isLayable(card)) {
			return Integer.MAX_VALUE; // card can not be layed
		}
		return (lay.getDirection().getDRow() * (card.getValue() - lay.read().getValue()));
	}

	/**
	 * 
	 * @param lays
	 * @param card
	 * @return a int[] with the weight
	 */
	public static int[] evalCardAllLay(List<ILayPile> lays, ICard card) {
		int[] tab = new int[lays.size()];
		for (int i = 0; i < lays.size(); i++) {
			tab[i] = evalCardLay(lays.get(i), card);
		}
		return tab;
	}

	/**
	 * which is the better lay for a given card ?
	 * 
	 * @param lays
	 * @param card
	 * @return
	 */
	public static int chooseOneLay(List<ILayPile> lays, ICard card) {
		int[] tab = evalCardAllLay(lays, card);
		int m = 0;
		for (int i = 1; i < tab.length; i++) {
			if (tab[i] <= tab[m]) {
				if (tab[i] == tab[m]) {
					// both card move are equivalent
					ILayPile layI = lays.get(i);
					ILayPile layM = lays.get(m);
					if (layI.getRemainCards() > layM.getRemainCards()) {
						// we choose the lay with the more place
						m = i;
					}
				} else {
					// the new card move is better
					m = i;
				}
			}
		}
		return m;
	}

	/**
	 * which is the better card for a lay ?
	 * 
	 * @param lays
	 * @param card
	 * @return
	 */
	public static int chooseOneCard(ILayPile lay, List<ICard> hand) {
		int m = 0;
		ICard cardM = hand.get(m);
		int eval = evalCardLay(lay, cardM);
		ICard cardI;
		for (int i = 1; i < hand.size(); i++) {
			cardI = hand.get(i);
			if (evalCardLay(lay, cardI) < eval) {
				m = i;
				cardM = hand.get(m);
			}
		}
		return m;
	}

	/**
	 * Calculated with chooseOneLay and chooseOneCard. It will act as a tournament.
	 * 
	 * @param lays
	 * @param hand
	 * @return [ lay_index, card_index ]
	 */
	public static int[] chooseOneLayOneCard(List<ILayPile> lays, List<ICard> hand) {
		// each lay chooses one card
		int[] tabChoice = new int[lays.size()];
		for (int i = 0; i < lays.size(); i++) {
			tabChoice[i] = chooseOneCard(lays.get(i), hand);
		}
		// then we calculate move for each
		int[] tabMove = new int[lays.size()];
		for (int i = 0; i < tabChoice.length; i++) {
			tabMove[i] = evalCardLay(lays.get(i), hand.get(tabChoice[i]));
		}
		// then we selected the lowest move
		int m = 0;
		for (int i = 1; i < tabMove.length; i++) {
			if (tabMove[i] <= tabMove[m]) {
				// the new move can be more better
				if (tabMove[i] == tabMove[m]) {
					// both move are equivalent
					ILayPile layI = lays.get(i);
					ILayPile layM = lays.get(m);
					if (layI.getRemainCards() > layM.getRemainCards()) {
						// we choose the lay with the more place
						m = i;
					}
				} else {
					// the new move is better
					m = i;
				}
			}
		}
		int[] result = { m, tabChoice[m] };
		return result;
	}

	/**
	 * this method allows to use again and directly the game
	 * 
	 * @param g
	 * @return a array with 2 column : | LayPile_index | Card_index | ; of which
	 *         size == drawPile.size
	 */
	public static List<Move> resolve(IGame g) {
		int[] resultTurn = new int[2];
		Move resultRow;
		List<Move> result = new ArrayList<Move>();
		int i = 0;
		while (!g.isHandBlocked() && (ServiceRules.getPlayerNumber() == 1)) {
			g.beginTurn();
			resultTurn = ServiceResolution.chooseOneLayOneCard(g.readLays(), g.readHand());
			boolean l = g.lay(resultTurn[0], g.readHand().get(resultTurn[1]));
			if (l) {
				resultRow = new Move(resultTurn[0], resultTurn[1]);
				result.add(resultRow);
				++i;
			}
			g.endTurn();
			// g.print();// to activate if you wanna see the game in progression
		}
		System.out.println("The score is : " + g.getScore() + " / " + g.getMinScore() + " .");
		System.out.println("IA has layed " + Integer.valueOf(i) + " cards.");
		if (g.isVictory()) {
			System.out.println("IA beat the game");
		} else {
			System.out.println("The Game won.");
		}
		g.close();
		g.restart();
		return result;
	}
}
