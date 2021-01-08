/**
 * 
 */
package view.scene;

import application.Main;
import controller.Services;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import nls.LanguageApp;
import view.button.ButtonQuit;
import view.constant.InsetsApp;
import view.constant.Spacing;

/**
 * @author Adrien Jallais
 *
 */
public class RulesScene extends MainScene {

	protected BorderPane pane;

	public RulesScene() {
		super(new BorderPane());
		pane = (BorderPane) (super.getPane());
		pane.setLeft(createLeftPane());
		pane.setCenter(createWebView());
	}

	/**
	 * 
	 * @return VBox containing the exit and menu buttons
	 */
	public Node createLeftPane() {
		VBox pane = new VBox();
		Button bM = new ButtonQuit(Main.d.get("COMMON_menu"));
		bM.setOnAction((ActionEvent e) -> {
			Services.changeScene(this, new MenuScene());
		});
		Button bQ = new ButtonQuit(Main.d.get("COMMON_exit"));
		bQ.setOnAction((ActionEvent e) -> {
			Services.quitApp(this);
		});
		pane.getChildren().addAll(bM, bQ);
		pane.setSpacing(Spacing.HIGH.getSpace());
		pane.setAlignment(Pos.CENTER_LEFT);
		pane.setPadding(InsetsApp.LITTLE.getInsets());
		return pane;
	}

	/**
	 * according the language given, the scene will show the apporpriate rules
	 * version
	 * 
	 * @return
	 */
	private WebView createWebView() {
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
