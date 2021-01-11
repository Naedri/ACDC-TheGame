package api;

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
		super("The file indicated, seems to contain duplicate card(s)");
	}

}
