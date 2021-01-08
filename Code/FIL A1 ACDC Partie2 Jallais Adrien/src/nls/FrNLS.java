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
		super(LanguageApp.FRENCH);
	}

	@Override
	protected void makeNLS() {
		// STAGE
		this.d.put("STAGE_title", "ACDC - The Game");

		// COMMON
		this.d.put("COMMON_back", "Retour");
		this.d.put("COMMON_exit", "Quitter");
		this.d.put("COMMON_menu", "Menu");
		this.d.put("COMMON_validate", "Valider");
		this.d.put("COMMON_cancel", "Annuler");

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
		this.d.put("PARAMETERS_welcome", "Modifier les paramètres souhaités.");
		this.d.put("PARAMETERS_langue", "Choisir la langue :");
		this.d.put("PARAMETERS_deck", "Choisir la méthode construisant la pioche du jeu :");
		this.d.put("PARAMETERS_deck_browse", "A partir d'un fichier");
		this.d.put("PARAMETERS_deck_random", "De manière aléatoire");
		this.d.put("PARAMETERS_text", "Texte");
		this.d.put("PARAMETERS_open_file", "Ouvrir un fichier");
		this.d.put("PARAMETERS_choosen_file", "Vous avez choisi le fichier suivant :");
		this.d.put("PARAMETERS_choosen_language", "Vous avez choisi la langue suivante :");
		this.d.put("PARAMETERS_choosen_random", "Vous avez choisi la construction aléatoire.");

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
		this.d.put("PLAY_human_layed_bad", "Le coup tenté est invalide.");

		this.d.put("PLAY_human_turn_begin", "Un nouveau tour commence.");
		this.d.put("PLAY_human_turn_end", "Votre tour va se terminer.");
		this.d.put("PLAY_human_can_not_draw", "Vous n'avez pas posé suffisament de cartes ce tour-ci, pour piocher.");
		this.d.put("PLAY_human_end_good", "Vous avez gagné.");
		this.d.put("PLAY_human_end_bad", "Vous avez perdu.");
		this.d.put("PLAY_info_restart", "Pour recommencer une partie : revenez au menu.");

		// SCENE - PLAY - IA
		this.d.put("PLAY_ia_begin", "L'IA joue dans :");
		this.d.put("PLAY_ia_start", "Feu");
		this.d.put("PLAY_ia_watch", "Résolution en cours !");
		this.d.put("PLAY_ia_turn", "L'IA commence le tour :");
		this.d.put("PLAY_ia_end_turn", "L'IA s'est arrêtée au tour :");
		this.d.put("PLAY_ia_end_good", "L'IA a battu le jeu.");
		this.d.put("PLAY_ia_end_bad", "Le jeu a battu l'IA.");
		this.d.put("PLAY_ia_slider_label", "Temps d'une action (sec)");

		// Exception message
		this.d.put("PLAY_human_card_not_found",
				"La valeur fournie ne correspond à aucune carte de la main du joueur indiqué.");
		this.d.put("PLAY_human_laying_miss_hand_card", "Pour poser, choisissez une carte de vote main");
		this.d.put("PLAY_human_laying_miss_lay_card", "Pour poser, choisisser une pile de dépôt.");

		this.d.put("API_build_deck_duplicated_cards",
				"Le fichier indiqué, semble comporter des carte(s) dupliquée(s).");
		this.d.put("API_build_deck_size", "Le fichier indiqué, ne semble pas comporter un nombre correct de cartes.");
		this.d.put("API_build_deck", "Le fichier indiqué, ne semple pas être constructible pas l'api.");
	}

}
