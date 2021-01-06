/**
 * 
 */
package view.exception;

/**
 * @author Adrien Jallais
 * 
 *         Exception to be used in the case, the user try to lay whereas he has
 *         not yet choose a card from his hand
 */
public class MissHandCardException extends Exception {

	/**
	 * serial number
	 */
	private static final long serialVersionUID = 3747754235892759247L;

	public MissHandCardException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
