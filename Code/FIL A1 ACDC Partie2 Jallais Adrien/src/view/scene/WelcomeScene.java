/**
 * 
 */
package view.scene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Main;
import controller.Services;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import view.constant.ColorApp;
import view.constant.FontApp;

/**
 * @author Adrien Jallais
 *
 */
public class WelcomeScene extends MainScene {
	private Media music;
	private MediaPlayer player;
	private BorderPane pane;

	private Label labelSubTitle;
	private boolean subTitleOn = true;
	private Timeline timeline;

	public WelcomeScene() {
		super(new BorderPane());
		pane = (BorderPane) (super.getPane());
		pane.setCenter(createCenterPane());
		pane.setBottom(createBottomPane());
		BorderPane.setAlignment(pane.getBottom(), Pos.CENTER);
		this.addingEnter();
		this.addingMusic();
		// set background
		this.getBorder().setBackground(
				new Background(new BackgroundFill(ColorApp.INFOL.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public Node createCenterPane() {
		// img
		String imgName = "knight_640.png";
		// String imgName = "Castle_Free.png";
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
		label.setTextFill(ColorApp.BADD.getColor());
		label.setAlignment(Pos.CENTER);
		// stack
		StackPane stack;
		stack = new StackPane();
		stack.getChildren().addAll(img, label);
		return stack;
	}

	public Node createBottomPane() {
		// subtitle
		labelSubTitle = new Label(Main.d.get("WELCOME_start"));
		labelSubTitle.setFont(FontApp.SUBTITLE.getFont());
		labelSubTitle.setTextFill(ColorApp.GOODD.getColor());
		labelSubTitle.setAlignment(Pos.CENTER);
		// pane
		StackPane pane = new StackPane();
		pane.getChildren().add(labelSubTitle);
		pane.setPadding(new Insets(0, 30, 60, 30));
		pane.setAlignment(Pos.TOP_CENTER);
		// maintaining size
		pane.setPrefSize(80, 20);
		return pane;
	}

	/**
	 * adding the music effect
	 */
	private void addingMusic() {
		String pathMusic = "src" + File.separator + "multimedia" + File.separator + "Welcome.mp3";
		music = new Media(new File(pathMusic).toURI().toString());
		player = new MediaPlayer(music);
	}

	/**
	 * the current scene will not change until the user has not pressed enter
	 * https://edencoding.com/stage-controller/
	 * https://coderslegacy.com/java/switch-between-scenes-in-javafx/
	 */
	private void addingEnter() {
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent k) {
				if (k.getCode().equals(KeyCode.ENTER)) {
					player.stop();
					timeline.stop();
					Services.changeScene(WelcomeScene.this, new MenuScene());
				}
			}
		});
	}

	/**
	 * adding music and flick effect only when the scene is loaded
	 */
	@Override
	public void triggerShow() {
		player.play();
		triggerBlink();
	}

	/**
	 * Will make flicking the bottom pane label; Every 500 milliseconds ;
	 * 
	 * @source https://stackoverflow.com/questions/9966136/javafx-periodic-background-task
	 */
	private void triggerBlink() {
		timeline = new Timeline(new KeyFrame(Duration.millis(500), ae -> {
			blinkSubTitle();
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	/**
	 * to make flicking the subtitle
	 */
	private void blinkSubTitle() {
		this.labelSubTitle.setVisible(this.subTitleOn);
		this.subTitleOn = !this.subTitleOn;
	}

}
