package api;

/**
 * 
 * @author Adrien Jallais
 * 
 *         Exception to be used in the case, the number of int given from the
 *         file to build the deck is not correct
 */
public class WrongSizeForDrawBuild extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2401226273522673612L;

	public WrongSizeForDrawBuild() {
		super("The file indicated, does not seem to contain the correct number of cards.");
	}

}
