package view.component;

import application.Main;
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
import javafx.scene.text.TextAlignment;
import view.constant.BordW;
import view.constant.ColorApp;
import view.constant.RadiusApp;

public class DrawComponent extends ACardComponent {

	private ICardView card;

	public DrawComponent(ICardView card) {
		super(Main.d.get("PLAY_draw"));
		init(card);
	}

	/**
	 * To gather steps done at the instanciation of the class in a same function
	 * 
	 * @param card
	 */
	private void init(ICardView card) {
		this.card = card;
		initBackgroundAfterSuper();
		setStyle();
	}

	@Override
	protected void initBackground() {
		// do nothing cause we need this.card = card; done first
	}

	/**
	 * with the I
	 */
	private void initBackgroundAfterSuper() {
		Background backgroundInit = new Background(
				new BackgroundFill(card.getBorderColor(), new CornerRadii(RadiusApp.MEDIUM.getRadius()), Insets.EMPTY));
		Background backgroundHover = new Background(new BackgroundFill(ColorApp.BADL.getColor(),
				new CornerRadii(RadiusApp.MEDIUM.getRadius()), Insets.EMPTY));
		setBackgroundInit(backgroundInit);
		setBackgroundHover(backgroundHover);
		setBackground(backgroundInit);
	}

	private void setStyle() {
		this.setPrefSize(card.getPrefWidth(), card.getPrefHeight());
		this.setFont(card.getFont());
		this.setWrapText(true);
		this.setTextAlignment(TextAlignment.CENTER);
		// this.setRotate(270);
		this.setTextFill(getBorderColor());
		// this.setBorder(new Border(card.getBorderStrokes()));
		this.setBorder(new Border(new BorderStroke(this.getBorderColor(), BorderStrokeStyle.SOLID,
				new CornerRadii(RadiusApp.MEDIUM.getRadius()), new BorderWidths(BordW.HIGH.getWidth()))));
		this.setWrapText(true);
	}

	@Override
	public StackPane makeSupported() {
		StackPane sp = new StackPane();
		sp.getChildren().addAll(this.makeSupport(), this);
		return sp;
	}

	@Override
	public Rectangle makeSupport() {
		Rectangle rect = new Rectangle(this.getPrefWidth() * 1.2, this.getPrefHeight() * 1.2,
				ColorApp.BLACK.getColor());
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
	public Color getBackgroundColor() {
		return card.getBackgroundColor();
	}

	@Override
	/**
	 * the border and the background should be the same for the hand
	 */
	public Color getBorderColor() {
		return card.getBackgroundColor();
	}

	@Override
	public BorderStroke getBorderStrokes() {
		return this.getBorder().getStrokes().get(0);
	}
}
