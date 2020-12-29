/**
 * 
 */
package view.constant;

/**
 * @author Adrien Jallais
 *
 */
public enum FontS {
	LITTLE(11.0), MEDIUM(13.0), HIGH(15.0);

	private double size;

	private FontS(double size) {
		this.size = size;
	}

	public double getSize() {
		return this.size;
	}
}
