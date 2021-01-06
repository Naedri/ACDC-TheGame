/**
 * 
 */
package view.exception;

/**
 * @author Adrien Jallais
 *
 *         Exception to be used in the case, the user try to lay whereas he has
 *         not yet choose a lay
 */
public class MissLayCardException extends Exception {

	/**
	 * serial number
	 */
	private static final long serialVersionUID = -4855286190791451127L;

	public MissLayCardException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
