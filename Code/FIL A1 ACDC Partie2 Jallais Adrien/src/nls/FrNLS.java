/**
 * 
 */
package nls;

/**
 * @author Adrien Jallais
 *
 */
public final class FrNLS extends DefaultNLS {

	/**
	 * 
	 */
	public FrNLS() {
		super(Language.FRENCH);
	}

	@Override
	protected void makeNLS() {
		// STAGE
		this.d.put("STAGE_title", "ACDC - The Game");

		// COMMON
		this.d.put("COMMON_back", "Retour");
		this.d.put("COMMON_exit", "Quitter");
		this.d.put("COMMON_menu", "Menu");

		// SCENE - Main
		this.d.put("MAIN_Signature", "IMT - Adrien Jallais - FILA1");

		// SCENE - Welcome
		this.d.put("WELCOME_title", "The Game");
		this.d.put("WELCOME_start", "Appuyer sur 'Entrée'");

		// SCENE - Menu
		this.d.put("MENU_playHuman", "Jouer à 'The Game'");
		this.d.put("MENU_playIA", "Regarder l'IA jouer à 'The Game'");
		this.d.put("MENU_rules", "Lire les règles de 'The Game'");
		this.d.put("MENU_authors", "Contacter les auteurs");
		this.d.put("MENU_parameters", "Modifier les paramètres");
		this.d.put("MENU_exit", "Quitter");

	}

}
