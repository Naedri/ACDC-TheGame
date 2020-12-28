/**
 * 
 */
package view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author Adrien Jallais
 *
 */
public class WelcomeScene extends MainScene {
	private VBox vbox;

	/**
	 * @param center
	 */
	public WelcomeScene(Pane center) {
		super(new VBox());
		vbox = (VBox) (super.getPane());
		createMenuBox();
	}

	private void createMenuBox() {
		// TODO Auto-generated method stub

	}

}
