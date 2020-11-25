package game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import card.ICard;
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

		game.beginTurn();
		game.print();
		List<ICard> hand = game.readHand();

		assertTrue(game.lay(3, hand.get(4)));
		assertFalse(game.lay(3, hand.get(3)));

		assertThrows(IndexOutOfBoundsException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				hand.get(5);
			}

		});

		assertFalse(game.lay(4, hand.get(2)));
		/*
		 * assertThrows(IndexOutOfBoundsException.class, new Executable() {
		 * 
		 * @Override public void execute() throws Throwable { game.lay(4, hand.get(2));
		 * }
		 * 
		 * });
		 */

	}

}
