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
		this.d.put("PLAY_drawing", "Cliquer sur la Pioche pour piocher.");
		this.d.put("PLAY_score_plural", "Cartes restantes");
		this.d.put("PLAY_score_singular", "Carte restante");

		this.d.put("PLAY_dialog_init", "A vous de jouer !");
		this.d.put("PLAY_human_choosen_card_hand", "Vous avez choisi une carte de votre main.");
		this.d.put("PLAY_human_choosen_card_lay", "Vous avez choisi une pile de dépôt.");
		this.d.put("PLAY_human_choose_card_hand", "Choisissez une carte de votre main.");
		this.d.put("PLAY_human_choose_card_lay", "Choisissez une pile de dépôt.");
		this.d.put("PLAY_human_layed_card", "Vous avez déposé une carte.");

	}

}
