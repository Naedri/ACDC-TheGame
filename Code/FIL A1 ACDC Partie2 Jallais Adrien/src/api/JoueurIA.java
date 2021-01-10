package api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JoueurIA extends Joueur {

	public static final int ECART_ACCEPTABLE = 10;

	public JoueurIA() {
		super("Ordinateur");
	}

	/**
	 * Liste des coups valides pour chaque tas
	 * 
	 * @param tas Instance du tas sur lequel calculer les coups valides
	 * @return instance de la meilleure Carte
	 */
	public List<Carte> coupsValides(Tas tas) {
		List<Carte> main = super.getMain();
		Collections.sort(main);

		List<Carte> coupsValides = new ArrayList<Carte>();
		if (tas instanceof TasDescendant) {
			for (int i = main.size() - 1; i >= 0; i--) {
				Carte carte = main.get(i);

				if (tas.isCoupValide(carte)) {
					coupsValides.add(carte);
				}
			}
		} else {
			for (int i = 0; i < main.size(); i++) {
				Carte carte = main.get(i);
				if (tas.isCoupValide(carte)) {
					coupsValides.add(carte);
				}
			}
		}

		return coupsValides;
	}

	/**
	 * Calcul de l'ecart d'une carte avec la derniere posee sur un tas
	 * 
	 * @param carte instance de la carte
	 * @param tas   instance du tas
	 * @return un entier correspondant a l'ecart
	 */
	public int ecartCarte(Carte carte, Tas tas) {
		int valeurTas = tas.getDerniereCarte().getValeur();
		int valeurCarte = carte.getValeur();

		if (tas instanceof TasDescendant) {
			return valeurTas - valeurCarte;
		}

		return valeurCarte - valeurTas;
	}

	/**
	 * Intelligence embarquee de jeu
	 * 
	 * @param jeu Instance du jeu sur laquelle jouer
	 * @return true si l'IA ne peut pas jouer, false si le tour a ete entierement
	 *         joue
	 * @throws CoupInvalideException
	 * @throws ActionIllegaleException
	 */
	public boolean jouerTour(Jeu jeu) throws CoupInvalideException, ActionIllegaleException {
		List<CarteIA> coupsPossibles = new ArrayList<CarteIA>();

		for (int i = 0; i < jeu.getTas().size(); i++) {

			Tas tas = jeu.getTasById(i);
			List<Carte> coupsValides = this.coupsValides(tas);

			for (int j = 0; j < coupsValides.size(); j++) {
				Carte carte = coupsValides.get(j);
				int ecart = this.ecartCarte(carte, tas);
				CarteIA c = new CarteIA(carte, i, ecart);

				coupsPossibles.add(c);
			}

		}

		if (coupsPossibles.size() == 0) {
			return true;
		}

		Collections.sort(coupsPossibles);
		Collections.reverse(coupsPossibles);

		System.out.println(coupsPossibles);

		int jouees = 0;
		for (int i = 0; i < coupsPossibles.size(); i++) {
			CarteIA c = coupsPossibles.get(i);

			if (!super.getMain().contains(c.getCarte())) {
				continue;
			}

			if (jouees < jeu.nbCartesAJouer() || c.getEcart() < JoueurIA.ECART_ACCEPTABLE) {
				System.out.println("L'IA va jouer " + c.getCarte() + " sur le tas " + c.getTas());
				jeu.jouer(c.getTas(), c.getCarte(), this);
				jouees++;

				coupsPossibles.remove(c);
				for (int j = 0; j < coupsPossibles.size(); j++) {
					if (coupsPossibles.get(j).getCarte() == c.getCarte()) {
						coupsPossibles.remove(j);
					}
				}
			}

		}

		jeu.passerTour();
		return false;
	}

}
