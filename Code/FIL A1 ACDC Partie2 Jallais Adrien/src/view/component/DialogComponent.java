package view.component;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import view.constant.ColorApp;
import view.constant.FontApp;
import view.constant.RadiusApp;
import view.label.MainLabel;

public class DialogComponent extends StackPane {
	private Background backg;
	private MainLabel dialogL;

	public DialogComponent(String text) {
		init(text);
	}

	public DialogComponent(String text, Node... children) {
		super(children);
		init(text);
	}

	private void init(String text) {
		dialogL = new MainLabel(text);
		// TODO change raw value
		dialogL.setPrefSize(180, 180);
		dialogL.setFont(FontApp.MEDIUM.getFont());
		dialogL.setTextAlignment(TextAlignment.RIGHT);
		dialogL.setWrapText(true);
		this.getChildren().add(dialogL);

		backg = new Background(new BackgroundFill(ColorApp.INFOL.getColor(),
				new CornerRadii(RadiusApp.MEDIUM.getRadius()), Insets.EMPTY));
		this.setBackground(backg);
	}

	/**
	 * @return the dialogL
	 */
	public MainLabel getDialogL() {
		return dialogL;
	}

	/**
	 * Define the text in the dialog box
	 * 
	 * @param text
	 */
	public void setDialog(String text) {
		this.dialogL.setText(text);
	}

	/**
	 * Adding new text to the dialog box at the end
	 */
	public void addDialog(String text) {
		this.dialogL.setText(dialogL.getText() + System.lineSeparator() + text);
	}

	/**
	 * ShortCut to clear the dialog box
	 */
	public void clearDialog() {
		this.dialogL.setText("");
	}

}
