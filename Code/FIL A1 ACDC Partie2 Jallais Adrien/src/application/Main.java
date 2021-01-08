/**
 * 
 */
package application;

import java.util.ArrayList;
import java.util.List;

import api.Joueur;
import api.JoueurIA;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nls.DefaultNLS;
import nls.EnNLS;
import nls.FrNLS;
import nls.LanguageApp;
import view.scene.IAScene;
import view.scene.MainScene;

/**
 * @author Adrien Jallais
 *
 */
public class Main extends Application {

	private static Rectangle2D primaryScreenBounds;
	public static Stage mainStage;
	private MainScene scene;
	private static boolean smallScreen = false;

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
		// pathDeck = "../../Jeu_essai/game1.txt"; // default
		pathDeck = null; // default
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		if (primaryScreenBounds.getHeight() < 720.0) {
			Main.smallScreen = true;
		}
		// Human
		joueurs = new ArrayList<Joueur>();
		joueur = new Joueur("Lorem lipsum");
		joueurs.add(joueur);
		// IA
		JoueurIA ai = new JoueurIA();
		joueurs = new ArrayList<Joueur>();
		joueurs.add(ai);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		// TODO select WelcomeScene
		// this.scene = new WelcomeScene();
		// this.scene = new MenuScene();
		// this.scene = new RulesScene();
//		this.scene = new HumanScene(joueurs, getPathDeck());
		// this.scene = new AuthorScene();
		// this.scene = new ParameterScene();
		this.scene = new IAScene(getPathDeck());
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
	 * Get the path of the file from where the deck could be build
	 * 
	 * @return the pathDeck
	 */
	public static String getPathDeck() {
		return Main.pathDeck;
	}

	/**
	 * Set the path of the file from where the deck could be build
	 * 
	 * @param pathDeck the pathDeck to set
	 */
	public static void setPathDeck(String pathDeck) {
		Main.pathDeck = pathDeck;
	}

	/**
	 * To know if the current screen is small or not, in order to adapt the display
	 * of the picture
	 * 
	 * @return the smallScreen
	 */
	public static boolean isSmallScreen() {
		return smallScreen;
	}

}
