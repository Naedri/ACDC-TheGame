package view.scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import api.ActionIllegaleException;
import api.CoupInvalideException;
import api.Joueur;
import api.JoueurIA;
import api.Tas;
import application.Main;
import controller.Services;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import view.button.ButtonQuit;
import view.component.CardComponent;
import view.component.LayComponent;
import view.constant.Spacing;
import view.label.MainLabel;

public class IAScene extends APlayScene {

	private static String name = Main.d.get("PLAY_mode_ia");
	private Timeline timeline;
	private static final int START_TIME = 3;
	private int timeStartSec;
	private static final int TURN_TIME = 2;
	private double timeTurnSec;

	// to avoid to stop the timeline at start
	private boolean sliderModify = false;

	// this.jeu.getNbCartesTour() can not be used as the IA lays and ends turn as
	// well
	private int cardsUpdated = 0;
	private int laysUpdated = 0;

	// to show the difference between the turn played by IA
	private List<Integer> cardLOld;
	private List<Integer> cardLNew;
	private List<Integer> cardLDiff;

	public IAScene(String path) {
		super(name, new ArrayList<Joueur>(Arrays.asList(new JoueurIA())), path);
		this.disablePlaying();
		this.disableHoverLays();
		this.disableHoverHand();
		this.disableHoverDraw();
		timeStartSec = START_TIME;
		timeTurnSec = TURN_TIME;
		this.setDialogsStart();
	}

