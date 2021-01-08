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
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import view.exception.MissHandCardException;
import view.exception.MissLayCardException;
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
	// protected CardComponent selectedCard;
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
		// adding effect
		this.addActionLay();
		this.addActionHand();
		this.addActionDraw();
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
		hand = new HandComponent(cardL, cardL.get(0).getPrefWidth() * 0.2, jeu.getNbCartesMax());
		hand.setPadding(insets);
		hand.setAlignment(Pos.CENTER_LEFT);
		// draw
		draw = new DrawComponent(cardL.get(0));
		StackPane drawStack = draw.makeSupported();
		drawStack.setPadding(insets);
		// merge
		HBox pane = new HBox(cardL.get(0).getPrefWidth() * 2);
		pane.getChildren().addAll(drawStack, hand);
		pane.setAlignment(Pos.CENTER_LEFT);
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

	/**
	 * Setting an OnMouseClicked event for all the lay component
	 */
	private void addActionLay() {
		layL.forEach(lay -> {
			lay.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (lay.isClickable()) {
						if (lay.isActive()) {
							lay.switchActive();
							selectedLay = null;
						} else {
							unSelectLays();
							lay.setActive(true);
							selectedLay = lay;
							try {
								layingActionFromLay(lay);
							} catch (Exception e) {
								dialogP.setDialog(e.getMessage());
							}
						}
					}
				}
			});
		});
	}

	/**
	 * Adding a new event OnMouseClicked for all the card form the hand ; without
	 * overriding the initial event click for the hand component
	 * 
	 * @source https://docs.oracle.com/javafx/2/events/handlers.htm
	 */
	private void addActionHand() {
		this.hand.getCardL().forEach(card -> {
			card.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (hand.isClickable()) {
						try {
							layingActionFromHand(card);
						} catch (Exception e) {
							dialogP.setDialog(e.getMessage());
						}
					}
				}
			});
		});
	}

	/**
	 * Allow to end its turn Event which checks if a card has been layed if no, ask
	 * confirmation to end the game if so, draw card without asking confirmation
	 */
	private void addActionDraw() {
		this.draw.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (draw.isClickable()) {
					unSelectAll();
					if (jeu.nbCartesAJouer() > 0) {
						dialogP.setDialog(Main.d.get("PLAY_human_can_not_draw"));
					} else {
						jeu.passerTour();
						updateBottomPane();
						if (jeu.isPartieFinie()) {
							disablePlaying();
							setDialogsResult();
						} else {
							dialogP.setDialog(Main.d.get("PLAY_human_turn_begin"));
						}
					}
				}
			}
		});
	}

	public void reloadHandAndDraw() {
		updateCardL();
		panePlay.setBottom(createBottomPane());
	}

	/**
	 * Update the bottom pane, in order to rebuild the hand and add associated effet
	 * As we rebuild the bottom pane, we should add Hand AND Draw event
	 */
	private void updateBottomPane() {
		reloadHandAndDraw();
		addActionHand();
		addActionDraw();
	}

	/**
	 * Try to lay a card, and to update the dialog box according the result or
	 * exceptions of jeu.jouer() method from API, and according the end of the game
	 * 
	 * @param selectedCard a CardComponent (taken from the cardL = HandComponent)
	 */
	private void layingActionFromHand(CardComponent selectedCard) throws MissHandCardException {
		System.out.println("Je passe layingActionFromHand");
		if (this.selectedLay != null) {
			this.dialogP.clearDialog();
			try {
				this.jeu.jouer(this.selectedLay.getIndex(), selectedCard.getCardAPI(), this.joueur);
			} catch (Exception e) {
				this.dialogP.setDialog(e.getMessage());
				unSelectAll();
				return;
			}
			this.selectedLay.setCardAPI(selectedCard.getCardAPI());
			this.hand.removeCard(selectedCard);
			unSelectAll();
			this.dialogP.addDialog(Main.d.get("PLAY_human_layed_card"));
			this.dialogP.addDialog(Main.d.get("PLAY_drawing_needed"));
			this.scoreP.setScoreT(this.jeu.score());
			if (this.jeu.isPartieFinie()) {
				disablePlaying();
				setDialogsResult();
			}
		} else {
			throw new MissHandCardException(Main.d.get("PLAY_human_choose_card_lay"));
			// throw new MissHandCardException();
		}
	}

	/**
	 * Try to lay a card, and to update the dialog box according the result or
	 * exceptions of jeu.jouer() method from API, and according the end of the game
	 * 
	 * @param selectedCard a LayComponent (taken from the layL = CenterPane)
	 */
	private void layingActionFromLay(LayComponent selectedLay) throws MissLayCardException {
		System.out.println("Je passe layingActionFromLay");
		if (this.hand.isCardSelected()) {
			this.dialogP.clearDialog();
			try {
				this.jeu.jouer(selectedLay.getIndex(), this.hand.getCardSelected().getCardAPI(), this.joueur);
			} catch (Exception e) {
				this.dialogP.setDialog(e.getMessage());
				unSelectAll();
				return;
			}
			selectedLay.setCardAPI(this.hand.getCardSelected().getCardAPI());
			this.hand.removeCard(this.hand.getCardSelected());
			unSelectAll();
			this.dialogP.addDialog(Main.d.get("PLAY_human_layed_card"));
			this.dialogP.addDialog(Main.d.get("PLAY_drawing_needed"));
			this.scoreP.setScoreT(this.jeu.score());
			if (this.jeu.isPartieFinie()) {
				disablePlaying();
				setDialogsResult();
			}
		} else {
			throw new MissLayCardException(Main.d.get("PLAY_human_choose_card_hand"));
			// throw new MissLayCardException();
		}
	}

	/**
	 * Unselect all the component from the APLayScene which could have been selected
	 */
	private void unSelectAll() {
		// selectedCard = null;
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
	 * In case of the game ends, modify the dialog box to add the result of the user
	 * against the game
	 */
	private void setDialogsResult() {
		if (this.jeu.isPartieFinie()) {
			if (this.jeu.isVictoire()) {
				this.dialogP.setDialog(Main.d.get("PLAY_human_end_good"));
			} else {
				this.dialogP.setDialog(Main.d.get("PLAY_human_end_bad"));
			}
			this.dialogP.addDialog(Main.d.get("PLAY_info_restart"));
		}
	}

	/**
	 * 
	 * @return le joueur dont c'est le tour
	 */
	public Joueur getJoueur() {
		return joueur;
	}
}
