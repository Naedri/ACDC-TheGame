/**
 * 
 */
package view.scene;

import application.Main;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import view.constant.InsetsApp;
import view.label.MainLabel;

/**
 * @author Adrien Jallais
 *
 */
public abstract class MainScene extends Scene {
	private BorderPane border;
	private StackPane pane;
	private boolean clikable = true;

	public MainScene(Pane center) {
		super(new BorderPane());
		border = (BorderPane) (this.getRoot());
		border.setPadding(InsetsApp.LITTLE.getInsets()); // top right bottom left
		border.setCenter(center);
		border.setBottom(createSignaturePane());
		this.afterShow();
		// TODO Erase
		border.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private Node createSignaturePane() {
		Label label = new MainLabel(Main.d.get("MAIN_Signature"));
		pane = new StackPane();

		pane.setPadding(new Insets(InsetsApp.HIGH.getTop(), InsetsApp.HIGH.getRight(),
				InsetsApp.HIGH.getBot() - this.getBorder().getInsets().getBottom(), InsetsApp.HIGH.getLeft()));
		pane.getChildren().add(label);

		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	public Node getPane() {
		return border.getCenter();
	}

	protected BorderPane getBorder() {
		return this.border;
	}

	/**
	 * adding effects only when the scene is loaded
	 */
	public void afterShow() {
		Main.mainStage.setOnShown(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				triggerShow();
			}
		});
	}

	/**
	 * what are the effect done after the stage is loaded
	 */
	public void triggerShow() {
		// BY default do nothing
	}

	protected boolean isClickable() {
		return clikable;
	}

	protected void setClickable(Boolean clickable) {
		this.clikable = clickable;
	}
}
