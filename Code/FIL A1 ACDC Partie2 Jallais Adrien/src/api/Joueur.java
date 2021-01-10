package api;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

	private List<Carte> main;
	private String nom;

	/**
	 * Construire un joueur avec son nom
	 * 
	 * @param nom Nom du joueur
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.main = new ArrayList<Carte>();
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * Retour des cartes de la main du joueur, duplication de la liste pour eviter
	 * de renvoyer la reference
	 * 
	 * @return List<Carte>
	 */
	public List<Carte> getMain() {
		List<Carte> main = new ArrayList<Carte>();
		main.addAll(this.main);
		return main;
	}

	/**
	 * Piocher un nombre de cartes dans le jeu Methode deportee ici afin de garder
	 * les changements de main en prive
	 * 
	 * @param jeu    Instance du jeu
	 * @param nombre Nombre de cartes a tirer
	 * @return List<Carte> liste de cartes piochees
	 */
	public List<Carte> piocher(Jeu jeu) {
		int nbCartesMax;

		switch (jeu.getJoueurs().size()) {
		case 1:
			nbCartesMax = 8;
			break;
		case 2:
			nbCartesMax = 7;
			break;
		default:
			nbCartesMax = 6;
			break;
		}

		int nombre = nbCartesMax - this.main.size();

		List<Carte> cartes = jeu.getPioche().piocher(nombre);
		this.main.addAll(cartes);

		return cartes;
	}

	/**
	 * On tolere le fait de retirer une carte de la main en public
	 * 
	 * @param carte instance de carte
	 */
	public void retirerCarte(Carte carte) {
		this.main.remove(carte);
	}
}
