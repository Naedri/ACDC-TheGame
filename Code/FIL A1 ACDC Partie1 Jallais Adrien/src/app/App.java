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
		String path;
		path = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt"; // ok

		/**
		 * wanna play a short game ? please change ass well services.ServicesRules line
		 * 11 with private final static int[] cardRange = new int[] { 10, 20 };
		 */
		path = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\gameSmall.txt"; // ok

		IGame game = gameF.getGame(path);
		game.playHuman();

	}

}
