package view.scene;

import java.util.ArrayList;
import java.util.List;

import api.Joueur;
import api.JoueurIA;
import application.Main;
import controller.Services;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.button.ButtonChangeScene;
import view.constant.Spacing;

public class MenuScene extends AMainScene {
	private VBox vbox;

	private Button bPH;
	private Button bPIA;
	private Button bP;
	private Button bR;
	private Button bA;
	private Button bE;

	public MenuScene() {
		super(new VBox());
		vbox = (VBox) (super.getPane());
		bPH = new ButtonChangeScene(Main.d.get("MENU_playHuman"));
		bPIA = new ButtonChangeScene(Main.d.get("MENU_playIA"));
		bP = new ButtonChangeScene(Main.d.get("MENU_parameters"));
		bR = new ButtonChangeScene(Main.d.get("MENU_rules"));
		bA = new ButtonChangeScene(Main.d.get("MENU_authors"));
		bE = new ButtonChangeScene(Main.d.get("MENU_exit"));
		addingButtonEvent();
		createMenuBox();
	}

	private void addingButtonEvent() {
		bPH.setOnAction(new ButtonScene());
		bPIA.setOnAction(new ButtonScene());
		bP.setOnAction(new ButtonScene());
		bR.setOnAction(new ButtonScene());
		bA.setOnAction(new ButtonScene());
		bE.setOnAction(new ButtonScene());
	}

	/**
	 * Button to change of scene especially for menu scene
	 * 
	 * @author Adrien Jallais
	 *
	 */
	private class ButtonScene implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			if (e.getSource() == bPH) {
				// joueur HUMAN
				Joueur joueur = new Joueur("Lorem lipsum");
				List<Joueur> joueurs = new ArrayList<Joueur>();
				joueurs.add(joueur);
				String path = Main.getPathDeck();
				Services.changeScene(MenuScene.this, new HumanScene(joueurs, path));
			} else if (e.getSource() == bPIA) {
				// joueur IA
				Joueur joueur = new JoueurIA();
				List<Joueur> joueurs = new ArrayList<Joueur>();
				joueurs.add(joueur);
				String path = Main.getPathDeck();
				Services.changeScene(MenuScene.this, new IAScene(path));
			} else if (e.getSource() == bP) {
				Services.changeScene(MenuScene.this, new ParameterScene());
			} else if (e.getSource() == bR) {
				Services.changeScene(MenuScene.this, new RulesScene());
			} else if (e.getSource() == bA) {
				Services.changeScene(MenuScene.this, new AuthorScene());
			} else if (e.getSource() == bE) {
				Services.quitApp(MenuScene.this);
			}
		}
	}

	/*
	 * Adjust the VBox which gather all the button items from the menu
	 */
	private void createMenuBox() {
		vbox.setSpacing(Spacing.MEDIUM.getSpace());
		vbox.setMaxWidth(400);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(bPH, bPIA, bP, bR, bA, bE);
	}

}
