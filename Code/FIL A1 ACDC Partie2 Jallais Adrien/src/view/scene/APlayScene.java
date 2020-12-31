/**
 * 
 */
package view.scene;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import controller.Services;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.button.ButtonQuit;
import view.component.CardView;
import view.component.DeckView;
import view.component.HandView;
import view.constant.Spacing;
import view.label.MainLabel;

/**
 * @author Adrien Jallais
 *
 */
public abstract class APlayScene extends MainScene {
	protected BorderPane pane;
	protected List<CardView> cardL;

	/*
	 * protected Pane leftP; protected Pane topP; protected Pane rightP; protected
	 * Pane botP;
	 */

	public APlayScene(String modeName) {
		super(new BorderPane());
		initCardGame();
		pane = (BorderPane) (super.getPane());
		pane.setTop(createTopPane(modeName));
		pane.setLeft(createLeftPane());
		pane.setBottom(createBottomPane());
		pane.setRight(createRightPane());
		pane.setCenter(createCenterPane());
		// BorderPane.setAlignment(pane.getBottom(), Pos.CENTER);
	}

	/**
	 * interaction with API to draw cards
	 */
	protected void initCardGame() {
		cardL = new ArrayList<CardView>();
		// TODO change mock data
		for (int i = 0; i < 8; i++) {
			cardL.add(new CardView(99 + i));
		}
	}

	protected Node createTopPane(String modeName) {
		Label label = new MainLabel(modeName);
		StackPane pane = new StackPane();
		pane.setPadding(new Insets(10 - this.getBorder().getInsets().getBottom(), 30, 10, 30));
		pane.getChildren().add(label);

		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	};

	protected Node createLeftPane() {
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
		pane.setSpacing(Spacing.MEDIUM.getSpace());
		pane.setAlignment(Pos.CENTER);

		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	protected abstract Node createRightPane();

	protected Node createBottomPane() {
//		
//		  HBox root = new HandView(this.cardL); root.setAlignment(Pos.CENTER);
//		  root.setBackground(new Background(new
//		  BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
//		  CornerRadii.EMPTY, Insets.EMPTY))); return root;

		HBox hand = new HandView(cardL, cardL.get(0).getPrefWidth() * 0.2);
		hand.setPadding(new Insets(10, 30, 10, 30));
		hand.setAlignment(Pos.CENTER_LEFT);

		DeckView deck = new DeckView(cardL.get(0));

		HBox pane = new HBox();
		pane.getChildren().addAll(deck, hand);
		pane.setAlignment(Pos.CENTER);
		return pane;
		// TODO Erase

	}

	protected abstract Node createCenterPane();

}
