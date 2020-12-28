/**
 * 
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @author Adrien Jallais
 *
 */
public class MainScene extends Scene {
	private BorderPane border;

	public MainScene(Pane center) {
		super(new BorderPane());
		border = (BorderPane) (this.getRoot());
		border.setPadding(new Insets(10, 20, 10, 20)); // top right bottom left
		border.setCenter(center);
	}

	public Node getPane() {
		return border.getCenter();
	}

}
