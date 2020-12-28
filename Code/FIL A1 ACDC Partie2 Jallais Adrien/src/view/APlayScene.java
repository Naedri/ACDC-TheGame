/**
 * 
 */
package view;

import javafx.scene.layout.BorderPane;

/**
 * @author Adrien Jallais
 *
 */
public abstract class APlayScene extends MainScene {
	public APlayScene() throws Exception {
		super(new BorderPane());
		BorderPane root = (BorderPane) (this.getRoot());
	}

}
