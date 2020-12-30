package view.button;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import view.constant.BordW;
import view.constant.Col;
import view.constant.Rad;

public class ButtonChangeScene extends AButton {

	public ButtonChangeScene(String text) {
		super(text);
		this.setTextFill(Col.GOODD.getColor());
		this.setBorder(new Border(new BorderStroke(Col.GOODD.getColor(), BorderStrokeStyle.SOLID,
				new CornerRadii(Rad.MEDIUM.getRadius()), new BorderWidths(BordW.HIGH.getWidth()))));
	}

}
