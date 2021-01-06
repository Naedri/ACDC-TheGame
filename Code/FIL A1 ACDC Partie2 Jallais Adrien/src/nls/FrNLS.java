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

		// SCENE - Parameters
		this.d.put("PARAMETERS_menu", "Menu");
		this.d.put("COMMON_menu", "Menu");
		this.d.put("COMMON_menu", "Menu");
		this.d.put("COMMON_menu", "Menu");

		// SCENE - Authors
		this.d.put("CREDITS_Authors", "Cette application est basée sur :");
		this.d.put("CREDITS_Author_back", "Le backend par réalisé par :");
		this.d.put("CREDITS_Author_front", "Le frontend réalisé par :");
		this.d.put("CREDITS_Image_welcome", "L'image d'accueil réalisée par :");
		this.d.put("CREDITS_Image_play", "L'image de fond du plateau de jeu par :");

		// SCENE - Play
		this.d.put("PLAY_mode_human", "Mode jeu");
		this.d.put("PLAY_mode_ia", "Mode démonstration");
		this.d.put("PLAY_draw", "Pioche");
		this.d.put("PLAY_drawing", "Cliquer sur la Pioche pour piocher.");
		this.d.put("PLAY_drawing_needed", "Cliquer sur la Pioche pour piocher, si besoin.");
		this.d.put("PLAY_score_plural", "Cartes restantes");
		this.d.put("PLAY_score_singular", "Carte restante");

		this.d.put("PLAY_dialog_init", "A vous de jouer !");
		this.d.put("PLAY_human_choosen_card_hand", "Vous avez choisi une carte de votre main.");
		this.d.put("PLAY_human_choosen_card_lay", "Vous avez choisi une pile de dépôt.");
		this.d.put("PLAY_human_choose_card_hand", "Choisissez une carte de votre main.");
		this.d.put("PLAY_human_choose_card_lay", "Choisissez une pile de dépôt.");
		this.d.put("PLAY_human_layed_card", "Vous avez déposé une carte.");

		this.d.put("PLAY_human_turn_begin", "Un nouveau tour commence.");
		this.d.put("PLAY_human_turn_end", "Votre tour va se terminer.");
		this.d.put("PLAY_human_can_not_draw", "Vous n'avez pas posé suffisament de cartes ce tour-ci, pour piocher.");
		this.d.put("PLAY_human_end_good", "Vous avez gagné.");
		this.d.put("PLAY_human_end_bad", "Vous avez perdu.");
		this.d.put("PLAY_info_restart", "Pour recommencer une partie : revenez au menu.");

		// SCENE - PLAY - Exception message
		this.d.put("PLAY_human_card_not_found",
				"La valeur fournie ne correspond à aucune carte de la main du joueur indiqué.");

	}

}
