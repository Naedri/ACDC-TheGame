package view.component;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.constant.BordW;
import view.constant.Col;
import view.constant.FontS;
import view.constant.Rad;

public class CardView extends Button implements ICardView {

	public CardView(int value) {
		super(Integer.toString(value));
		setStyle();
		setAction();
	}

	private void setAction() {
		// mouse event
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setBackground(new Background(new BackgroundFill(Col.INFOL.getColor(),
						new CornerRadii(Rad.MEDIUM.getRadius()), Insets.EMPTY)));

			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setBackground(new Background(new BackgroundFill(Col.INFOD.getColor(),
						new CornerRadii(Rad.MEDIUM.getRadius()), Insets.EMPTY)));

			}
		});
	}

	private void setStyle() {
		// TODO raw value
		this.setPrefSize(60, 100);
		this.setFont(Font.font("Arial", FontWeight.BOLD, FontS.MEDIUM.getSize()));

		this.setTextFill(getBorderColor());
		this.setBorder(new Border(new BorderStroke(getBorderColor(), BorderStrokeStyle.SOLID,
				new CornerRadii(Rad.MEDIUM.getRadius()), new BorderWidths(BordW.HIGH.getWidth()))));

		// BACKGROUNG
		this.setBackground(new Background(
				new BackgroundFill(this.getBackgroundColor(), new CornerRadii(Rad.MEDIUM.getRadius()), Insets.EMPTY)));
	}

	public Rectangle makeCardSupport() {
		Rectangle rect = new Rectangle(this.getPrefWidth() * 1.2, this.getPrefHeight() * 1.2, Col.WHITE.getColor());
		rect.setArcHeight(10);
		rect.setArcWidth(100);
		return rect;
	}

	/**
	 * a card support with less opacity
	 * 
	 * @return
	 */
	public Rectangle makeCardSupportTrans() {
		Rectangle rect = this.makeCardSupport();
		rect.setOpacity(rect.getOpacity() * 0.5);
		return rect;
	}

	@Override
	public Color getBorderColor() {
		// Paint paint = this.getBorder().getStrokes().get(0).getTopStroke();
		// return new Color(paint.red, paint.green, paint.blue, 1);
		return Col.GOODD.getColor();
	}

	@Override
	public Color getBackgroundColor() {
		return Col.INFOD.getColor();
	}

	@Override
	public BorderStroke getBorderStrokes() {
		return this.getBorder().getStrokes().get(0);
	}

}
