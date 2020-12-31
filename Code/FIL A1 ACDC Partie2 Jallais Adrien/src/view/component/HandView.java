/**
 * 
 */
package view.component;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import view.constant.Col;
import view.constant.Rad;

/**
 * @author Adrien Jallais
 *
 */
public class HandView extends HBox {
	private List<CardView> cardL;
	private List<StackPane> stackL;

	public HandView(List<CardView> listCard, double spacing) {
		super(spacing);
		initHand(listCard);
		setStyle();
	}

	public HandView(List<CardView> listCard) {
		super();
		initHand(listCard);
		setStyle();
	}

	private void setStyle() {
		this.setBackground(new Background(
				new BackgroundFill(Col.INFOL.getColor(), new CornerRadii(Rad.HIGH.getRadius()), Insets.EMPTY)));
	}

	private void initHand(List<CardView> listCard) {
		this.cardL = listCard;
		this.stackL = new ArrayList<StackPane>();

		cardL.forEach(card -> {
			StackPane sp = new StackPane();
			sp.getChildren().addAll(card.makeCardSupport(), card);
			this.stackL.add(sp);
		});

		stackL.forEach(stack -> {
			this.getChildren().add(stack);
		});

	}
}
