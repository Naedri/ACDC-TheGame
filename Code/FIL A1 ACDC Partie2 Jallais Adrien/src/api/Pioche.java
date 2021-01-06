package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import view.exception.DuplicateCardFromDraw;
import view.exception.WrongSizeForDrawBuild;

public class Pioche {

	private List<Carte> cartes;

	public Pioche(List<Carte> cartes) {
		this.cartes = cartes;
	}

	public Pioche() {
		this.cartes = new ArrayList<Carte>();
	}

	/**
	 * Initialiser la pioche a partir d'un fichier
	 * 
	 * @param chemin Chemin du fichier txt contenant les valeurs de la pioche
	 * @return Pioche
	 */
	public static Pioche fromFile(String chemin) {
		List<Carte> pioche = new ArrayList<Carte>();
		if (chemin != null) {
			try {
				BufferedReader fichier = new BufferedReader(new FileReader(chemin));

				String line;

				do {
					line = fichier.readLine();

					if (line == null) {
						continue;
					}

					int value = Integer.parseInt(line);
					Carte carte = new Carte(value);
					if (isCarteValide(carte, pioche)) {
						pioche.add(new Carte(value));
					} else {
						throw new DuplicateCardFromDraw();
					}
				} while (line != null);
				if (pioche.size() != 98) {
					throw new WrongSizeForDrawBuild();
				}
				return new Pioche(pioche);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			for (int i = 1; i < 99; i++) {
				Carte carte = new Carte(i);
				pioche.add(carte);
			}
			Collections.shuffle(pioche);
			return new Pioche(pioche);
		}
	}

	/**
	 * Piocher un nombre defini de cartes
	 * 
	 * @param nombre Le nombre de cartes a piocher
	 * @return Une List des cartes piochées
	 */
	public List<Carte> piocher(int nombre) {

		if (nombre > this.cartes.size()) {
			return null;
		}

		List<Carte> cartesPiochees;
		List<Carte> buffer = new ArrayList<Carte>();
		buffer.addAll(this.cartes);

		try {
			cartesPiochees = this.cartes.subList(0, nombre);
			buffer.removeAll(cartesPiochees);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

		this.cartes = buffer;
		return cartesPiochees;
	}

	public List<Carte> getCartes() {
		return this.cartes;
	}

	public String toString() {
		String res = "[";

		for (Carte i : this.cartes) {
			res += i.getValeur() + " ";
		}

		return res + "]";

	}

	/**
	 * Observe la redondance d'une carte dans la pioche
	 * 
	 * @param carte  Carte à évaluer
	 * @param pioche collectrions observée
	 * @return true si la carte n'est pas déjà dans la pioce
	 */
	public static boolean isCarteValide(Carte carte, List<Carte> pioche) {
		return !pioche.contains(carte);
	}

};