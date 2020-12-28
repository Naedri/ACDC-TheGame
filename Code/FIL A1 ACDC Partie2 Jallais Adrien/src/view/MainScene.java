/**
 * 
 */
package view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
		border.setBottom(createSignaturePane());

		// TODO Erase
		border.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private Pane createSignaturePane() {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10, 60, 0, 60));
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().add(new Label(Main.d.get("MAIN_Signature")));
		return pane;
	}

	public Node getPane() {
		return border.getCenter();
	}
}
