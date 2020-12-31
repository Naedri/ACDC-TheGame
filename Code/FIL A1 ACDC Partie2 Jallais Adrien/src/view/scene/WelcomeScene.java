/**
 * 
 */
package view.scene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Main;
import controller.Services;
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
import view.constant.Col;
import view.constant.FontApp;

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

	private Node createBottomPane() {
		// subtitle
		Label label = new Label(Main.d.get("WELCOME_start"));
		label.setFont(FontApp.SUBTITLE.getFont());
		label.setTextFill(Col.GOODD.getColor());
		label.setAlignment(Pos.CENTER);
		// pane
		StackPane pane = new StackPane();
		pane.getChildren().add(label);
		pane.setPadding(new Insets(0, 30, 60, 30));
		pane.setAlignment(Pos.TOP_CENTER);

		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	private Node createCenterPane() {
		// img
		String imgName = "knight_640.png";
//		String imgName = "Castle_Free.png";
		String pathPict = "src" + File.separator + "multimedia" + File.separator + imgName;
		ImageView img;
		try {
			img = new ImageView(new Image(new FileInputStream(pathPict)));
		} catch (FileNotFoundException e) {
			img = null;
			e.printStackTrace();
		}
		if (img != null) {
			img.setSmooth(true);
			// img.setFitHeight(500);
			img.setPreserveRatio(true);
		}
		// title
		Label label = new Label(Main.d.get("WELCOME_title"));
		label.setFont(FontApp.TITLE.getFont());
		label.setTextFill(Col.BADD.getColor());
		label.setAlignment(Pos.CENTER);

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
					Services.changeScene(WelcomeScene.this, new MenuScene());
				}
			}
		});
	}

}
