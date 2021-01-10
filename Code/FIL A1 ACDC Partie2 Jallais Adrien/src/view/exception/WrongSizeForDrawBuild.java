package view.exception;

import application.Main;

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
		super(Main.d.get("API_build_deck_size"));
	}

}
