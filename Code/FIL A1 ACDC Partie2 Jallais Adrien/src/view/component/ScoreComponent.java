package view.component;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import view.constant.ColorApp;
import view.constant.InsetsApp;
import view.constant.RadiusApp;
import view.constant.Spacing;
import view.label.MainLabel;

public class ScoreComponent extends VBox {
	private MainLabel scoreT;
	private MainLabel scoreInfo;
	private Background backg;

	public ScoreComponent(int initScore) {
		init(initScore);
		this.setSpacing(Spacing.LITTLE.getSpace());
	}

	public ScoreComponent(double spacing, int initScore) {
		super(spacing);
		init(initScore);
	}

	private void init(int initScore) {
		// TODO Check value with API
		scoreT = new MainLabel(Integer.toString(initScore));
		scoreInfo = new MainLabel(Main.d.get("PLAY_score_plural"));
		this.getChildren().addAll(scoreT, scoreInfo);
		this.setAlignment(Pos.CENTER);
		this.setPadding(InsetsApp.HIGH.getInsets());
		backg = new Background(new BackgroundFill(ColorApp.INFOL.getColor(),
				new CornerRadii(RadiusApp.MEDIUM.getRadius()), Insets.EMPTY));
		this.setBackground(backg);
	}

	/**
	 * @param score the scoreT to set
	 */
	public void setScoreT(int score) {
		this.scoreT.setText(Integer.toString(score));
		this.updateScoreInfo(score);
	}

	private void updateScoreInfo(int score) {
		if (score < 2) {
			this.scoreInfo.setText(Main.d.get("PLAY_score_singular"));
		}
	}
}
