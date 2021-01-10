package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws CoupInvalideException, ActionIllegaleException {

		Joueur joueur = new Joueur("Humain");
		List<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(joueur);

		Scanner scanner = new Scanner(System.in);

		Jeu partie = Jeu.lancerPartie(joueurs, "game1.txt");

		joueur.piocher(partie);

		do {
			System.out.println(partie);
			System.out.println("Main joueur : " + joueur.getMain());

			boolean finTour = false;
			do {
				System.out.println("Jouez votre coup en precisant le tas et la carte comme suit : 0,23");
				String input = scanner.nextLine();
				String[] argsInput = input.split(",");

				int tas = 0;
				int carte = 0;

				try {
					tas = Integer.parseInt(argsInput[0]);
					carte = Integer.parseInt(argsInput[1]);
				} catch (Exception e) {
					continue;
				}

				Carte carteJouee = null;

				for (Carte c : joueur.getMain()) {
					if (c.getValeur() == carte) {
						carteJouee = c;
						break;
					}
				}

				if (carteJouee == null) {
					System.out.println("Merci de saisir une carte existante");
					continue;
				}

				try {
					partie.jouer(tas, carteJouee, joueur);
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

				if (partie.nbCartesAJouer() <= 0) {
					System.out.println("Souhaitez-vous terminer le tour ? [Y/n]");
					String inputFin = scanner.nextLine();

					if (inputFin.matches("n|N")) {
						finTour = false;
					} else {
						finTour = true;
					}
				}
			} while (partie.nbCartesAJouer() > 0 || !finTour);

			partie.passerTour();

		} while (!partie.isPartieFinie());

		System.out.println("La partie est terminee. Vous avez fait un score de " + partie.score());
	}

}
