/**
 * 
 */
package view.component;

import api.Carte;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Adrien Jallais
 *
 */
public interface IOneCard {
	public Carte getCardAPI();

	public void setCardAPI(Carte cardAPI);

	public SimpleIntegerProperty getCarteObs();

}
