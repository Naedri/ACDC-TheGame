package pile;

import card.ICard;

public interface IDrawPile {
	
	public boolean isClean();
	public boolean isComplete();
	public boolean isRedundant();
	public void shuffle();
	public ICard draw();
	
}
