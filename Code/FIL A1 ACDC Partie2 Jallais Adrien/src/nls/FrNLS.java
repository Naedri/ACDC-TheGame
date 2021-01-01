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
		this.d.put("WELCOME_title", "The" + System.lineSeparator() + "Game");
		this.d.put("WELCOME_start", "Appuyez sur 'Entrée' pour continuer");

		// SCENE - Menu
		this.d.put("MENU_playHuman", "Jouer à 'The Game'");
		this.d.put("MENU_playIA", "Regarder l'IA jouer à 'The Game'");
		this.d.put("MENU_rules", "Lire les règles de 'The Game'");
		this.d.put("MENU_authors", "Consulter les crédits");
		this.d.put("MENU_parameters", "Modifier les paramètres");
		this.d.put("MENU_exit", "Quitter");

		// SCENE - Play
		this.d.put("PLAY_mode_human", "Mode jeu");
		this.d.put("PLAY_mode_ia", "Mode démonstration");
		this.d.put("PLAY_draw", "Pioche");
		this.d.put("PLAY_score", "Carte(s) restante(s)");
		this.d.put("PLAY_dialog_init", "A vous de jouer !");
	}

}
