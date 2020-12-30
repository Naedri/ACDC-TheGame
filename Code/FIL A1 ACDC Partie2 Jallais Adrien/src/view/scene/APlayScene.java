/**
 * 
 */
package view.scene;

import javafx.scene.layout.BorderPane;

/**
 * @author Adrien Jallais
 *
 */
public abstract class APlayScene extends MainScene {
	public APlayScene() {
		super(new BorderPane());
		BorderPane root = (BorderPane) (this.getRoot());
	}

}
