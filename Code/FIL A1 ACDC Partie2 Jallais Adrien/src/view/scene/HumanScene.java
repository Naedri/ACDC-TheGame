package view.scene;

import java.util.List;

import api.Joueur;
import application.Main;

public class HumanScene extends APlayScene {
	private static String name = Main.d.get("PLAY_mode_human");

	public HumanScene(List<Joueur> joueurs, String path) {
		super(name, joueurs, path);
		/*
		 * setActionCard(); setActionDrawPile(); setActionLayPile();
		 */
	}

	private void setActionCard() {
		// TODO Auto-generated method stub
	}

	private void setActionDrawPile() {
		// TODO Auto-generated method stub

	}

	private void setActionLayPile() {
		// TODO Auto-generated method stub

	}
}
