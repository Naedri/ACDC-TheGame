package api;

public class Carte implements Comparable<Carte> {

	private int valeur;

	/**
	 * Constructeur de Carte
	 * 
	 * @param valeur Valeur de la carte entre 2 et 98
	 */
	public Carte(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * Getter Valeur de la carte
	 * 
	 * @return valeur
	 */
	public int getValeur() {
		return this.valeur;
	}

	/**
	 * Implementation de Comparable<Carte>
	 * 
	 * @param Carte Instance de la carte a comparer
	 * @return 1 si cette instance est superieure a la carte donnee, 0 si egale, -1
	 *         si inferieure
	 */
	public int compareTo(Carte carte) {
		if (carte.getValeur() == this.getValeur()) {
			return 0;
		}

		return carte.getValeur() < this.getValeur() ? 1 : -1;
	}

	/**
	 * Affichage d'un resume de la carte
	 */
	public String toString() {
		return Integer.toString(this.getValeur());
	}

}
