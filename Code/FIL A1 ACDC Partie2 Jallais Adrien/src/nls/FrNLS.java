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
		super();
	}

	@Override
	protected void makeNLS() {
		// STAGE
		this.d.put("STAGE_title", "ACDC - The Game");

		// SCENE - Main
		this.d.put("MAIN_Signature", "Institut des Mines Télécom Atlantique - FILA1 - Adrien Jallais");

		// SCENE - Accueil
		this.d.put("WELCOME_Start", "Appuyer sur Entrée");

		// SCENE - Menu
		this.d.put("MENU_button_playHuman", "Jouer à 'The Game'");
		this.d.put("MENU_button_playIA", "Regarder l'IA jouer à 'The Game'");
		this.d.put("MENU_button_rules", "Lire les règles de 'The Game'");
		this.d.put("MENU_button_authors", "Contacter les auteurs");
		this.d.put("MENU_button_parameters", "Modifier les paramètres");

	}

}
