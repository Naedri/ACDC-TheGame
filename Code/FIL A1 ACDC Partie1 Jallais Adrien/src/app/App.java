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

		// game.playHuman();
	}

}
