package factory;

import game.Game;
import game.IGame;

public class GameFactory {

	public IGame getGame(String path, int nbrPlayer) {
		if (path == null) {
			return (makeGame(nbrPlayer));
		} else {
			return makeGame(nbrPlayer, path);
		}
	}

	private Game makeGame(int nbrPlayer) {
		return null;

	};

	private Game makeGame(int nbrPlayer, String path) {
		return null;
	};
}
