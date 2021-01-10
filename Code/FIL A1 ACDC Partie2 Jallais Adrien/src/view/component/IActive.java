package view.component;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Interface to be used when we want to generate a state active for the
 * implemented component
 * 
 * @author Adrien Jallais
 *
 */
public interface IActive {

	public Boolean isActive();

	public void switchActive();

	public void setActive(Boolean state);

	/**
	 * To trigger action associated to changing boolean state
	 * 
	 * @return SimpleBooleanProperty
	 */
	public SimpleBooleanProperty getActiveObs();
}
