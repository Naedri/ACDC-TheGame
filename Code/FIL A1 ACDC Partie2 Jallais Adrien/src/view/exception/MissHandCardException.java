/**
 * 
 */
package view.exception;

import application.Main;

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

	public MissHandCardException() {
		super(Main.d.get("PLAY_human_choose_card_hand"));
		// super(Main.d.get("PLAY_human_laying_miss_hand_card"));
	}

	public MissHandCardException(String message) {
		super(message);
	}
}
