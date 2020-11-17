package game;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import factory.GameFactory;

class GameTest {

	@Test
	void test() throws IOException {
		GameFactory gameF = new GameFactory();
		IGame game;
		game = gameF.getGame(null);
		int target = game.getScore();
		target = 0;
		assertTrue(game.getScore() != 0);
		assertTrue(target == 0);
		assertTrue(target != game.getScore());
	}

}
