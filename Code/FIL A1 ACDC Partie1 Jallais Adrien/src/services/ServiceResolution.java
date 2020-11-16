package services;

import java.util.List;

import card.ICard;
import game.Game.LayInfo;

public class ServiceResolution implements IResolution {

	@Override
	public boolean isCombination() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int evalLay(List<LayInfo> lays, ICard card) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] evalAllLay(List<LayInfo> lays, List<ICard> card) {
		// TODO Auto-generated method stub
		return null;
	}

}
