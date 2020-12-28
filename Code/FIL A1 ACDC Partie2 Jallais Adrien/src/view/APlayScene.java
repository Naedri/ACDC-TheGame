/**
 * 
 */
package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * @author Adrien Jallais
 *
 */
public abstract class APlayScene extends Scene {
	public APlayScene() throws Exception {
		super(new BorderPane());
		BorderPane root = (BorderPane) (this.getRoot());
	}

}
