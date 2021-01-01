/**
 * 
 */
package view.component;

import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * @author Adrien Jallais
 *
 */
public interface ICardView {

	public Font getFont();

	public BorderStroke getBorderStrokes();

	public Border getBorder();

	public Color getBorderColor();

	public Color getBackgroundColor();

	public void setBackground(Background background);

	public Background getBackground();

	public void setBackgroundInit(Background background);

	public Background getBackgroundInit();

	public void setBackgroundHover(Background background);

	public Background getBackgroundHover();

	public double getPrefWidth();

	public double getPrefHeight();

	public Rectangle makeSupport();

	public Rectangle makeSupportTrans();

	public StackPane makeSupported();

	public Boolean isActive();

	public void switchActive();

	public void setActive(Boolean selected);

}
