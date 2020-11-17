package services;

import java.util.List;

import card.ICard;
import pile.IHand;
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
	public static int chooseOneCard(ILayPile lay, IHand hand) {
		int m = 0;
		ICard cardM = hand.read().get(m);
		int eval = evalCardLay(lay, cardM);
		ICard cardI;
		for (int i = 1; i < hand.read().size(); i++) {
			cardI = hand.read().get(i);
			if (evalCardLay(lay, cardI) < eval) {
				m = i;
				cardM = hand.read().get(m);
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
	public static int[] chooseOneLayOneCard(List<ILayPile> lays, IHand hand) {
		// each lay chooses one card
		int[] tabChoice = new int[lays.size()];
		for (int i = 0; i < lays.size(); i++) {
			tabChoice[i] = chooseOneCard(lays.get(i), hand);
		}
		// then we calculate move for each
		int[] tabMove = new int[lays.size()];
		for (int i = 0; i < tabChoice.length; i++) {
			tabMove[i] = evalCardLay(lays.get(i), hand.read().get(tabChoice[i]));
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

}