	/**
	 * In case of the game start, modify the dialog box to add the go-ahead
	 */
	private void setDialogsStart() {
		this.dialogP.setDialog(Main.d.get("PLAY_ia_begin"));
		this.timeline = new Timeline();
		this.timeline.setCycleCount(START_TIME + 1);
		this.timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
			if (timeStartSec > 0) {
				this.dialogP.addDialog(String.valueOf(timeStartSec));
			} else if (timeStartSec == 0) {
				// timeline.stop();
				this.dialogP.addDialog(Main.d.get("PLAY_ia_start"));
				startTurns();
			}
			--timeStartSec;
		}));
		this.timeline.play();
	}

	/**
	 * Start the IA turn concretely
	 */
	private void startTurns() {
		this.timeline = new Timeline();
		this.timeline.setCycleCount(Timeline.INDEFINITE);
		this.timeline.getKeyFrames().add(makeKeyFrameIATurn(getTimeTurn()));
		this.sliderModify = true;
		this.timeline.play();
	}

	/**
	 * End the IA Turns displaying dialog and laying last cards
	 */
	private void endTurns() {
		this.sliderModify = false;
		this.timeline.stop();
		this.timeline.setCycleCount(1);
		this.setTimeTurn(timeTurnSec);
		this.timeline.getKeyFrames().clear();
		this.timeline.getKeyFrames().add(makeKeyFrameIAEnd(getTimeTurn()));
		this.timeline.play();
	}

	/**
	 * 
	 * @param time number of second for duration of the Turn
	 * @return keyframe which allows to the IA to play
	 */
	private KeyFrame makeKeyFrameIATurn(double time) {
		return new KeyFrame(Duration.seconds(time), event -> {
			this.reloadHandAndDrawAndLays();
			this.initCardLDiff();
			boolean goodTurn = true;
			try {
				goodTurn = ((JoueurIA) super.getJoueur()).jouerTour(this.jeu);
			} catch (CoupInvalideException | ActionIllegaleException e) {
				e.printStackTrace();
			}
			this.updateCardLDiff();
			this.cardsUpdated = this.activeUpdatedHand();
			this.laysUpdated = this.activeUpdatedLays();
			this.scoreP.setScoreT(this.jeu.score());
			if (!goodTurn && !this.jeu.isPartieFinie()) {
				this.updateDialogTurn();
			} else {
				this.endTurns();
			}
		});
	}

	/**
	 * End the IA Turns displaying dialog and laying last cards
	 */
	private KeyFrame makeKeyFrameIAEnd(double time) {
		return new KeyFrame(Duration.seconds(time), event -> {
			this.reloadHandAndDrawAndLays();
			this.endTurns();
			this.setDialogsResult();
		});
	}

	/**
	 * setActive(false) all card from lays and hand, and update their value on the
	 * scene
	 */
	private void reloadHandAndDrawAndLays() {
		this.reloadHandAndDraw();
		this.reloadLays();
	}

	/**
	 * setActive(false) all the lays, but DO modify their value
	 */
	private void reloadLays() {
		for (int i = 0; i < this.getLayL().size(); i++) {
			Tas tas = this.jeu.getTasById(i);
			LayComponent lay = this.getLayL().get(i);
			lay.setCardAPI(tas.getDerniereCarte());
			lay.setActive(false);
		}
	}

	/**
	 * setActive(true) the lays which will have been updated by the IA during its
	 * turn, but do NOT modify their value
	 * 
	 * @return the number of lays to update
	 */
	private int activeUpdatedLays() {
		int layUpdated = 0;
		for (int i = 0; i < this.getLayL().size(); i++) {
			Tas tas = this.jeu.getTasById(i);
			LayComponent lay = this.getLayL().get(i);
			if (tas.getDerniereCarte().getValeur() != lay.getCardAPI().getValeur()) {
				++layUpdated;
				lay.setActive(true);
			} else {
				lay.setActive(false);
			}
		}
		return layUpdated;
	}

	/**
	 * to init the cardLDiff
	 */
	private void initCardLDiff() {
		System.out.println("initCardLdiff");
		this.cardLOld = new ArrayList<Integer>();
		this.getCardL().forEach(card -> {
			this.cardLOld.add(card.getCardAPI().getValeur());
		});
		this.cardLDiff = new ArrayList<Integer>(this.cardLOld);
	}

	/**
	 * to make cardLDiff containing all the card which has been removed from the
	 * hand of the IA Player, during its last turn
	 */
	private int updateCardLDiff() {
		System.out.println("updateCardLDiff");
		super.updateCardL();
		this.cardLNew = new ArrayList<Integer>();
		this.getCardL().forEach(card -> {
			this.cardLNew.add(card.getCardAPI().getValeur());
		});
		this.cardLDiff.removeAll(cardLNew);
		return this.cardLDiff.size();
	}

	/**
	 * @return the timeTurn
	 */
	private double getTimeTurn() {
		return timeTurnSec;
	}

	/**
	 * @param timeTurn the timeTurn to set
	 */
	private void setTimeTurn(double timeTurn) {
		this.timeTurnSec = timeTurn;
	}

	/**
	 * set active the card from the hand which are in the cardLDiff, showing
	 * difference between two turns of IA
	 */
	private int activeUpdatedHand() {
		System.out.println("boum");
		int cardUpdated = 0;
		CardComponent cardEval;
		Integer valueEval;
		for (int i = 0; i < this.getCardLFromHand().size(); i++) {
			cardEval = this.getCardLFromHand().get(i);
			valueEval = Integer.valueOf(cardEval.getCardAPI().getValeur());
			if (this.cardLDiff.contains(valueEval)) {
				++cardUpdated;
				cardEval.setActive(true);
			} else {
				cardEval.setActive(false);
			}
		}
		return cardUpdated;
	}

	/**
	 * to be sure to cancel all hover action of hand and draw
	 */
	@Override
	protected void reloadHandAndDraw() {
		super.reloadHandAndDraw(); // no need to setActive(false) the card form hand as it is recreated
		this.disableHoverHand();
		this.disableHoverDraw();
	}

	/**
	 * Does not work properly in the Timeline, update the dialog box with the given
	 * turn
	 * 
	 * @param cardUpdated
	 * @param turn
	 * 
	 * @param turn
	 */
	private void updateDialogTurn() {
		this.dialogP.setDialog(Main.d.get("PLAY_ia_turn"));
		this.dialogP.addDialog(String.valueOf(this.jeu.getTour() + 1)); // as turn begin at 0
		if (this.laysUpdated > 0) {
			this.dialogP.addDialog(Main.d.get("PLAY_ia_will_use_lay"));
			this.dialogP.addDialog(String.valueOf(this.laysUpdated));
		}
		if (this.cardsUpdated > 0) {
			this.dialogP.addDialog(Main.d.get("PLAY_ia_will_use_hand"));
			this.dialogP.addDialog(String.valueOf(this.cardsUpdated));
		}
	}

	/**
	 * In case of the game ends, modify the dialog box to add the result of the user
	 * against the game
	 */
	private void setDialogsResult() {
		if (this.jeu.isPartieFinie()) {
			this.dialogP.setDialog(Main.d.get("PLAY_ia_end_turn"));
			this.dialogP.addDialog(String.valueOf(this.jeu.getTour() + 1)); // as turn begin at 0
			if (this.jeu.isVictoire()) {
				this.dialogP.addDialog(Main.d.get("PLAY_ia_end_good"));
			} else {
				this.dialogP.addDialog(Main.d.get("PLAY_ia_end_bad"));
			}
			this.dialogP.addDialog(Main.d.get("PLAY_info_restart"));
		}
	}

	/**
	 * To kill whenever and whereever I want the timeline
	 */
	private void killTimeline() {
		this.timeline.stop();
	}

	/**
	 * To kill whenever and whereever I want the timeline
	 */
	private boolean isTimelineNull() {
		return timeline == null;
	}

	/**
	 * 
	 * @return VBox containing the exit and menu buttons
	 */
	@Override
	public Node createLeftPane() {
		VBox pane = new VBox();
		Button bM = new ButtonQuit(Main.d.get("COMMON_menu"));
		bM.setOnAction((ActionEvent e) -> {
			Services.changeScene(this, new MenuScene());
			if (!isTimelineNull()) {
				killTimeline();
			}
		});
		Button bQ = new ButtonQuit(Main.d.get("COMMON_exit"));
		bQ.setOnAction((ActionEvent e) -> {
			Services.quitApp(this);
			if (!isTimelineNull()) {
				killTimeline();
			}
		});
		// merge
		pane.getChildren().addAll(bM, bQ);
		pane.setSpacing(Spacing.HIGH.getSpace());
		pane.setAlignment(Pos.CENTER);
		// Slider
		VBox slb = makeSlider();
		pane.getChildren().addAll(slb);
		return pane;
	}

	/**
	 * 
	 * @return a vBox which contains
	 */
	private VBox makeSlider() {
		MainLabel sll = new MainLabel(Main.d.get("PLAY_ia_slider_label"));
		Slider sl = new Slider(0.25, 4, TURN_TIME);
		sl.setBlockIncrement(0.25);
//		Slider sl = new Slider(0.5, 4, TURN_TIME);
//		sl.setBlockIncrement(0.5);
		sl.setMajorTickUnit(1);
		sl.setShowTickLabels(true);
		sl.setShowTickMarks(true);
		sl.setSnapToTicks(true);
		sl.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v, Number av, Number ap) {
				setTimeTurn(ap.doubleValue());
				if (sliderModify) {
					timeline.stop();
					timeline.getKeyFrames().clear();
					timeline.getKeyFrames().add(makeKeyFrameIATurn(getTimeTurn()));
					reloadLays();
					reloadHandAndDraw();
					timeline.play();
				}
			}
		});
		VBox slb = new VBox();
		slb.getChildren().addAll(sl, sll);
		slb.setSpacing(Spacing.LITTLE.getSpace());
		slb.setAlignment(Pos.CENTER);
		return slb;
	}
}
