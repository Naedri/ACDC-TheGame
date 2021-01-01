/**
 * 
 */
package view.scene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.button.ButtonQuit;
import view.component.CardComponent;
import view.component.DialogComponent;
import view.component.DrawComponent;
import view.component.HandComponent;
import view.component.LayComponent;
import view.component.ScoreComponent;
import view.constant.ColorApp;
import view.constant.InsetsApp;
import view.constant.Spacing;
import view.label.MainLabel;

/**
 * @author Adrien Jallais
 *
 */
public abstract class APlayScene extends MainScene {
	protected BorderPane pane;
	protected List<CardComponent> cardL;
	protected List<LayComponent> layAscL;
	protected List<LayComponent> layDscL;
	protected DrawComponent draw;

	protected int score;
	protected String dialog;

	/*
	 * protected Pane leftP; protected Pane topP; protected Pane rightP; protected
	 * Pane botP;
	 */

	public APlayScene(String modeName) {
		super(new BorderPane());
		initGame();
		pane = (BorderPane) (super.getPane());
		pane.setTop(createTopPane(modeName));
		pane.setLeft(createLeftPane());
		pane.setBottom(createBottomPane());
		pane.setRight(createRightPane());
		pane.setCenter(createCenterPane());
		// BorderPane.setAlignment(pane.getBottom(), Pos.CENTER);
	}

	protected void initGame() {
		// TODO add api function
		this.score = 99;
		this.dialog = Main.d.get("Play_dialog_init");
		initCardGame();
		initLayPile();
	}

	protected void initLayPile() {
		layAscL = new ArrayList<LayComponent>();
		layDscL = new ArrayList<LayComponent>();
		// TODO change mock data 2 should be given by API
		for (int i = 0; i < 2; i++) {
			layAscL.add(new LayComponent(1, ColorApp.BADD, true));
			layDscL.add(new LayComponent(100, ColorApp.BADL, false));
		}
	}

	/**
	 * interaction with API to draw cards
	 */
	protected void initCardGame() {
		cardL = new ArrayList<CardComponent>();
		// TODO change mock data
		for (int i = 0; i < 8; i++) {
			cardL.add(new CardComponent(99 + i));
		}
	}

	protected Node createTopPane(String modeName) {
		Label label = new MainLabel(modeName);
		StackPane pane = new StackPane();
		pane.setPadding(new Insets(InsetsApp.HIGH.getTop() - this.getBorder().getInsets().getBottom(),
				InsetsApp.HIGH.getRight(), InsetsApp.HIGH.getBot(), InsetsApp.HIGH.getLeft()));

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
		pane.setSpacing(Spacing.HIGH.getSpace());
		pane.setAlignment(Pos.CENTER);

		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	protected Node createRightPane() {
		// score
		ScoreComponent scoreP = new ScoreComponent();
		// dialog
		DialogComponent dialogP = new DialogComponent();
		// merge
		VBox pane = new VBox();
		pane.getChildren().addAll(scoreP, dialogP);
		pane.setSpacing(Spacing.HIGH.getSpace());
		pane.setAlignment(Pos.CENTER);
		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	protected Node createBottomPane() {
		Insets insets = InsetsApp.MEDIUM.getInsets();

		// TODO Raw Value 0.2
		HBox hand = new HandComponent(cardL, cardL.get(0).getPrefWidth() * 0.2);
		hand.setPadding(insets);
		hand.setAlignment(Pos.CENTER_LEFT);

		draw = new DrawComponent(cardL.get(0));
		StackPane handStack = draw.makeDeckSupported();
		handStack.setPadding(insets);

		HBox pane = new HBox(cardL.get(0).getPrefWidth() * 2);
		pane.getChildren().addAll(handStack, hand);
		pane.setAlignment(Pos.CENTER);

		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	protected Node createCenterPane() {
		Insets insets = InsetsApp.HIGH.getInsets();
		// descending
		HBox descP = new HBox(Spacing.HIGH.getSpace());
		layDscL.forEach(lay -> {
			StackPane layStack = lay.makeLaySupported();
			layStack.setPadding(insets);
			descP.getChildren().add(layStack);
		});
		descP.setAlignment(Pos.CENTER);
		// ascending
		HBox ascP = new HBox(Spacing.HIGH.getSpace());
		layAscL.forEach(lay -> {
			StackPane layStack = lay.makeLaySupported();
			layStack.setPadding(insets);
			ascP.getChildren().add(layStack);
		});
		ascP.setAlignment(Pos.CENTER);

		// img
		String pathPict = "src" + File.separator + "multimedia" + File.separator + "Castle_Transparent.png";
		ImageView img;
		try {
			img = new ImageView(new Image(new FileInputStream(pathPict)));
		} catch (FileNotFoundException e) {
			img = null;
			e.printStackTrace();
		}
		if (img != null) {
			img.setSmooth(true);
			img.setFitHeight(200);
			img.setPreserveRatio(true);
		}

		// merge
		VBox pane = new VBox();
		pane.getChildren().addAll(descP, img, ascP);
		pane.setSpacing(Spacing.HIGH.getSpace());
		pane.setAlignment(Pos.CENTER);
		// TODO Erase
		pane.setBackground(new Background(new BackgroundFill(Color.color(Math.random(), Math.random(), Math.random()),
				CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

}
