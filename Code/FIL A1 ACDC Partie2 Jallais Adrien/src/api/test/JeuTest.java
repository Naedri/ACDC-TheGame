package api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import api.ActionIllegaleException;
import api.Carte;
import api.CoupInvalideException;
import api.Jeu;
import api.Joueur;
import api.Pioche;
import api.Tas;
import api.TasAscendant;
import api.TasDescendant;

@TestInstance(Lifecycle.PER_CLASS)
class JeuTest {

	private Jeu jeu;
	private List<Joueur> joueurs;
	private List<Tas> tas;
	private Pioche pioche;

	@BeforeAll
	void initJeu() {
		// Initialisation de la pioche avec 100 cartes de 0 à 99
		List<Carte> cartes = new ArrayList<Carte>();

		for (int i = 1; i < 99; i++) {
			Carte carte = new Carte(i);

			cartes.add(carte);
		}

		this.pioche = new Pioche(cartes);

		// Initialisation des joueurs : Test
		Joueur joueur = new Joueur("Test");
		Joueur joueur2 = new Joueur("Joueur 2");

		this.joueurs = new ArrayList<Joueur>();
		this.joueurs.add(joueur);
		this.joueurs.add(joueur2);

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

	@AfterEach
	void reset() {
		this.jeu.setTour(0);
	}

	@Test
	@DisplayName("pas d'erreur pendant la création de la partie")
	void testInitJeu() {
		// Vérifications de base
		assertEquals(this.jeu.getTas(), this.tas);
		assertEquals(this.jeu.getPioche(), this.pioche);
	}

	@Test
	@DisplayName("cartes piochées dans la main du joueur")
	void testPiocher() {
		Joueur joueur = this.joueurs.get(0);
		List<Carte> buffer = joueur.getMain();
		List<Carte> pioche = joueur.piocher(this.jeu);

		List<Carte> main = joueur.getMain();
		main.removeAll(buffer);

		assertEquals(pioche, main);
	}

	@Test
	@DisplayName("carte retirée de la main une fois jouée")
	void testPioche() {
		Joueur joueur = this.joueurs.get(0);
		Carte carteAJouer = joueur.getMain().get(0);

		try {
			List<Carte> main = joueur.getMain();
			this.jeu.jouer(3, carteAJouer, joueur);
			List<Carte> mainApres = joueur.getMain();

			main.remove(carteAJouer);

			assertEquals(mainApres, main);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@DisplayName("pas de création de carte")
	void testCreationCarte() {
		Carte carte = new Carte(1);

		assertThrows(ActionIllegaleException.class, () -> this.jeu.jouer(2, carte, this.joueurs.get(0)));
	}

	@Test
	@DisplayName("coup valide")
	void testCoupValide() {
		Joueur joueur = this.joueurs.get(0);
		Carte carteAJouer = joueur.getMain().get(0);

		try {
			this.jeu.jouer(2, carteAJouer, joueur);
			assertEquals(this.jeu.getTas().get(2).getDerniereCarte(), carteAJouer);
		} catch (ActionIllegaleException e) {
			assertFalse(true);
		} catch (CoupInvalideException e) {
			assertFalse(true);
		}
	}

	@Test
	@DisplayName("coup invalide")
	void testCoupInvalide() {
		Joueur joueur = this.joueurs.get(0);
		Carte carteAJouer1 = joueur.getMain().get(1);
		Carte carteAJouer2 = joueur.getMain().get(0);

		try {
			this.jeu.jouer(2, carteAJouer1, joueur);
		} catch (ActionIllegaleException e) {
			assertFalse(true);
		} catch (CoupInvalideException e) {
			assertFalse(true);
		}

		try {
			this.jeu.jouer(2, carteAJouer2, joueur);
			assertFalse(true);
		} catch (ActionIllegaleException e) {
			assertFalse(true);
		} catch (CoupInvalideException e) {
			assertTrue(true);
		}
	}

	@Test
	@DisplayName("changement de tour")
	void testTours() {
		Joueur joueur = this.joueurs.get(0);
		joueur.piocher(this.jeu);
		Carte carte1 = joueur.getMain().get(0);
		Carte carte2 = joueur.getMain().get(1);

		try {
			this.jeu.jouer(2, carte1, joueur);
			this.jeu.jouer(2, carte2, joueur);
			System.out.println(this.jeu.passerTour());
			assertEquals(1, jeu.getTour());
		} catch (ActionIllegaleException e) {
			assertTrue(false);
		} catch (CoupInvalideException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
	}
}
