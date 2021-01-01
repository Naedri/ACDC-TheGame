/**
 * 
 */
package view.component;

import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * @author Adrien Jallais
 *
 */
public abstract class ACardComponent extends Button implements ICardView {

	private Boolean selected;

	public ACardComponent() {
		super();
		selected = false;
	}

	public ACardComponent(String text, Node graphic) {
		super(text, graphic);
		selected = false;

	}

	public ACardComponent(String text) {
		super(text);
		selected = false;

	}

	@Override
	public void switchActive() {
		selected = !selected;
	}

	@Override
	public Boolean isActive() {
		return selected;
	}

}
