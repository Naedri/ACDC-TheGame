package api;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

	private Pioche pioche;
	private List<Joueur> joueurs;
	private List<Tas> tas;
	private List<Carte> cartes = new ArrayList<Carte>();

	private int tour = 0;
	private int nbCartesTour = 0;
	private boolean partieFinie = false;

	/**
	 * Constructeur de Jeu
	 * 
	 * @param pioche  Pioche du jeu (avec ordre definitif)
	 * @param joueurs Liste des joueurs
	 * @param tas     Liste des tas
	 */
	public Jeu(Pioche pioche, List<Joueur> joueurs, List<Tas> tas) {
		this.pioche = pioche;
		this.joueurs = joueurs;
		this.tas = tas;

		for (int i = 0; i < this.pioche.getCartes().size(); i++) {
			this.cartes.add(pioche.getCartes().get(i));
		}

		for (int i = 0; i < this.tas.size(); i++) {
			for (int j = 0; j < this.tas.get(i).getCartes().size(); j++) {
				this.cartes.add(this.tas.get(i).getCartes().get(j));
			}
		}
	}

	/**
	 * Recupere l'index du joueur dans la liste des joueurs (delegation)
	 * 
	 * @param Joueur
	 * @return position du joueur dans la liste
	 */
	private int getJoueurIndex(Joueur joueur) {
		return this.joueurs.indexOf(joueur);
	}

	/**
	 * Methode d'arbitrage : Verification que la carte est bien enregistree et jouee
	 * correctement
	 * 
	 * @param carte  Instance d'une carte
	 * @param joueur Instance du joueur realisant l'action
	 * @return true ou false
	 */
	public boolean isCarteValide(Carte carte, Joueur joueur) {
		if (!this.cartes.contains(carte)) {
			System.out.println("ici");
			return false;
		}

		/*
		 * if (this.pioche.getCartes().contains(carte)) { return false; }
		 */

		for (int i = 0; i < this.tas.size(); i++) {
			if (this.tas.get(i).getCartes().contains(carte)) {
				System.out.println("ici 2");
				return false;
			}
		}

		List<Carte> main = joueur.getMain();
		if (!main.contains(carte)) {
			System.out.println(carte.getValeur());

			for (int i = 0; i < main.size(); i++) {
				System.out.println(carte.toString());
			}

			return false;
		}

		return true;

	}

	public List<Tas> getTas() {
		return this.tas;
	}

	public Tas getTasById(int id) {
		return this.tas.get(id);
	}

	/**
	 * Savoir a qui est le tour
	 * 
	 * @return index du prochain joueur
	 */
	public int getTour() {
		return this.tour;
	}

	/**
	 * Nombre de cartes jouees ce tour
	 * 
	 * @return nombre de cartes
	 */
	public int getNbCartesTour() {
		return this.nbCartesTour;
	}

	/**
	 * Jouer une carte sur un tas specifique
	 * 
	 * @param tasId  Numero du tas (visible via l'affichage)
	 * @param carte  Carte a jouer (doit faire partie du jeu)
	 * @param joueur Joueur effectuant l'action
	 */
	public void jouer(int tasId, Carte carte, Joueur joueur) throws CoupInvalideException, ActionIllegaleException {
		if (joueur != getJoueurActuel()) {
			throw new ActionIllegaleException("Ce n'est pas votre tour !");
		}

		if (this.partieFinie) {
			throw new ActionIllegaleException("La partie est terminee.");
		}

		if (!this.isCarteValide(carte, joueur)) {
			throw new ActionIllegaleException("TENTATIVE DE TRICHE : Carte invalide");
		}

		Tas tas = this.getTasById(tasId);

		boolean coupValide = tas.poser(carte, joueur);

		if (!coupValide) {
			throw new CoupInvalideException("Ce coup n'est pas valide.");
		}

		this.nbCartesTour++;
	}

	/**
	 * Obtenir le joueur qui doit jouer ce tour-ci
	 */
	private Joueur getJoueurActuel() {
		return this.joueurs.get(this.tour % this.joueurs.size());
	}

	/**
	 * Le joueur passe son tour, si 2 cartes ont ete posees ou 1 si la pioche est
	 * vide
	 * 
	 * @return true si le changement de tour est autorise, false si non
	 */
	public boolean passerTour() {
		if (this.pioche.getCartes().size() != 0 && this.nbCartesTour < 2) {
			return false;
		}

		if (this.pioche.getCartes().size() == 0 && this.nbCartesTour == 0) {
			return false;
		}

		if (this.isPartieFinie()) {
			return false;
		}

		this.getJoueurActuel().piocher(this);

		this.tour++;

		this.nbCartesTour = 0;
		return true;
	}

	/**
	 * Calculer le nombre de cartes a jouer avant de pouvoir passer le tour
	 * 
	 * @return int nombre de cartes a jouer avant la fin du tour
	 */
	public int nbCartesAJouer() {
		if (this.getPioche().getCartes().size() == 0) {
			return 1 - this.getNbCartesTour();
		}

		return 2 - this.getNbCartesTour();
	}

	/**
	 * Determiner si le joueur actuel ne peut plus jouer, si oui, la partie s'arrete
	 * 
	 * @return true si la partie s'arrete, false si non
	 */
	public boolean isPartieFinie() {
		if (this.partieFinie) {
			return true;
		}

		Joueur joueur = this.getJoueurActuel();
		int nbCoupsPossibles = 0;
		for (Carte carte : joueur.getMain()) {
			for (Tas tas : this.getTas()) {
				nbCoupsPossibles = tas.isCoupValide(carte) ? nbCoupsPossibles + 1 : nbCoupsPossibles;
			}
		}

		if (nbCoupsPossibles < nbCartesAJouer()) {
			this.partieFinie = true;
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @return le nombre de cartes maximum par joueurs
	 */
	public int getNbCartesMax() {
		int nbCartesMax;
		switch (this.getJoueurs().size()) {
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
		return nbCartesMax;
	}

	/**
	 * Fabrique pour une partie "cle-en-mains"
	 * 
	 * @param joueurs      Liste des joueurs
	 * @param cheminPioche Chemin vers le fichier contenant la pioche
	 * @return Instance du Jeu
	 */
	public static Jeu lancerPartie(List<Joueur> joueurs, String cheminPioche) {
		Pioche pioche = Pioche.fromFile(cheminPioche);

		Tas tasDescendant1 = new TasDescendant();
		Tas tasDescendant2 = new TasDescendant();
		Tas tasAscendant1 = new TasAscendant();
		Tas tasAscendant2 = new TasAscendant();

		List<Tas> tas = new ArrayList<Tas>();
		tas.add(tasDescendant1);
		tas.add(tasDescendant2);
		tas.add(tasAscendant1);
		tas.add(tasAscendant2);

		return new Jeu(pioche, joueurs, tas);
	}

	/**
	 * Uniquement pour les tests Ne pose pas de pb de securite car les joueurs
	 * jouent ensemble
	 * 
	 * @param joueurId index du joueur dans le jeu
	 */
	public void setTour(int joueurId) {
		this.tour = joueurId;
	}

	public List<Joueur> getJoueurs() {
		return this.joueurs;
	}

	public Pioche getPioche() {
		return this.pioche;
	}

	/**
	 * Score final de la partie
	 * 
	 * @return somme des cartes dans les mains des joueurs et dans la pioche
	 */
	public int score() {
		int somme = this.pioche.getCartes().size();

		for (Joueur joueur : this.joueurs) {
			somme += joueur.getMain().size();
		}

		return somme;
	}

	/**
	 * 
	 * @return si la victoire est atteinte ou non
	 */
	public Boolean isVictoire() {
		return this.score() <= 0;
	}

	/**
	 * Affichage (textuel) de la partie
	 * 
	 * @return String affichant la partie
	 */
	public String toString() {
		String affichage = "Dernieres cartes posees : \n";

		for (int i = 0; i < this.tas.size(); i++) {
			Tas tas = this.tas.get(i);
			affichage += tas.toString(i) + "\n";
		}

		return affichage;
	}

}
