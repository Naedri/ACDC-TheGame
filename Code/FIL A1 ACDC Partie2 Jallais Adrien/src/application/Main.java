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
import nls.EnNLS;
import nls.FrNLS;
import nls.LanguageApp;
import view.scene.MainScene;
import view.scene.ParameterScene;

/**
 * @author Adrien Jallais
 *
 */
public class Main extends Application {

	private static Rectangle2D primaryScreenBounds;
	public static Stage mainStage;
	private MainScene scene;

	private static LanguageApp lang;
	public static DefaultNLS d;
	private static String pathDeck;

	private List<Joueur> joueurs;
	private Joueur joueur;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setLang(LanguageApp.FRENCH);
		pathDeck = "../../Jeu_essai/game1.txt"; // default
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		joueurs = new ArrayList<Joueur>();
		joueur = new Joueur("Lorem lipsum");
		joueurs.add(joueur);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		// TODO select WelcomeScene
		// this.scene = new WelcomeScene();
		// this.scene = new MenuScene();
		// this.scene = new RulesScene();
		// this.scene = new HumanScene(joueurs, getPathDeck());
		// this.scene = new AuthorScene();
		this.scene = new ParameterScene();
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

	/**
	 * @return the lang
	 */
	public static LanguageApp getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public static void setLang(LanguageApp lang) {
		Main.lang = lang;
		Main.updateNLS();
	}

	private static void updateNLS() {
		switch (lang) {
		case FRENCH: {
			Main.d = new FrNLS();
			break;
		}
		case ENGLISH: {
			Main.d = new EnNLS();
			break;
		}
		default:
			Main.d = new FrNLS();
		}
	}

	/**
	 * @return the pathDeck
	 */
	public static String getPathDeck() {
		return Main.pathDeck;
	}

	/**
	 * @param pathDeck the pathDeck to set
	 */
	public static void setPathDeck(String pathDeck) {
		Main.pathDeck = pathDeck;
	}

}
