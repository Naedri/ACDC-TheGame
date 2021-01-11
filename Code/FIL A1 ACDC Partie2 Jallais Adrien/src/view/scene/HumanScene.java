package view.scene;

import java.util.ArrayList;
import java.util.Arrays;

import api.Joueur;
import application.Main;

public class HumanScene extends APlayScene {
	private static String name = Main.d.get("PLAY_mode_human");

	public HumanScene(String path) {
		super(name, new ArrayList<Joueur>(Arrays.asList(new Joueur("Lorem lipsum"))), path);
	}
}
