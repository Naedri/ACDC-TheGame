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
import view.scene.WelcomeScene;

/**
 * @author Adrien Jallais
 *
 */
public class Main extends Application {
	public static DefaultNLS d;
	private static Rectangle2D primaryScreenBounds;
	private WelcomeScene scene;

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

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO select WelcomeScene
		this.scene = new WelcomeScene();
		// this.scene = new MenuScene();
		// this.scene = new RulesScene();
		// this.scene = new HumanScene();
		primaryStage.setTitle(d.get("STAGE_title"));
		primaryStage.setWidth(primaryScreenBounds.getWidth() * 0.9);
		primaryStage.setHeight(primaryScreenBounds.getHeight() * 0.9);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.setScene(this.scene);
		primaryStage.show();

		scene.addingFlick();
	}

	@Override
	public void stop() throws Exception {

	}

}
