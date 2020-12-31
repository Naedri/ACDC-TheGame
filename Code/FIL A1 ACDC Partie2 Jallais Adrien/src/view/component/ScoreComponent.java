package view.component;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import view.constant.Col;
import view.constant.InsetsApp;
import view.constant.Rad;
import view.constant.Spacing;
import view.label.MainLabel;

public class ScoreComponent extends VBox {
	private MainLabel scoreT;
	private MainLabel scoreInfo;
	private Background backg;

	public ScoreComponent() {
		init();
		this.setSpacing(Spacing.LITTLE.getSpace());
	}

	public ScoreComponent(double spacing) {
		super(spacing);
		init();
	}

	private void init() {
		// TODO Check value with API
		scoreT = new MainLabel("99");
		scoreInfo = new MainLabel(Main.d.get("PLAY_score"));
		this.getChildren().addAll(scoreT, scoreInfo);
		this.setAlignment(Pos.CENTER);
		this.setPadding(InsetsApp.HIGH.getInsets());
		backg = new Background(
				new BackgroundFill(Col.INFOL.getColor(), new CornerRadii(Rad.MEDIUM.getRadius()), Insets.EMPTY));
		this.setBackground(backg);
	}

	/**
	 * @param score the scoreT to set
	 */
	public void setScoreT(int score) {
		this.scoreT.setText(Integer.toString(score));
	}
}
