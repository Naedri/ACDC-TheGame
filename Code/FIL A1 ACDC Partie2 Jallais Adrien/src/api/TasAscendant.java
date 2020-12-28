package api;

public class TasAscendant extends Tas implements ITas {

	public TasAscendant() {
		super();
	}

	@Override
	public Carte getDerniereCarte() {
		if (this.getTaille() != 0) {
			return super.getDerniereCarte();
		}

		return new Carte(0);
	}

	/**
	 * Est-ce possible de poser cette carte ?
	 * 
	 * @param carte Instance de la carte a poser
	 * @return true ou false
	 */
	public boolean isCoupValide(Carte carte) {
		Carte derniereCarte = this.getDerniereCarte();

		if (derniereCarte.getValeur() - carte.getValeur() == 10) {
			return true;
		}

		if (carte.getValeur() < derniereCarte.getValeur()) {
			return false;
		}

		if (carte.getValeur() < derniereCarte.getValeur()) {
			return false;
		}

		return true;
	}

	public String toString(int i) {
		return "Tas " + i + " (ascendant) : " + this.getDerniereCarte().getValeur() + "\n";
	}

	public String toString() {
		return "Tas ascendant : " + this.getDerniereCarte().getValeur() + "\n";
	}

}
