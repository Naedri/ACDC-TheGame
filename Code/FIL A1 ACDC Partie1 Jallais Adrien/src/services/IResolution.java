package services;

import java.util.List;

import card.ICard;
import game.Game.LayInfo;

public interface IResolution {

	/**
	 * Can we do a draw back and laying card between
	 * 
	 * @return
	 */
	public boolean isCombination();

	/**
	 * allow to know where is better to lay ONE card between the different lays
	 * 
	 * @param lays from game.readLays()
	 * @param card from game.readHand()
	 * @return
	 */
	public int evalLay(List<LayInfo> lays, ICard card);

	/**
	 * allow to know where is better to lay SEVERAL card between the different lays
	 * 
	 * @param lays from game.readLays()
	 * @param card from game.readHand()
	 * @return
	 */
	public int[] evalAllLay(List<LayInfo> lays, List<ICard> card);

}
