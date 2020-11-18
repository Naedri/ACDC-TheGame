package factory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class TestFactory {

	@Test
	void test_DrawFactory() {
		DrawPileFactory drawF = new DrawPileFactory();

		/**
		 * everything all right without path
		 */
		assertDoesNotThrow(new Executable() {

			@Override
			public void execute() throws Throwable {
				drawF.getDrawPile(null);
			}
		});

		/**
		 * path1 =
		 * "C:\\Users\\Adrien_Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt";
		 * // absolute path
		 */
		String path1 = "../../Jeu_essai/game1.txt"; // relative path
		String path2 = "../../Jeu_essai/game2.txt"; // relative path
		String path3 = "../../Jeu_essai/game3.txt"; // relative path
		String path4 = "../../Jeu_essai/game4.txt"; // relative path

		/**
		 * everything all right
		 */
		assertDoesNotThrow(new Executable() {

			@Override
			public void execute() throws Throwable {
				drawF.getDrawPile(path1);
			}

		});

		/**
		 * with a duplicate number
		 */
		assertThrows(IllegalArgumentException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				drawF.getDrawPile(path2);
			}

		});

		/**
		 * with a blank number
		 */
		assertThrows(IllegalArgumentException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				drawF.getDrawPile(path3);
			}

		});

		/**
		 * with a string instead a number Error : For input string: "bachibouzouc "
		 */
		assertThrows(NumberFormatException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				drawF.getDrawPile(path4);
			}

		});

	}
}
