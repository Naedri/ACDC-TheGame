/**
 * 
 */
package application;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nls.DefaultNLS;
import nls.FrNLS;
import view.MainScene;
import view.RulesScene;

/**
 * @author Adrien Jallais
 *
 */
public class Main extends Application {
	public static DefaultNLS d;
	private static Rectangle2D primaryScreenBounds;
	private MainScene scene;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		d = new FrNLS();
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	}

	private void initScene() throws Exception {
		// this.scene = new WelcomeScene();
		// this.scene = new MenuScene();
		this.scene = new RulesScene();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.initScene();
		primaryStage.setTitle(d.get("STAGE_title"));
		primaryStage.setScene(this.scene);
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
