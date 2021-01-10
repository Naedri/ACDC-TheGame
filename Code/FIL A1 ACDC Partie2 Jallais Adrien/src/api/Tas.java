package api;

import java.util.ArrayList;
import java.util.List;

public abstract class Tas implements ITas {

	protected List<Carte> cartes;

	public Tas() {
		this.cartes = new ArrayList<Carte>();
	}

	/**
	 * Jouer une carte sur le tas, verification anti-triche et verification des
	 * regles
	 * 
	 * @param carte  Instance d'une carte appartenant au Jeu
	 * @param joueur Instance d'un joueur
	 * @return true si l'action a ete effectuee, false si non
	 * @throws ActionIllegaleException Tentative de triche
	 */
	@Override
	public boolean poser(Carte carte, Joueur joueur) throws ActionIllegaleException {
		if (!this.isCoupValide(carte)) {
			return false;
		}

		this.cartes.add(carte);
		joueur.retirerCarte(carte);
		return true;
	}

	/**
	 * Calcul de la validite d'un coup
	 * 
	 * @param Carte instance de la carte a jouer
	 * @return true si valide, false si non
	 */
	abstract public boolean isCoupValide(Carte carte);

	public Carte getDerniereCarte() {
		return this.cartes.get(this.cartes.size() - 1);
	}

	public int getTaille() {
		return this.cartes.size();
	}

	public List<Carte> getCartes() {
		return this.cartes;
	}

	public abstract String toString(int i);

	public abstract String toString();
}
