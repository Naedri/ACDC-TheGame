package api;

import java.util.List;

/**
 * Interface regissant le polymorphisme de Tas
 * 
 * @author Nicolas Kirchhoffer <nicolas.kirchhoffer@imt-atlantique.net>
 */
public interface ITas {
	public boolean poser(Carte carte, Joueur joueur) throws ActionIllegaleException;

	public boolean isCoupValide(Carte carte);

	public Carte getDerniereCarte();

	public List<Carte> getCartes();

	public String toString(int i);
}
