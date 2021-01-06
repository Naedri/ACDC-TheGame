package view.scene;

import java.util.List;

import api.Joueur;
import application.Main;

public class IAScene extends APlayScene {
	private static String name = Main.d.get("PLAY_mode_ia");

	public IAScene(List<Joueur> joueurs, String path) {
		super(name, joueurs, path);
	}

}
