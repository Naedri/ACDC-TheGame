package view.scene;

import java.util.ArrayList;
import java.util.Arrays;

import api.ActionIllegaleException;
import api.CoupInvalideException;
import api.Joueur;
import api.JoueurIA;
import api.Tas;
import application.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import view.component.LayComponent;

public class IAScene extends APlayScene {

	private static String name = Main.d.get("PLAY_mode_ia");
	private Timeline timeline;
	private static final Integer STARTTIME = 3;
	private Integer timeSeconds;

	public IAScene(String path) {
		super(name, new ArrayList<Joueur>(Arrays.asList(new JoueurIA())), path);
		this.disablePlaying();
		this.setDialogsStart();
	}

	/**
	 * In case of the game start, modify the dialog box to add the go-ahead
	 */
	private void setDialogsStart() {
		this.dialogP.setDialog(Main.d.get("PLAY_ia_begin"));
		timeSeconds = STARTTIME;
		timeline = new Timeline();
		timeline.setCycleCount(STARTTIME + 1);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
			if (timeSeconds > 0) {
				dialogP.addDialog(timeSeconds.toString());
			} else if (timeSeconds == 0) {
				// timeline.stop();
				dialogP.addDialog(Main.d.get("PLAY_ia_start"));
				startTurns();
			}
			--timeSeconds;
		}));
		timeline.play();
	}

	/**
	 * Start the IA turn concretely
	 */
	private void startTurns() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.5), event -> {
			boolean goodTurn = false;
			try {
				goodTurn = ((JoueurIA) super.getJoueur()).jouerTour(jeu);
			} catch (CoupInvalideException | ActionIllegaleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!goodTurn) {
				updateBoardGame();
				// setDialogTurn(); //does not work
			} else if (goodTurn || jeu.isPartieFinie()) {
				timeline.stop();
				unSelectLays();
				setDialogsResult();
			}
		}));
		this.dialogP.setDialog(Main.d.get("PLAY_ia_watch"));
		timeline.play();
	}

	/**
	 * Allow to update the card on lays showed according to the API And to set
	 * active the ones for wich a the value of the Carte has changed
	 */
	@Override
	protected void updateLays() {
		for (int i = 0; i < layL.size(); i++) {
			Tas tas = jeu.getTasById(i);
			LayComponent lay = layL.get(i);
			lay.setActive(false);
			if (tas.getDerniereCarte().getValeur() != lay.getCardAPI().getValeur()) {
				lay.setActive(true);
			}
			lay.setCardAPI(tas.getDerniereCarte());
		}
	}

	/**
	 * Does not work properly in the Timeline, update the dialog box with the given
	 * turn
	 * 
	 * @param turn
	 */
	private void setDialogTurn() {
		this.dialogP.setDialog(Main.d.get("PLAY_ia_turn"));
		this.dialogP.addDialog(String.valueOf(jeu.getTour()));
	}

	/**
	 * In case of the game ends, modify the dialog box to add the result of the user
	 * against the game
	 */
	private void setDialogsResult() {
		if (this.jeu.isPartieFinie()) {
			this.dialogP.setDialog(Main.d.get("PLAY_ia_end_turn"));
			this.dialogP.addDialog(String.valueOf(jeu.getTour()));
			if (this.jeu.isVictoire()) {
				this.dialogP.addDialog(Main.d.get("PLAY_ia_end_good"));
			} else {
				this.dialogP.addDialog(Main.d.get("PLAY_ia_end_bad"));
			}
			this.dialogP.addDialog(Main.d.get("PLAY_info_restart"));
		}
	}

}
