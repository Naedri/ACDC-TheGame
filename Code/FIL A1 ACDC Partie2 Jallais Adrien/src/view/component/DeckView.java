package view.component;

import application.Main;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Rectangle;
import view.constant.BordW;
import view.constant.Col;
import view.constant.Rad;

public class DeckView extends Button {

	ICardView card;

	public DeckView(ICardView card) {
		super(Main.d.get("PLAY_draw"));
		this.card = card;
		setStyle();
	}

	public DeckView(ICardView card, String text, Node graphic) {
		super(text, graphic);
		setStyle();
	}

	public DeckView(ICardView card, String text) {
		super(text);
		setStyle();
	}

	private void setStyle() {
		this.setPrefSize(card.getPrefWidth(), card.getPrefHeight());
		this.setFont(card.getFont());
		this.setTextFill(card.getBackgroundColor());
		// this.setBorder(new Border(card.getBorderStrokes()));
		this.setBorder(new Border(new BorderStroke(card.getBackgroundColor(), BorderStrokeStyle.SOLID,
				new CornerRadii(Rad.MEDIUM.getRadius()), new BorderWidths(BordW.HIGH.getWidth()))));
		// this.setBackground(card.getBackground());
		this.setBackground(new Background(
				new BackgroundFill(card.getBorderColor(), new CornerRadii(Rad.MEDIUM.getRadius()), Insets.EMPTY)));

	}

	public Rectangle makeDeckSupport() {
		Rectangle rect = new Rectangle(this.getPrefWidth() * 1.2, this.getPrefHeight() * 1.2, Col.BLACK.getColor());
		rect.setArcHeight(10);
		rect.setArcWidth(100);
		return rect;
	}

	public Rectangle makeCardSupportTrans() {
		Rectangle rect = this.makeDeckSupport();
		rect.setOpacity(rect.getOpacity() * 0.5);
		return rect;
	}

}
