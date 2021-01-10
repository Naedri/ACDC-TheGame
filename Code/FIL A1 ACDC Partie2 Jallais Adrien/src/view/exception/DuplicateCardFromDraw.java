package view.exception;

import application.Main;

/**
 * 
 * @author Adrien Jallais
 * 
 *         Raised when there is a duplicate number of card in the given file for
 *         the draw pile construction
 */
public class DuplicateCardFromDraw extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2499400066093246800L;

	public DuplicateCardFromDraw() {
		super(Main.d.get("API_build_deck_duplicated_cards"));
	}

}
