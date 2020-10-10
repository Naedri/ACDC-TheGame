package pile;

import card.ICard;
import direction.Direction;

public interface ILayPile {
	public ICard getThresholdMin();

	public ICard getThresholdMax();

	public ICard getBackwards();

	public ICard getCard();

	public Direction getDirection();
}
