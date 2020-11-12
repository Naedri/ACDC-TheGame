/**
 * 
 */
package pile;

import java.util.Deque;
import java.util.LinkedList;

import card.ICard;
import card.Number;
import direction.Direction;
import services.RulesService;

/**
 * @author Adrien Jallais
 *
 */
public abstract class CALayPile implements ILayPile {

	private Deque<ICard> deque;
	private Direction direction;

	public CALayPile(Direction direction) {
		this.direction = direction;
		this.deque = new LinkedList<ICard>();
		// Descending Pile
		if (this.direction == Direction.DOWN) {
			this.deque.add(new Number(RulesService.getLayDescendingPileRange()[0]));
		}
		// Ascending Pile
		if (this.direction == Direction.UP) {
			this.deque.add(new Number(RulesService.getLayAscendingPileRange()[0]));
		}
	}
}
