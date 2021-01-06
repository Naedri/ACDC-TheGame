package view.scene;

import application.Main;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import view.component.DialogComponent;
import view.constant.FontApp;

public class AuthorsScene extends MainScene {

	private VBox vbox;
	private DialogComponent mainLabel;

	private DialogComponent labelAuthors;
//	private DialogComponent labelAuthorBack;
//	private DialogComponent labelAuthorFront;
//	private DialogComponent labelImageWelcome;
//	private DialogComponent labelImagePlay;

	public AuthorsScene() {
		super(new VBox(1));
		vbox = (VBox) (this.getPane());
		initMainLabel();
		labelAuthors.getDialogL().setFont(FontApp.HIGH.getFont());
		labelAuthors.getDialogL().setTextAlignment(TextAlignment.LEFT);

		// TODO change raw value
		vbox.setMaxSize(300, 350);
		vbox.getChildren().addAll(labelAuthors, mainLabel);
		vbox.setAlignment(Pos.CENTER_LEFT);
		vbox.setBackground(mainLabel.getBackground());
	}

	private void initMainLabel() {
		labelAuthors = new DialogComponent(Main.d.get("CREDITS_Authors"));
		labelAuthors.getDialogL().setFont(FontApp.HIGH.getFont());

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
}
