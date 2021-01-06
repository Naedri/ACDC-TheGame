/**
 * 
 */
package application;

import java.util.ArrayList;
import java.util.List;

import api.Joueur;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nls.DefaultNLS;
import nls.FrNLS;
import view.scene.AuthorsScene;
import view.scene.MainScene;
import view.scene.WelcomeScene;

/**
 * @author Adrien Jallais
 *
 */
public class Main extends Application {
	public static DefaultNLS d;
	private static Rectangle2D primaryScreenBounds;
	public static Stage mainStage;
	private MainScene scene;

	private String path1;
	private List<Joueur> joueurs;
	private Joueur joueur;

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
		List<Joueur> joueurs = new ArrayList<Joueur>();
		joueur = new Joueur("Lorem lipsum");
		joueurs.add(joueur);
		path1 = "../../Jeu_essai/game1.txt"; // relative path
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		// TODO select WelcomeScene
		this.scene = new WelcomeScene();
		// this.scene = new MenuScene();
		// this.scene = new RulesScene();
		// this.scene = new HumanScene(joueurs, path1);
		this.scene = new AuthorsScene();
		// joueur
		mainStage.setTitle(d.get("STAGE_title"));
		mainStage.setWidth(primaryScreenBounds.getWidth() * 0.9);
		mainStage.setHeight(primaryScreenBounds.getHeight() * 0.9);
		mainStage.setResizable(true);
		mainStage.centerOnScreen();
		mainStage.setScene(this.scene);
		// primaryStage.onShowingProperty();
		mainStage.show();
	}

	@Override
	public void stop() throws Exception {

	}

}
