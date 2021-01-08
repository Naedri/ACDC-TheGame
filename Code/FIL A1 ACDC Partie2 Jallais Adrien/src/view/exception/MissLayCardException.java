/**
 * 
 */
package view.exception;

import application.Main;

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

	public MissLayCardException() {
		super(Main.d.get("PLAY_human_choose_card_lay"));
	}

	public MissLayCardException(String message) {
		super(message);
	}

}
