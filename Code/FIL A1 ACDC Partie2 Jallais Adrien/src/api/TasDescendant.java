package api;

public class TasDescendant extends Tas implements ITas {

	public TasDescendant() {
		super();
	}

	@Override
	public Carte getDerniereCarte() {
		if (this.getTaille() != 0) {
			return super.getDerniereCarte();
		}

		return new Carte(100);
	}

	/**
	 * Est-ce que ce coup est valide ?
	 * 
	 * @param carte Instance de carte
	 * @return true ou false
	 */
	public boolean isCoupValide(Carte carte) {
		Carte derniereCarte = this.getDerniereCarte();

		if (carte.getValeur() - derniereCarte.getValeur() == 10) {
			return true;
		}

		if (carte.getValeur() > derniereCarte.getValeur()) {
			return false;
		}

		if (carte.getValeur() > derniereCarte.getValeur()) {
			return false;
		}

		return true;
	}

	public String toString(int i) {
		return "Tas " + i + " (descendant) : " + this.getDerniereCarte().getValeur() + "\n";
	}

	public String toString() {
		return "Tas descendant : " + this.getDerniereCarte().getValeur() + "\n";
	}

}
