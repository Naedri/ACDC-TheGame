package view.scene;

import application.Main;
import controller.Services;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import view.button.ButtonQuit;
import view.component.DialogComponent;
import view.constant.FontApp;
import view.constant.InsetsApp;
import view.constant.Spacing;

public class AuthorScene extends MainScene {

	protected BorderPane pane;
	private DialogComponent mainLabel;
	private DialogComponent labelAuthors;

	public AuthorScene() {
		super(new BorderPane());
		pane = (BorderPane) (super.getPane());
		initMainLabel();
		pane.setCenter(createCenterPane());
		pane.setLeft(createLeftPane());
	}

	private void initMainLabel() {
		labelAuthors = new DialogComponent(Main.d.get("CREDITS_Authors"));
		labelAuthors.getDialogL().setFont(FontApp.HIGH.getFont());
		labelAuthors.getDialogL().setTextAlignment(TextAlignment.LEFT);

		mainLabel = new DialogComponent(Main.d.get("CREDITS_Author_front"));
		mainLabel.addDialog("- Adrien Jallais");
		mainLabel.addDialog(Main.d.get("CREDITS_Author_back"));
		mainLabel.addDialog("- Nicolas Kirchhoffer");
		mainLabel.addDialog(Main.d.get("CREDITS_Image_welcome"));
		mainLabel.addDialog("- Momentmal - pixabay.com");
		mainLabel.addDialog(Main.d.get("CREDITS_Image_play"));
		mainLabel.addDialog("- Castle Transparent - pngall.com");

		mainLabel.getDialogL().setTextAlignment(TextAlignment.LEFT);
		mainLabel.getDialogL().setMaxWidth(Double.MAX_VALUE);
	}

	public Node createCenterPane() {
		VBox vbox = new VBox();
		// TODO change raw value
		vbox.setMaxSize(350, 400);
		vbox.getChildren().addAll(labelAuthors, mainLabel);
		vbox.setAlignment(Pos.CENTER_LEFT);
		vbox.setBackground(mainLabel.getBackground());
		vbox.setPadding(InsetsApp.HIGH.getInsets());
		return vbox;
	}

	/**
	 * 
	 * @return VBox containing the exit and menu buttons
	 */
	public Node createLeftPane() {
		VBox pane = new VBox();
		Button bM = new ButtonQuit(Main.d.get("COMMON_menu"));
		bM.setOnAction((ActionEvent e) -> {
			Services.changeScene(this, new MenuScene());
		});
		Button bQ = new ButtonQuit(Main.d.get("COMMON_exit"));
		bQ.setOnAction((ActionEvent e) -> {
			Services.quitApp(this);
		});
		pane.getChildren().addAll(bM, bQ);
		pane.setSpacing(Spacing.HIGH.getSpace());
		pane.setAlignment(Pos.CENTER);
		return pane;
	}
}
