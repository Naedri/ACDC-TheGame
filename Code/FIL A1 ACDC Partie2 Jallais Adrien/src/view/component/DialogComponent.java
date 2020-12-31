package view.component;

import application.Main;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import view.constant.Col;
import view.constant.FontApp;
import view.constant.Rad;
import view.label.MainLabel;

public class DialogComponent extends StackPane {
	private Background backg;
	private String dialog;
	private MainLabel dialogL;

	public DialogComponent() {
		init();
	}

	public DialogComponent(Node... children) {
		super(children);
		init();
	}

	private void init() {

//		Rectangle rect = new Rectangle(90, 90, Col.INFOL.getColor());
//		this.getChildren().add(rect);

		// TODO Check value with API
		dialog = Main.d.get("PLAY_dialog_init");
		dialogL = new MainLabel(dialog);
		dialogL.setPrefSize(180, 180);

		dialogL.setFont(FontApp.MEDIUM.getFont());
		dialogL.setWrapText(true);
		this.getChildren().add(dialogL);

		backg = new Background(
				new BackgroundFill(Col.INFOL.getColor(), new CornerRadii(Rad.MEDIUM.getRadius()), Insets.EMPTY));
		this.setBackground(backg);
	}

	/**
	 * @param score the scoreT to set
	 */
	public void setDialog(String text) {
		this.dialog = text;
	}

}
