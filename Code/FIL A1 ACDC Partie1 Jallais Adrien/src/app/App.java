package app;

import java.io.IOException;
import java.util.List;

import factory.GameFactory;
import game.IGame;
import game.Move;
import services.ServiceResolution;

public class App {

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		GameFactory gameF = new GameFactory();
		String path1, path2;

		IGame game;

		/**
		 * USUAL GAME ?
		 */
		// for random cards
		path1 = null;
		// for given cards
		path1 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt";

		/**
		 * SHORT GAME ?
		 */
		// please change services.ServicesRules line
		// add line 11 with private final static int[] cardRange = new int[] { 10, 20 };
		path2 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\gameSmall_Win.txt";
		// you win if you do : 020 - 020 - 020 ...
		path2 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\gameSmall_Loose.txt";
		// you loose if you do : 430 - 320 - 000 - 010

		/**
		 * PLAY
		 */
		// HUMAN
		game = gameF.getGame(null);
		game.playHuman();
		// IA
		game = gameF.getGame(path1);
		List<Move> tableResolved = ServiceResolution.resolve(game);

		// showing IA results
		System.out.println("| LayPile_index | Card_index |");
		tableResolved.forEach(row -> {
			row.print();
		});
	}

}
