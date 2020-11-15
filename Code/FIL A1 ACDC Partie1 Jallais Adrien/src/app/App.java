package app;

import java.io.IOException;

import factory.GameFactory;
import game.IGame;

public class App {

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		GameFactory gameF = new GameFactory();
		String path1 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt"; // ok
		IGame game = gameF.getGame(path1);

		class TestGet {
			private int attributInt;

			public TestGet(int i) {
				this.attributInt = i;
			}

			public int getI() {
				return this.attributInt;
			}
		}

		TestGet test1 = new TestGet(3);
		int i = test1.getI();
		System.out.println(i);
		i = 5;
		System.out.println(i);
		System.out.println(test1.getI());
	}

}
