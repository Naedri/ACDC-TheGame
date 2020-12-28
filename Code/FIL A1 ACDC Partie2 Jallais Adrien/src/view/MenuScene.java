package view;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.button.Button_Load_Scene;

public class MenuScene extends MainScene {
	private VBox vbox;

	public MenuScene() throws Exception {
		super(new VBox());
		vbox = (VBox) (super.getPane());
		createMenuBox();

		// TODO Erase
		vbox.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
	}

	/*
	 * 
	 */
	private void createMenuBox() {
		vbox.setSpacing(8);
		vbox.setMaxWidth(400);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(new Button_Load_Scene(Main.d.get("MENU_button_playHuman")),
				new Button_Load_Scene(Main.d.get("MENU_button_playIA")),
				new Button_Load_Scene(Main.d.get("MENU_button_parameters")),
				new Button_Load_Scene(Main.d.get("MENU_button_rules")),
				new Button_Load_Scene(Main.d.get("MENU_button_authors")));
	}

}
