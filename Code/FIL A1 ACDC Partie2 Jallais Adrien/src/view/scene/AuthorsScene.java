package view.scene;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import view.component.DialogComponent;
import view.constant.FontApp;
import view.constant.Spacing;

public class AuthorsScene extends MainScene {

	private VBox vbox;
	private DialogComponent mainLabel;

	private DialogComponent labelAuthors;
	private DialogComponent labelAuthorBack;
	private DialogComponent labelAuthorFront;
	private DialogComponent labelImageWelcome;
	private DialogComponent labelImagePlay;

	public AuthorsScene() {
		super(new VBox(1));
		vbox = (VBox) (this.getPane());
		initLabel();
		vbox.getChildren().addAll(labelAuthors, labelAuthorBack, labelAuthorFront, labelImageWelcome, labelImagePlay);
		vbox.setSpacing(Spacing.HIGH.getSpace());
		vbox.setAlignment(Pos.CENTER_LEFT);
		vbox.setMaxSize(350, 600);
		vbox.setBackground(labelAuthors.getBackground());
	}

	private void initLabel() {
		labelAuthors = new DialogComponent(Main.d.get("CREDITS_Authors"));
		labelAuthors.getDialogL().setFont(FontApp.HIGH.getFont());
		labelAuthorBack = new DialogComponent(Main.d.get("CREDITS_Author_back"));
		labelAuthorFront = new DialogComponent(Main.d.get("CREDITS_Author_front"));
		labelImageWelcome = new DialogComponent(Main.d.get("CREDITS_Image_welcome"));
		labelImagePlay = new DialogComponent(Main.d.get("CREDITS_Image_play"));

		getDialog().forEach(dialog -> {
			dialog.getDialogL().setTextAlignment(TextAlignment.LEFT);
			// dialog.getDialogL().setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
			dialog.getDialogL().setMaxWidth(Double.MAX_VALUE);
		});
		labelAuthorBack.addDialog("Adrien Jallais");
		labelAuthorFront.addDialog("Nicolas Kirchhoffer");
		labelImageWelcome.addDialog("Momentmal - pixabay.com");
		labelImagePlay.addDialog("Castle Transparent - pngall.com");
	}

	private List<DialogComponent> getDialog() {
		List<DialogComponent> listDialog = new ArrayList<DialogComponent>();
		listDialog.add(labelAuthors);
		listDialog.add(labelAuthorBack);
		listDialog.add(labelAuthorFront);
		listDialog.add(labelImageWelcome);
		listDialog.add(labelImagePlay);
		return listDialog;
	}
}
