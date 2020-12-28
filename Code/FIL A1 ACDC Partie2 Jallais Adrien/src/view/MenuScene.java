package view;

import application.Main;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import view.button.Button_Load_Scene;

public class MenuScene extends MainScene {
	private VBox vbox;

	public MenuScene() throws Exception {
		super(new VBox());
		vbox = (VBox) (super.getPane());
		createMenuBox();
	}

	/*
	 * public final Parent getRoot() { return root == null ? null : root.get(); }
	 */

	public void createMenuBox() {
		// vbox.setPadding(new Insets(0, 20, 10, 20));
		// vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		// vbox.setPrefWidth(10000);
		vbox.setMaxWidth(400);
		vbox.setBackground(new Background(new BackgroundFill(Col.BADD, CornerRadii.EMPTY, Insets.EMPTY)));
		Main.d.get("STAGE_title");

		vbox.getChildren().addAll(new Button_Load_Scene(Main.d.get("MENU_button_playHuman")),
				new Button_Load_Scene(Main.d.get("MENU_button_playIA")),
				new Button_Load_Scene(Main.d.get("MENU_button_parameters")),
				new Button_Load_Scene(Main.d.get("MENU_button_rules")),
				new Button_Load_Scene(Main.d.get("MENU_button_authors")));
	}

}
