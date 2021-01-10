/**
 * 
 */
package view.constant;

/**
 * @author Adrien Jallais
 *
 */
public enum BordW {
	LITTLE(1.0), MEDIUM(2.0), HIGH(4.0);

	private double width;

	private BordW(double width) {
		this.width = width;
	}

	public double getWidth() {
		return this.width;
	}
}
