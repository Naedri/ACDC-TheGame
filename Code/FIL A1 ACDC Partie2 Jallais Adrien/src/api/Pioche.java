package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
		try {
			BufferedReader fichier = new BufferedReader(new FileReader(chemin));

			String line;
			List<Carte> pioche = new ArrayList<Carte>();
			do {
				line = fichier.readLine();

				if (line == null) {
					continue;
				}

				int value = Integer.parseInt(line);

				pioche.add(new Carte(value));
			} while (line != null);

			return new Pioche(pioche);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
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

};