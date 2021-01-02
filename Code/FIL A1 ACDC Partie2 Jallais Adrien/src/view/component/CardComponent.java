package view.component;

import api.Carte;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.constant.BordW;
import view.constant.ColorApp;
import view.constant.FontApp;
import view.constant.RadiusApp;

public class CardComponent extends ACardComponent implements IOneCard {

	// TODO add an event listener to cardAPI change to update value of text
	private Border border;
	private Carte cardAPI;

	public CardComponent(Carte cardAPI) {
		super(Integer.toString(cardAPI.getValeur()));
		this.cardAPI = cardAPI;
		initBackground();
		initBorder();
		setStyle();
	}

	private void initBorder() {
		border = new Border(new BorderStroke(getBorderColor(), BorderStrokeStyle.SOLID,
				new CornerRadii(RadiusApp.MEDIUM.getRadius()), new BorderWidths(BordW.HIGH.getWidth())));
	}

	protected void initBackground() {
		Background backgroundInit = new Background(
				new BackgroundFill(getBackgroundColor(), new CornerRadii(RadiusApp.MEDIUM.getRadius()), Insets.EMPTY));
		Background backgroundHover = new Background(new BackgroundFill(ColorApp.INFOL.getColor(),
				new CornerRadii(RadiusApp.MEDIUM.getRadius()), Insets.EMPTY));
		setBackgroundInit(backgroundInit);
		setBackgroundHover(backgroundHover);
		setBackground(backgroundInit);
	}

	private void setStyle() {
		// TODO raw value
		this.setPrefSize(60, 100);
		this.setFont(FontApp.MEDIUM.getFont());
		this.setWrapText(true);
		this.setTextFill(getBorderColor());
		this.setBorder(border);
	}

	public StackPane makeSupported() {
		StackPane sp = new StackPane();
		sp.getChildren().addAll(this.makeSupport(), this);
		return sp;
	}

	@Override
	public Rectangle makeSupport() {
		Rectangle rect = new Rectangle(this.getPrefWidth() * 1.2, this.getPrefHeight() * 1.2,
				ColorApp.WHITE.getColor());
		rect.setArcHeight(10);
		rect.setArcWidth(100);
		return rect;
	}

	@Override
	public Rectangle makeSupportTrans() {
		Rectangle rect = this.makeSupport();
		rect.setOpacity(rect.getOpacity() * 0.5);
		return rect;
	}

	@Override
	public Color getBorderColor() {
		// Paint paint = this.getBorder().getStrokes().get(0).getTopStroke();
		// return new Color(paint.red, paint.green, paint.blue, 1);
		return ColorApp.GOODD.getColor();
	}

	@Override
	public Color getBackgroundColor() {
		return ColorApp.INFOD.getColor();
	}

	@Override
	public BorderStroke getBorderStrokes() {
		return this.getBorder().getStrokes().get(0);
	}

	@Override
	public Carte getCardAPI() {
		return cardAPI;
	}

	@Override
	public void setCardAPI(Carte cardAPI) {
		this.cardAPI = cardAPI;
	}
}
