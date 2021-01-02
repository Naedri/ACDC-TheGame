package view.scene;

import java.util.List;

import api.Joueur;
import application.Main;
import javafx.scene.Node;

public class IAScene extends APlayScene {
	private static String name = Main.d.get("PLAY_mode_ia");

	public IAScene(List<Joueur> joueurs, String path) {
		super(name, joueurs, path);
	}

	@Override
	protected Node createRightPane() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Node createBottomPane() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Node createCenterPane() {
		// TODO Auto-generated method stub
		return null;
	}
}
