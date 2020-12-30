/**
 * 
 */
package view.scene;

import application.Main;
import controller.Services;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.button.ButtonQuit;
import view.constant.Spacing;

/**
 * @author Adrien Jallais
 *
 */
public abstract class APlayScene extends MainScene {
	protected BorderPane pane;
	protected Pane leftP;
	protected Pane topP;
	protected Pane rightP;
	protected Pane botP;

	public APlayScene() {
		super(new BorderPane());
		leftP = createLeftPane();
		pane = (BorderPane) (super.getPane());
		pane.setTop(createTopPane());
		pane.setLeft(createLeftPane());
		pane.setBottom(createBottomPane());
		pane.setRight(createRightPane());
		pane.setCenter(createCenterPane());
		// BorderPane.setAlignment(pane.getBottom(), Pos.CENTER);
	}

	protected abstract Node createTopPane();

	protected Pane createLeftPane() {
		VBox pane = new VBox();
		Button bM = new ButtonQuit(Main.d.get("COMMON_menu"));
		bM.setOnAction((ActionEvent e) -> {
			Services.changeScene(this, new MenuScene());
		});
		Button bQ = new ButtonQuit(Main.d.get("COMMON_exit"));
		bQ.setOnAction((ActionEvent e) -> {
			Services.quitApp(this);
		});
		pane.getChildren().addAll(bM, bQ);
		pane.setSpacing(Spacing.MEDIUM.getSpace());
		pane.setAlignment(Pos.CENTER);

		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	protected abstract Node createRightPane();

	protected abstract Node createBottomPane();

	protected abstract Node createCenterPane();

}
