package app;

import java.io.IOException;
import java.util.List;

import factory.GameFactory;
import game.IGame;
import game.Move;
import services.ServiceResolution;

public class App {

	/**
	 * Avant de lancer l'application, veuillez taper (dans les argurments de
	 * l'application) un des paramètres suivants pour régler le lancement de
	 * l’application : (tutoriel au lien ci-dessous)
	 * https://www.javaprogrammingforums.com/java-jdk-ide-tutorials/362-how-send-command-line-arguments-eclipse.html)
	 */

	// < 1 > Pour afficher les résultats de résolution d'un jeu, formé à partir
	// d'une pioche aléatoire.
	// < 2 > Pour afficher les résultats de résolution d'un jeu, formé à partir
	// d'un fichier.
	// < 3 > Pour jouer en mode console à partir d'une pioche aléatoire.
	// < 4 > Pour jouer en mode console à partir d'un fichier.

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// System.out.println("Working Directory = " + System.getProperty("user.dir"));

		String path, path1, path2, path3, path4;

		/**
		 * USUAL GAME ?
		 */
		// for given cards
		path1 = "../../Jeu_essai/game1.txt"; // relative path
		/**
		 * path1 =
		 * "C:\\Users\\Adrien_Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt";
		 * // absolute path
		 */
		// for random cards
		path2 = null;

		/**
		 * SHORT GAME ?
		 */
		// please change services.ServicesRules line
		// add line 11 with private final static int[] cardRange = new int[] { 10, 20 };
		path3 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\gameSmall_Win.txt";
		// you win if you do : 020 - 020 - 020 ...
		path4 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\gameSmall_Loose.txt";
		// you loose if you do : 430 - 320 - 000 - 010

		// MAKE YOUR CHOICE HERE
		// by changing path1 to path1, path2, path3 or path4
		path = path1;

		GameFactory gameF = new GameFactory();
		IGame game;

		for (String s : args) {
			int choixJeu = Integer.parseUnsignedInt(s);

			switch (choixJeu) {
			case 1: {
				// IA
				System.out.println("Let's see !\n");
				game = gameF.getGame(null);
				List<Move> tableResolved = ServiceResolution.resolve(game);
				// showing IA results
				System.out.println("| LayPile_index | Card_index |");
				tableResolved.forEach(row -> {
					row.print();
				});
				break;
			}
			case 2: {
				// IA
				System.out.println("Let's see !\n");
				game = gameF.getGame(path);
				List<Move> tableResolved = ServiceResolution.resolve(game);
				// showing IA results
				System.out.println("| LayPile_index | Card_index |");
				tableResolved.forEach(row -> {
					row.print();
				});
				break;
			}
			case 3: {
				// HUMAN
				System.out.println("Let's go !\n");
				game = gameF.getGame(null);
				game.playHuman();
				break;
			}
			case 4: {
				// HUMAN
				System.out.println("Let's go !\n");
				game = gameF.getGame(path);
				game.playHuman();
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choixJeu);
			}
		}
	}

}
