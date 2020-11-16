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

		/**
		 * USUAL GAME
		 */
		path = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt";
		// for given cards
		path = null;
		// for random cards

		/**
		 * SHORT GAME?
		 */
		// please change services.ServicesRules line
		// add line 11 with private final static int[] cardRange = new int[] { 10, 20 };
		path = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\gameSmall_Win.txt";
		// you win if you do : 020 - 020 - 020 ...
		path = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\gameSmall_Loose.txt";
		// you loose if you do : 430 - 320 - 000 - 010

		IGame game = gameF.getGame(null);
		game.playHuman();

	}

}
