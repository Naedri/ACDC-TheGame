package api;

import java.util.ArrayList;
import java.util.List;

public class MainAI {

	public static void main(String[] args) throws CoupInvalideException, ActionIllegaleException {
		JoueurIA ai = new JoueurIA();
		List<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(ai);

		Jeu jeu = Jeu.lancerPartie(joueurs, "game1.txt");

		ai.piocher(jeu);

		boolean fini = false;
		do {
			fini = ai.jouerTour(jeu);
		} while (!fini && !jeu.isPartieFinie());

		System.out.println("Score de la partie : " + jeu.score());
		System.out.println(jeu);
		System.out.println("Main : " + ai.getMain());

		int score = jeu.score();
		System.exit(score);
	}

}
