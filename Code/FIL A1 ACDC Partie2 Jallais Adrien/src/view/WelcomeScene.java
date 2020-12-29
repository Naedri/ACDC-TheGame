/**
 * 
 */
package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Main;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.label.MainLabel;

/**
 * @author Adrien Jallais
 *
 */
public class WelcomeScene extends MainScene {
	private Media music;
	private MediaPlayer player;
	private BorderPane pane;

	public WelcomeScene() {
		super(new BorderPane());
		pane = (BorderPane) (super.getPane());
		pane.setCenter(createCenterPane());
		pane.setBottom(createBottomPane());
		BorderPane.setAlignment(pane.getBottom(), Pos.CENTER);
		this.addingEnter();
		this.addingMusic();

		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private Label createBottomPane() {
		Label label = new MainLabel(Main.d.get("WELCOME_Start"));
		// TODO Erase
		label.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return label;
	}

	private Node createCenterPane() {
		// img
		String pathPict = "src" + File.separator + "multimedia" + File.separator + "knight_640.png";
		ImageView img;
		try {
			img = new ImageView(new Image(new FileInputStream(pathPict)));
		} catch (FileNotFoundException e) {
			img = null;
			e.printStackTrace();
		}
		if (img != null) {
			img.setSmooth(true);
		}
		// title
		Label label = new MainLabel(Main.d.get("WELCOME_Title"));
		// stack
		StackPane stack;
		stack = new StackPane();
		stack.getChildren().addAll(img, label);
		// TODO Erase
		stack.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return stack;

	}

	private void addingMusic() {
		String pathMusic = "src" + File.separator + "multimedia" + File.separator + "Welcome.mp3";
		music = new Media(new File(pathMusic).toURI().toString());
		player = new MediaPlayer(music);
		player.play();
	}

	/**
	 * https://edencoding.com/stage-controller/
	 * https://coderslegacy.com/java/switch-between-scenes-in-javafx/
	 */
	private void addingEnter() {
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent k) {
				if (k.getCode().equals(KeyCode.ENTER)) {
					player.stop();
					System.out.println("0000");
					Stage thisStage;
					thisStage = (Stage) getRoot().getScene().getWindow();
					MainScene newScene = new MenuScene();
					thisStage.hide();
					thisStage.setScene(newScene);
					thisStage.show();
				}
			}
		});
	}

}
