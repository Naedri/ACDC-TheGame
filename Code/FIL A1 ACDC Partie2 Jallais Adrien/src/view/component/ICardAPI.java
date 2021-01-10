/**
 * 
 */
package view.component;

import api.Carte;
import javafx.beans.property.StringProperty;

/**
 * Interface to be used when we want our component to include and to use the
 * basis element of the Game (API backend) : Carte
 * 
 * @author Adrien Jallais
 *
 */
public interface ICardAPI {
	public Carte getCardAPI();

	public void setCardAPI(Carte cardAPI);

	public StringProperty getCarteObs();
}
