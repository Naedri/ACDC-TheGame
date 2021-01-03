/**
 * 
 */
package view.scene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import api.ActionIllegaleException;
import api.Carte;
import api.CoupInvalideException;
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
import javafx.scene.paint.Color;
import view.button.ButtonQuit;
import view.component.CardComponent;
import view.component.DialogComponent;
import view.component.DrawComponent;
import view.component.HandComponent;
import view.component.LayComponent;
import view.component.ScoreComponent;
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
	protected BorderPane pane;
	protected List<CardComponent> cardL;
	protected List<LayComponent> layDscL;
	protected List<LayComponent> layAscL;
	protected List<LayComponent> layL;
	protected DrawComponent draw;
	protected HandComponent hand;
	protected ScoreComponent scoreP;
	protected DialogComponent dialogP;

	protected LayComponent selectedLay;
	protected CardComponent selectedCard;
	protected Joueur joueur;
	protected Jeu jeu;

	private int drawClick = 0;

	/*
	 * protected Pane leftP; protected Pane topP; protected Pane rightP; protected
	 * Pane botP;
	 */

	public APlayScene(String modeName, List<Joueur> joueurs, String pathDeck) {
		super(new BorderPane());
		initGame(joueurs, pathDeck);
		pane = (BorderPane) (super.getPane());
		pane.setTop(createTopPane(modeName));
		pane.setLeft(createLeftPane());
		pane.setCenter(createCenterPane());
		pane.setBottom(createBottomPane());
		pane.setRight(createRightPane());
		// adding effect
		this.addActionLay();
		this.addActionHand();
		this.addActionDraw();
		// BorderPane.setAlignment(pane.getBottom(), Pos.CENTER);
	}

	protected void initGame(List<Joueur> joueurs, String pathDeck) {
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

	protected void initLayPile() {
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
	 * interaction with API to draw cards
	 */
	protected void initCardGame() {
		cardL = new ArrayList<CardComponent>();
		joueur.piocher(jeu);
		joueur.getMain().forEach(carte -> {
			cardL.add(new CardComponent(carte));
		});
	}

	protected Node createTopPane(String modeName) {
		Label label = new MainLabel(modeName);
		StackPane pane = new StackPane();
		pane.setPadding(new Insets(InsetsApp.HIGH.getTop() - this.getBorder().getInsets().getBottom(),
				InsetsApp.HIGH.getRight(), InsetsApp.HIGH.getBot(), InsetsApp.HIGH.getLeft()));
		pane.getChildren().add(label);
		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	};

	protected Node createLeftPane() {
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

		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	protected Node createRightPane() {
		VBox pane = new VBox();
		pane.getChildren().addAll(scoreP, dialogP);
		pane.setSpacing(Spacing.HIGH.getSpace());
		pane.setAlignment(Pos.CENTER);
		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	protected Node createBottomPane() {
		Insets insets = InsetsApp.MEDIUM.getInsets();
		hand = new HandComponent(cardL, cardL.get(0).getPrefWidth() * 0.2);
		hand.setPadding(insets);
		hand.setAlignment(Pos.CENTER_LEFT);

		draw = new DrawComponent(cardL.get(0));
		StackPane handStack = draw.makeSupported();
		handStack.setPadding(insets);

		HBox pane = new HBox(cardL.get(0).getPrefWidth() * 2);
		pane.getChildren().addAll(handStack, hand);
		pane.setAlignment(Pos.CENTER);

		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	protected Node createCenterPane() {
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
		pane.getChildren().addAll(descP, img, ascP);
		pane.setSpacing(Spacing.HIGH.getSpace());
		pane.setAlignment(Pos.CENTER);
		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	protected void addActionLay() {
		layL.forEach(lay -> {
			lay.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (isClickable()) {
						if (lay.isActive()) {
							lay.switchActive();
							selectedLay = null;
						} else {
							unSelectLays();
							lay.setActive(true);
							selectedLay = lay;
							System.out.println(Main.d.get("PLAY_human_choosen_card_lay"));
							System.out.println(lay.getText());
							try {
								layingAction(lay);
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
	 * adding a new event OnMouseClicked without overriding the super class
	 * 
	 * @source https://docs.oracle.com/javafx/2/events/handlers.htm
	 */
	protected void addActionHand() {
		this.hand.getCardL().forEach(card -> {
			card.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println(Main.d.get("PLAY_human_choosen_card_hand"));
					System.out.println(card.getText());
					try {
						layingAction(card);
					} catch (Exception e) {
						dialogP.setDialog(e.getMessage());
					}
				}
			});
		});
	}

	/**
	 * Allow to end its turn Event which checks if a card has been layed if no, ask
	 * confirmation to end the game if so, draw card without asking confirmation
	 */
	protected void addActionDraw() {
		this.draw.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				++drawClick;
				unSelectLays();
				hand.unSelectCard();
				if (jeu.nbCartesAJouer() > 0 && drawClick < 1) {
					dialogP.setDialog(Main.d.get("PLAY_human_turn_end_bad"));
				} else {
					jeu.passerTour();
					cardL = new ArrayList<CardComponent>();
					joueur.getMain().forEach(carte -> {
						cardL.add(new CardComponent(carte));
					});
					if (!jeu.isPartieFinie()) {
						if (jeu.isVictoire()) {
							dialogP.setDialog(Main.d.get("PLAY_human_end_good"));
							dialogP.addDialog(Main.d.get("PLAY_info_restart"));
						} else {
							dialogP.setDialog(Main.d.get("PLAY_human_end_bad"));
							dialogP.addDialog(Main.d.get("PLAY_info_restart"));
						}
					} else {
						dialogP.setDialog(Main.d.get("PLAY_human_turn_begin"));
					}
				}
			}
		});
	}

	protected void layingAction(CardComponent selectedCard) throws MissHandCardException, MissLayCardException {
		if (selectedCard instanceof CardComponent) {
			if (selectedLay != null) {
				dialogP.clearDialog();
				try {
					jeu.jouer(selectedLay.getIndex(), selectedCard.getCardAPI(), joueur);
				} catch (Exception e) {
					dialogP.setDialog(e.getMessage());
					unSelectAll();
					return;
				}
				selectedLay.setCardAPI(selectedCard.getCardAPI());
				hand.removeCard(selectedCard);
				unSelectAll();
				dialogP.addDialog(Main.d.get("PLAY_human_layed_card"));
				dialogP.addDialog(Main.d.get("PLAY_drawing_needed"));
				this.scoreP.setScoreT(jeu.score());
			} else {
				throw new MissHandCardException(Main.d.get("PLAY_human_choose_card_lay"));
			}
		}
	}

	protected void layingAction(LayComponent selectedCard) throws MissHandCardException, MissLayCardException {
		if (selectedCard instanceof LayComponent) {
			LayComponent lay = (LayComponent) selectedCard;
			if (this.hand.isCardSelected()) {
				dialogP.clearDialog();
				try {
					jeu.jouer(lay.getIndex(), this.hand.getCardSelected().getCardAPI(), joueur);
				} catch (Exception e) {
					dialogP.setDialog(e.getMessage());
					unSelectAll();
					return;
				}
				lay.setCardAPI(selectedLay.getCardAPI());
				hand.removeCard(this.hand.getCardSelected());
				unSelectAll();
				dialogP.addDialog(Main.d.get("PLAY_human_layed_card"));
				dialogP.addDialog(Main.d.get("PLAY_drawing_needed"));
				this.scoreP.setScoreT(jeu.score());
			} else {
				throw new MissLayCardException(Main.d.get("PLAY_human_choose_card_hand"));
			}
		}
	}

	private void unSelectLays() {
		this.layL.forEach(card -> {
			card.setActive(false);
		});
	}

	/**
	 * @param selectedLay the selectedLay to set
	 */
	private void setSelectedLay(LayComponent selectedLay) {
		this.selectedLay = selectedLay;
	}

	/**
	 * @param selectedCard the selectedCard to set
	 */
	private void setSelectedCard(CardComponent selectedCard) {
		this.selectedCard = selectedCard;
	}

	private void unSelectAll() {
		setSelectedLay(null);
		setSelectedCard(null);
		hand.setCardSelected(null);
		unSelectLays();
		hand.unSelectCard();
	}

	/**
	 * Retrouve une carte à partir de sa valeur pour la jouer sur un tas spécifique
	 * 
	 * @param tasId  Numero du tas (visible via l'affichage)
	 * @param carte  Valeur de la carte à jouer depuis la main du joueur
	 *               indiqué(doit faire partie du jeu)
	 * @param joueur Joueur effectuant l'action
	 * @throws ActionIllegaleException, CoupInvalideException
	 */
	protected void jouer(int tasId, int carte, Joueur joueur)
			throws ActionIllegaleException, ActionIllegaleException, CoupInvalideException {
		Carte carteJouee = null;
		for (Carte c : joueur.getMain()) {
			if (c.getValeur() == carte) {
				carteJouee = c;
				break;
			}
		}
		if (carteJouee == null) {
			throw new ActionIllegaleException(Main.d.get("PLAY_human_card_not_found"));
		}
		this.jeu.jouer(tasId, carteJouee, joueur);
	}

}
