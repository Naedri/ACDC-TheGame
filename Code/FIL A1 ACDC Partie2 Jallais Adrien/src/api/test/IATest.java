package api.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import api.ActionIllegaleException;
import api.Carte;
import api.CoupInvalideException;
import api.Jeu;
import api.Joueur;
import api.JoueurIA;
import api.Pioche;
import api.Tas;
import api.TasAscendant;
import api.TasDescendant;

@TestInstance(Lifecycle.PER_CLASS)
class IATest {

	private Jeu jeu;
	private List<Joueur> joueurs;
	private JoueurIA joueur;
	private List<Tas> tas;
	private Pioche pioche;

	@BeforeAll
	void initJeu() {
		// Initialisation de la pioche avec 100 cartes de 0 à 99
		List<Carte> cartes = new ArrayList<Carte>();

		for (int i = 1; i < 100; i++) {
			Carte carte = new Carte(i);

			cartes.add(carte);
		}

		Collections.shuffle(cartes);

		this.pioche = new Pioche(cartes);

		// Initialisation des joueurs : Test
		this.joueur = new JoueurIA();

		this.joueurs = new ArrayList<Joueur>();
		this.joueurs.add(this.joueur);

		// Initialisation des tas
		TasDescendant tas1 = new TasDescendant();
		TasDescendant tas2 = new TasDescendant();
		TasAscendant tas3 = new TasAscendant();
		TasAscendant tas4 = new TasAscendant();

		this.tas = new ArrayList<Tas>();
		this.tas.add(tas1);
		this.tas.add(tas2);
		this.tas.add(tas3);
		this.tas.add(tas4);

		// Initialisation de l'instance de jeu
		this.jeu = new Jeu(this.pioche, this.joueurs, this.tas);
	}

	@Test
	void testMeilleurCoup() throws CoupInvalideException, ActionIllegaleException {
	}

}
