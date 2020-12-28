package view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class MenuScene extends Scene {

	public MenuScene() throws Exception {
		super(new VBox());
		VBox root = (VBox) (this.getRoot());
	}
}
