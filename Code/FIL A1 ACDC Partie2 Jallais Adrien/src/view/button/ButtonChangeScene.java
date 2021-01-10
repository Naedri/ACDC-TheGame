package view.button;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import view.constant.BordW;
import view.constant.ColorApp;
import view.constant.RadiusApp;

public class ButtonChangeScene extends AButton {

	public ButtonChangeScene(String text) {
		super(text);
		this.setTextFill(ColorApp.GOODD.getColor());
		this.setBorder(new Border(new BorderStroke(ColorApp.GOODD.getColor(), BorderStrokeStyle.SOLID,
				new CornerRadii(RadiusApp.MEDIUM.getRadius()), new BorderWidths(BordW.HIGH.getWidth()))));
	}

}
