package pile;

import card.ICard;

public interface IDrawPile {

	public boolean isClean();

	public boolean isComplete();

	public boolean isRedundant();

	public void shuffle();

	public ICard draw();

	public boolean contains(ICard card);

	public boolean miss(ICard card);
}
