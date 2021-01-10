package view.scene;

import java.util.ArrayList;
import java.util.Arrays;

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
	private int cardUpdated = 0;

	public IAScene(String path) {
		super(name, new ArrayList<Joueur>(Arrays.asList(new JoueurIA())), path);
		this.disablePlaying();
		this.disableHoverLays();
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
	 * 
	 * @param time number of second for duration of the Turn
	 * @return keyframe which allows to the IA to play
	 */
	private KeyFrame makeKeyFrameIATurn(double time) {
		return new KeyFrame(Duration.seconds(time), event -> {
			boolean goodTurn = true;
			try {
				goodTurn = ((JoueurIA) super.getJoueur()).jouerTour(this.jeu);
			} catch (CoupInvalideException | ActionIllegaleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!goodTurn && !this.jeu.isPartieFinie()) {
				reloadHandAndDraw();
				this.scoreP.setScoreT(this.jeu.score());
				this.cardUpdated = updateLays();
				this.updateDialogTurn();
			} else {
				reloadHandAndDraw();
				this.scoreP.setScoreT(this.jeu.score());
				this.updateLays();
				this.unSelectLays();
				this.setDialogsResult();
				this.timeline.stop();
			}
		});
	}

	/**
	 * @return the timeTurn
	 */
	public double getTimeTurn() {
		return timeTurnSec;
	}

	/**
	 * @param timeTurn the timeTurn to set
	 */
	public void setTimeTurn(double timeTurn) {
		this.timeTurnSec = timeTurn;
	}

	/**
	 * Allow to update the card on lays showed according to the API And to set
	 * active the ones for wich a the value of the Carte has changed
	 * 
	 * @return number of card updated
	 */
	public int updateLays() {
		int cardUpdated = 0;
		for (int i = 0; i < layL.size(); i++) {
			Tas tas = this.jeu.getTasById(i);
			LayComponent lay = layL.get(i);
			lay.setActive(false);
			if (tas.getDerniereCarte().getValeur() != lay.getCardAPI().getValeur()) {
				++cardUpdated;
				lay.setActive(true);
			}
			lay.setCardAPI(tas.getDerniereCarte());
		}
		return cardUpdated;

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
	public void updateDialogTurn() {
		this.dialogP.setDialog(Main.d.get("PLAY_ia_turn"));
		this.dialogP.addDialog(String.valueOf(this.jeu.getTour() + 1)); // as turn begin at 0
		// can not be used as the IA lays and ends turn as well
//		if (this.jeu.getNbCartesTour() > 0) {
//			this.dialogP.addDialog(Main.d.get("PLAY_ia_layed_card"));
//			this.dialogP.addDialog(String.valueOf(this.jeu.getNbCartesTour()));
//		}
		if (this.cardUpdated > 0) {
			this.dialogP.addDialog(Main.d.get("PLAY_ia_layed_card"));
			this.dialogP.addDialog(String.valueOf(this.cardUpdated));
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
	public void killTimeline() {
		this.timeline.stop();
	}

	/**
	 * To kill whenever and whereever I want the timeline
	 */
	public boolean isTimelineNull() {
		return timeline == null;
	}

	/**
	 * 
	 * @return VBox containing the exit and menu buttons
	 */
	@Override
	protected Node createLeftPane() {
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
	public VBox makeSlider() {
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
					unSelectLays();
					updateLays();
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
