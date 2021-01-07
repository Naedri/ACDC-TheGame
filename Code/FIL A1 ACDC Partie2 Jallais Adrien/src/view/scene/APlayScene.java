/**
 * 
 */
package view.scene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import api.Carte;
import api.Jeu;
import api.Joueur;
import api.Tas;
import api.TasAscendant;
import api.TasDescendant;
import application.Main;
import controller.Services;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.button.ButtonQuit;
import view.component.CardComponent;
import view.component.DialogComponent;
import view.component.DrawComponent;
import view.component.HandComponent;
import view.component.LayComponent;
import view.component.ScoreComponent;
import view.constant.ColorApp;
import view.constant.InsetsApp;
import view.constant.Spacing;
import view.label.MainLabel;

/**
 * @author Adrien Jallais
 *
 */
public abstract class APlayScene extends MainScene {

	protected BorderPane panePlay;
	protected List<CardComponent> cardL;
	protected List<LayComponent> layDscL;
	protected List<LayComponent> layAscL;
	protected List<LayComponent> layL;
	protected DrawComponent draw;
	protected HandComponent hand;
	protected ScoreComponent scoreP;
	protected DialogComponent dialogP;

	protected LayComponent selectedLay;
	protected Joueur joueur;
	protected Jeu jeu;

	public APlayScene(String modeName, List<Joueur> joueurs, String pathDeck) {
		super(new BorderPane());
		initGame(joueurs, pathDeck);
		panePlay = (BorderPane) (super.getPane());
		// setting pane
		panePlay.setTop(createTopPane(modeName));
		panePlay.setLeft(createLeftPane());
		panePlay.setCenter(createCenterPane());
		panePlay.setBottom(createBottomPane());
		panePlay.setRight(createRightPane());

		// set background
		this.getBorder().setBackground(
				new Background(new BackgroundFill(ColorApp.GOODL.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
	}

	/**
	 * Load card and game to allow the creation of the component
	 * 
	 * @param joueurs
	 * @param pathDeck
	 */
	private void initGame(List<Joueur> joueurs, String pathDeck) {
		// jeu
		this.jeu = Jeu.lancerPartie(joueurs, pathDeck);
		// joueur
		this.joueur = joueurs.get(0);
		// score
		scoreP = new ScoreComponent(jeu.score());
		// dialog
		dialogP = new DialogComponent(Main.d.get("PLAY_dialog_init"));
		initCardGame();
		initLayPile();
	}

	/**
	 * Draw cards to update the list of Carte : CardL
	 */
	private void initCardGame() {
		joueur.piocher(jeu);
		updateCardL();
	}

	/**
	 * Reload the list of CardComponent : cardL, and sort it
	 */
	private void updateCardL() {
		cardL = new ArrayList<CardComponent>();
		List<Carte> handSorted = joueur.getMain();
		Collections.sort(handSorted);
		handSorted.forEach(carte -> {
			cardL.add(new CardComponent(carte));
		});
	}

	/**
	 * Alllow to draw (updateCardL) and to update the bottom pane
	 */
	protected void updateHand() {
		updateCardL();
		panePlay.setBottom(createBottomPane());
	}

	/**
	 * Init the lay component and gather it in a list : layL
	 */
	private void initLayPile() {
		layDscL = new ArrayList<LayComponent>();
		layAscL = new ArrayList<LayComponent>();
		layL = new ArrayList<LayComponent>();
		for (int i = 0; i < jeu.getTas().size(); i++) {
			Tas tas = jeu.getTasById(i);
			if (tas instanceof TasAscendant) {
				layAscL.add(new LayComponent(tas, i));
			} else if (tas instanceof TasDescendant) {
				layDscL.add(new LayComponent(tas, i));
			}
		}
		layL.addAll(layDscL);
		layL.addAll(layAscL);
	}

	/**
	 * Disable all Click events added to game component
	 */
	protected void disablePlaying() {
		layL.forEach(lay -> {
			lay.setClickable(false);
		});
		draw.setClickable(false);
		hand.setClickable(false);
	}

	/**
	 * Unselect all the component from the APLayScene which could have been selected
	 */
	protected void unSelectAll() {
		selectedLay = null;
		hand.setCardSelected(null);
		hand.unSelectCards();
		unSelectLays();
	}

	/**
	 * Unselect all lays
	 */
	protected void unSelectLays() {
		this.layL.forEach(lay -> {
			lay.setActive(false);
		});
	}

	/**
	 * 
	 * @param modeName either Human mode or IA mode
	 * @return StackPane with label containing the mode name
	 */
	private Node createTopPane(String modeName) {
		Label label = new MainLabel(modeName);
		StackPane pane = new StackPane();
		pane.setPadding(new Insets(InsetsApp.HIGH.getTop() - this.getBorder().getInsets().getBottom(),
				InsetsApp.HIGH.getRight(), InsetsApp.HIGH.getBot(), InsetsApp.HIGH.getLeft()));
		pane.getChildren().add(label);
		return pane;
	}

	/**
	 * 
	 * @return VBox containing the exit and menu buttons
	 */
	private Node createLeftPane() {
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
		pane.setAlignment(Pos.CENTER);
		return pane;
	}

	/**
	 * create the right pane with a VBox, including score and dialog box
	 */
	private Node createRightPane() {
		VBox pane = new VBox();
		pane.getChildren().addAll(scoreP, dialogP);
		pane.setSpacing(Spacing.HIGH.getSpace());
		pane.setAlignment(Pos.CENTER);
		return pane;
	}

	/**
	 * create the bottom pane with a HBox, including hand and draw component
	 */
	private Node createBottomPane() {
		Insets insets = InsetsApp.MEDIUM.getInsets();
		// hand
		hand = new HandComponent(cardL, cardL.get(0).getPrefWidth() * 0.2);
		hand.setPadding(insets);
		hand.setAlignment(Pos.CENTER_LEFT);
		// draw
		draw = new DrawComponent(cardL.get(0));
		StackPane drawStack = draw.makeSupported();
		drawStack.setPadding(insets);
		// merge
		HBox pane = new HBox(cardL.get(0).getPrefWidth() * 2);
		pane.getChildren().addAll(drawStack, hand);
		pane.setAlignment(Pos.CENTER);
		return pane;
	}

	/**
	 * Create the bottom pane with a HBox, including lays in HBox and an image
	 */
	private Node createCenterPane() {
		Insets insets = InsetsApp.HIGH.getInsets();
		// descending
		HBox descP = new HBox(Spacing.HIGH.getSpace());
		layDscL.forEach(lay -> {
			StackPane layStack = lay.makeSupported();
			layStack.setPadding(insets);
			descP.getChildren().add(layStack);
		});
		descP.setAlignment(Pos.CENTER);
		// ascending
		HBox ascP = new HBox(Spacing.HIGH.getSpace());
		layAscL.forEach(lay -> {
			StackPane layStack = lay.makeSupported();
			layStack.setPadding(insets);
			ascP.getChildren().add(layStack);
		});
		ascP.setAlignment(Pos.CENTER);
		// img
		String pathPict = "src" + File.separator + "multimedia" + File.separator + "Castle_Transparent.png";
		ImageView img;
		try {
			img = new ImageView(new Image(new FileInputStream(pathPict)));
		} catch (FileNotFoundException e) {
			img = null;
			e.printStackTrace();
		}
		if (img != null) {
			img.setSmooth(true);
			img.setFitHeight(200);
			img.setPreserveRatio(true);
		}
		// merge
		VBox pane = new VBox();
		if (Main.isSmallScreen()) {
			pane.getChildren().addAll(descP, ascP);
		} else {
			pane.getChildren().addAll(descP, img, ascP);
		}
		pane.setSpacing(Spacing.HIGH.getSpace());
		pane.setAlignment(Pos.CENTER);
		return pane;
	}
}
