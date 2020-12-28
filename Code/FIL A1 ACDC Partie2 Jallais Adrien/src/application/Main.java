/**
 * 
 */
package application;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nls.DefaultNLS;
import nls.FrNLS;
import view.APlayScene;
import view.MenuScene;

/**
 * @author Adrien Jallais
 *
 */
public class Main extends Application {
	private static Rectangle2D primaryScreenBounds;
	private DefaultNLS d;
	private Scene menuScene;
	private APlayScene playScene;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		d = new FrNLS();
		this.menuScene = new MenuScene();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(d.get("STAGE_title"));
		primaryStage.setScene(this.menuScene);
		primaryStage.setWidth(primaryScreenBounds.getWidth() * 0.9);
		primaryStage.setHeight(primaryScreenBounds.getHeight() * 0.9);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {

	}

}
