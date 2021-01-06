/**
 * 
 */
package view.component;

import javafx.scene.layout.Background;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * @author Adrien Jallais
 *
 */
public interface ICardView extends IClickable, IActive {

	/**
	 * 
	 * @return the font of the component
	 */
	public Font getFont();

	/**
	 * 
	 * @return the border strokes of the component
	 */
	public BorderStroke getBorderStrokes();

	/**
	 * 
	 * @return the border color of the component
	 */
	public Color getBorderColor();

	/**
	 * 
	 * @return the background color of the component
	 */
	public Color getBackgroundColor();

	/**
	 * define the background of the component
	 */
	public void setBackground(Background background);

	/**
	 * 
	 * @return the background of the component
	 */
	public Background getBackground();

	/**
	 * @param backgroundInit the backgroundInit to set
	 */
	public void setBackgroundInit(Background background);

	/**
	 * 
	 * @return Another variation of the background
	 */
	public Background getBackgroundInit();

	/**
	 * @param backgroundHover the backgroundHover to set
	 */
	public void setBackgroundHover(Background background);

	/**
	 * 
	 * @return Another variation of the background
	 */
	public Background getBackgroundHover();

	/**
	 * 
	 * @return the preferred size on the x axis of the component
	 */
	public double getPrefWidth();

	/**
	 * 
	 * @return the preferred size on the y axis of the component
	 */
	public double getPrefHeight();

	/**
	 * 
	 * @return Rectangle bigger than the Card
	 */
	public Rectangle makeSupport();

	/**
	 * 
	 * @return A stackPane with at the bottom the Rectangle supporting the Card
	 *         component at the top
	 */
	public StackPane makeSupported();

	/**
	 * Could be used to deactive Button or to emphasize a state less consistent of
	 * the component
	 * 
	 * @return Rectangle with transparency bigger than the Card
	 */
	public Rectangle makeSupportTrans();

}
