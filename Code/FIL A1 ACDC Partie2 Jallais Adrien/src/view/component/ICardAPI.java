/**
 * 
 */
package view.component;

import api.Carte;
import javafx.beans.property.StringProperty;

/**
 * @author Adrien Jallais
 *
 */
public interface ICardAPI {
	public Carte getCardAPI();

	public void setCardAPI(Carte cardAPI);

	public StringProperty getCarteObs();

}
