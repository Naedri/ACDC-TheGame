package view.component;

import application.Main;
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

	public DialogComponent() {
		init();
	}

	public DialogComponent(Node... children) {
		super(children);
		init();
	}

	private void init() {
		dialogL = new MainLabel(Main.d.get("PLAY_drawing"));
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
	 * @param score the scoreT to set
	 */
	public void setDialog(String text) {
		this.dialogL.setText(text);
	}

}
