/**
 * 
 */
package view.component;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * @author Adrien Jallais
 *
 */
public abstract class ACardComponent extends Button implements ICardView {

	private Boolean selected;
	private SimpleBooleanProperty selectedObs;

	public ACardComponent() {
		super();
		selected = false;
		initSelectedObs();
	}

	public ACardComponent(String text, Node graphic) {
		super(text, graphic);
		selected = false;
		initSelectedObs();

	}

	public ACardComponent(String text) {
		super(text);
		selected = false;
		initSelectedObs();

	}

	private void initSelectedObs() {
		selectedObs = new SimpleBooleanProperty(selected);
	}

	@Override
	public void switchActive() {
		selected = !selected;
		selectedObs.set(!selectedObs.getValue());
	}

	@Override
	public Boolean isActive() {
		return selected;
	}

	public SimpleBooleanProperty getActiveObs() {
		return selectedObs;
	}

}
