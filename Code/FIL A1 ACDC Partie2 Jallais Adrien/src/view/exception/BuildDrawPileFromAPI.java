package view.exception;

import application.Main;

/**
 * 
 * @author Adrien Jallais
 *
 *         General exception for the fromFile API method
 */
public class BuildDrawPileFromAPI extends Exception {

	/**
	 * serial number
	 */
	private static final long serialVersionUID = -4734256631976589686L;

	public BuildDrawPileFromAPI() {
		super(Main.d.get("API_build_deck"));
	}
}
