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

	public ScoreComponent(int score) {
		super(Spacing.LITTLE.getSpace());
		init(score);
	}

	public ScoreComponent(double spacing, int score) {
		super(spacing);
		init(score);
	}

	private void init(int score) {
		scoreT = new MainLabel(Integer.toString(score));
		setScoreInfo(score);
		this.getChildren().addAll(scoreT, scoreInfo);
		this.setAlignment(Pos.CENTER);
		this.setPadding(InsetsApp.HIGH.getInsets());
		backg = new Background(new BackgroundFill(ColorApp.INFOL.getColor(),
				new CornerRadii(RadiusApp.MEDIUM.getRadius()), Insets.EMPTY));
		this.setBackground(backg);
	}

	/**
	 * To update the score dialogs and the score number
	 * 
	 * @param score the scoreT to set
	 */
	public void setScoreT(int score) {
		this.scoreT.setText(Integer.toString(score));
		this.setScoreInfo(score);
	}

	/**
	 * To update the score dialogs
	 * 
	 * @param score
	 */
	private void setScoreInfo(int score) {
		if (score >= 2) {
			scoreInfo = new MainLabel(Main.d.get("PLAY_score_plural"));
		} else {
			scoreInfo = new MainLabel(Main.d.get("PLAY_score_singular"));
		}
	}
}
