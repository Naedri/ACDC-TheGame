package factory;

import game.Game;
import game.IGame;

public class GameFactory {

	public IGame getGame(String path) {
		if (path == null) {
			return (makeGame());
		} else {
			return makeGame(path);
		}
	}

	private Game makeGame() {
		return null;

	};

	private Game makeGame(String path) {
		return null;
	};
}
