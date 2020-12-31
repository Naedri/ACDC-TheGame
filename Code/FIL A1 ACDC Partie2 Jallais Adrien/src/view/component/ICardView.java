/**
 * 
 */
package view.component;

import javafx.scene.layout.Background;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Adrien Jallais
 *
 */
public interface ICardView {

	public Color getBorderColor();

	public Color getBackgroundColor();

	public Font getFont();

	public double getPrefWidth();

	public double getPrefHeight();

	public BorderStroke getBorderStrokes();

	public Background getBackground();
}
