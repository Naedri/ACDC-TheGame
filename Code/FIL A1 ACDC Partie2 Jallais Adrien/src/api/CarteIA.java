package api;

/**
 * Couche haute pour l'IA Permet d'encapsuler tas, carte et ecart du tas en une
 * classe
 * 
 * @author Nicolas Kirchhoffer <nicolas.kirchhoffer@imt-atlantique.net>
 */
public class CarteIA implements Comparable<CarteIA> {

	private Carte carte;
	private int tas;
	private int ecart;

	/**
	 * Constructeur par defaut
	 * 
	 * @param carte Instance de carte
	 * @param tas   Instance de tas
	 * @param ecart Valeur d'ecart avec la derniere carte du tas
	 */
	public CarteIA(Carte carte, int tas, int ecart) {
		this.carte = carte;
		this.tas = tas;
		this.ecart = ecart;
	}

	/**
	 * Getter de l'ecart
	 * 
	 * @return ecart de cette carte avec la derniere du tas
	 */
	public int getEcart() {
		return this.ecart;
	}

	/**
	 * Getter de l'instance de carte
	 * 
	 * @return instance de la carte
	 */
	public Carte getCarte() {
		return this.carte;
	}

	/**
	 * Getter de l'instance du tas
	 * 
	 * @return instance du tas
	 */
	public int getTas() {
		return this.tas;
	}

	/**
	 * Affichage de l'ecart de la carte avec le tas concerne
	 * 
	 * @return un String contenant l'ecart de la carte
	 */
	public String toString() {
		return Integer.toString(this.getEcart());
	}

	/**
	 * Methode d'implementation de Comparable<CarteIA>
	 * 
	 * @param CarteIA instance de la carte a comparer
	 * @return 1 si l'ecart de cette carte est inferieur a celle en parametre, 0 si
	 *         égal, -1 si superieur
	 */
	@Override
	public int compareTo(CarteIA c) {
		if (this.getEcart() == c.getEcart()) {
			return 0;
		}

		return (this.getEcart() < c.getEcart()) ? 1 : -1;
	}

}
