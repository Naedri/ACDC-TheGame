/**
 * 
 */
package view.scene;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import view.label.MainLabel;

/**
 * @author Adrien Jallais
 *
 */
public class MainScene extends Scene {
	private BorderPane border;
	private GridPane pane;

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
		Label label = new MainLabel(Main.d.get("MAIN_Signature"));
		pane = new GridPane();
		pane.setPadding(new Insets(10, 60, 10 - border.getInsets().getBottom(), 60));
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().add(label);
		return pane;
	}

	public Node getPane() {
		return border.getCenter();
	}

	// TODO erase
	public Parent getRoot2() {
		return border;
	}

	public Window getWindow2() {
		return this.getRoot().getScene().getWindow();
	}

}
