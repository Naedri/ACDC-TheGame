package view.scene;

import java.util.List;

import api.Joueur;
import application.Main;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.component.CardComponent;
import view.component.LayComponent;
import view.exception.MissHandCardException;
import view.exception.MissLayCardException;

public class HumanScene extends APlayScene {
	private static String name = Main.d.get("PLAY_mode_human");

	public HumanScene(List<Joueur> joueurs, String path) {
		super(name, joueurs, path);
		// adding effect
		this.addActionLay();
		this.addActionHand();
		this.addActionDraw();
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

	/**
	 * Update the bottom pane, in order to rebuild the hand and add associated effet
	 * As we rebuild the bottom pane, we should add Hand AND Draw event
	 */
	private void updateBottomPane() {
		super.updateHand();
		this.addActionHand();
		this.addActionDraw();
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
}
