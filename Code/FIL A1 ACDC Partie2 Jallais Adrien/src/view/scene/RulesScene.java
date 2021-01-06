/**
 * 
 */
package view.scene;

import application.Main;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import nls.LanguageApp;

/**
 * @author Adrien Jallais
 *
 */
public class RulesScene extends MainScene {

	/**
	 * @param center
	 */
	public RulesScene() {
		super(new BorderPane(RulesScene.createWebView()));
	}

	private static WebView createWebView() {
		WebView view;
		WebEngine engine;
		String url;
		LanguageApp lang = Main.d.getLanguage();
		switch (lang) {
		case FRENCH:
			url = "https://github.com/Naedri/ACDC-TheGame/blob/main/Consignes/the-game-francais.pdf";
			break;
		case ENGLISH:
			url = "https://github.com/Naedri/ACDC-TheGame/blob/main/Consignes/the-game-english.pdf";
			break;
		default:
			url = "https://github.com/Naedri/ACDC-TheGame/blob/main/Consignes/the-game-english.pdf";
			break;
		}
		view = new WebView();
		engine = view.getEngine();
		engine.load(url);
		return view;
	}
}
