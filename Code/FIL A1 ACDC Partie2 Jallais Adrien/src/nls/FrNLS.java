/**
 * 
 */
package nls;

/**
 * @author Adrien Jallais
 *
 */
public class FrNLS extends DefaultNLS {

	/**
	 * 
	 */
	public FrNLS() {
		super();
	}

	@Override
	protected void makeNLS() {
		// STAGE
		this.d.put("STAGE_title", "ACDC - The Game - Adrien Jallais");

		// SCENE - Menu
		this.d.put("MENU_button_playHuman", "Jouer contre 'The Game'");
		this.d.put("MENU_button_playIA", "Regarder l'IA jouer contre 'The Game'");
		this.d.put("MENU_button_rules", "Lire les règles");
		this.d.put("MENU_button_authors", "Contacter les auteurs");
		this.d.put("MENU_button_parameters", "Modifier les paramètres");

		// SCENE - Menu
	}

}
