package nls;

/**
 * 
 * @author Adrien Jallais
 *
 */
public class EnNLS extends DefaultNLS {

	/**
	 * 
	 */
	public EnNLS() {
		super(LanguageApp.ENGLISH);
	}

	@Override
	protected void makeNLS() {
		// STAGE
		this.d.put("STAGE_title", "ACDC - The Game");

		// COMMON
		this.d.put("COMMON_back", "Back");
		this.d.put("COMMON_exit", "Quit");
		this.d.put("COMMON_menu", "Menu");
		this.d.put("COMMON_validate", "Confirm");
		this.d.put("COMMON_cancel", "Cancel");

		// SCENE - Main
		this.d.put("MAIN_Signature", "IMT - Adrien Jallais - FILA1");

		// SCENE - Welcome
		this.d.put("WELCOME_title", "The" + System.lineSeparator() + "Game");
		this.d.put("WELCOME_start", "Press 'Enter' to continue");

		// SCENE - Menu
		this.d.put("MENU_playHuman", "Play at 'The Game'");
		this.d.put("MENU_playIA", "Watch AI playing to 'The Game'");
		this.d.put("MENU_rules", "Read rules from the 'The Game'");
		this.d.put("MENU_authors", "Read credit");
		this.d.put("MENU_parameters", "Modify parameters");
		this.d.put("MENU_exit", "Quit");

		// SCENE - Parameters
		this.d.put("PARAMETERS_welcome", "Modify desired parameters.");
		this.d.put("PARAMETERS_language", "Choose language:");
		this.d.put("PARAMETERS_deck", "Choose the method to build the game deck:");
		this.d.put("PARAMETERS_deck_browse", "From a file");
		this.d.put("PARAMETERS_deck_random", "Randomly");
		this.d.put("PARAMETERS_text", "Text");
		this.d.put("PARAMETERS_open_file", "Open a file");
		this.d.put("PARAMETERS_choosen_file", "You have chosen the following file:");
		this.d.put("PARAMETERS_choosen_language", "You have chosen the following language:");
		this.d.put("PARAMETERS_choosen_random", "You have chosen the random construction.");

		// SCENE - Authors
		this.d.put("CREDITS_Authors", "This application is based on:");
		this.d.put("CREDITS_Author_back", "The backend by realized by:");
		this.d.put("CREDITS_Author_front", "The frontend made by:");
		this.d.put("CREDITS_Image_welcome", "The frontend image realized by:");
		this.d.put("CREDITS_Image_play", "The background image of the game board made by:");

		// SCENE - Play
		this.d.put("PLAY_mode_human", "Game mode");
		this.d.put("PLAY_mode_ia", "Demo mode");
		this.d.put("PLAY_draw", "Draw");
		this.d.put("PLAY_drawing", "Click on the Drawing Board to draw.");
		this.d.put("PLAY_drawing_needed", "Click on the deck to draw, if needed.");
		this.d.put("PLAY_score_plural", "Remaining cards");
		this.d.put("PLAY_score_singular", "Remaining cards");

		this.d.put("PLAY_dialog_init", "It's your turn!");
		this.d.put("PLAY_human_choosen_card_hand", "You have chosen a card from your hand.");
		this.d.put("PLAY_human_choosen_card_lay", "You have chosen a stack of cards from your hand.");
		this.d.put("PLAY_human_choose_card_hand", "You have chosen a card from your hand.");
		this.d.put("PLAY_human_choose_card_lay", "Choose a stack of cards from your hand.");
		this.d.put("PLAY_human_layed_card", "You have layed a card.");
		this.d.put("PLAY_human_layed_bad", "The attempted layed action is not allowed.");

		this.d.put("PLAY_human_turn_begin", "A new round begins.");
		this.d.put("PLAY_human_turn_begin-1", "The turn ");
		this.d.put("PLAY_human_turn_begin-2", " begins.");
		this.d.put("PLAY_human_turn_end", "Your turn will end.");
		this.d.put("PLAY_human_can_not_draw", "You didn't put down enough cards this turn to draw.");
		this.d.put("PLAY_human_end_good", "You won.");
		this.d.put("PLAY_human_end_bad", "You lost.");
		this.d.put("PLAY_info_restart", "To restart a game: return to the menu.");

		// SCENE - PLAY - IA
		this.d.put("PLAY_ia_begin", "AI plays in:");
		this.d.put("PLAY_ia_start", "Fire");
		this.d.put("PLAY_ia_watch", "AI is running !");
		this.d.put("PLAY_ia_turn", "AI play the turn:");
		this.d.put("PLAY_ia_end_turn", "AI stopped at turn:");
		this.d.put("PLAY_ia_end_good", "The AI beat the game.");
		this.d.put("PLAY_ia_end_bad", "The game beat the AI.");
		this.d.put("PLAY_ia_slider_label", "Time of an action (sec)");
		this.d.put("PLAY_ia_layed_card_last_turn", "AI has layed the following number of card : ");
		this.d.put("PLAY_ia_layed_card_turn", "This turn, AI has layed the following number of cards :");
		this.d.put("PLAY_ia_layed_card", "Number of layed card(s), this turn :");

		// Exception message
		this.d.put("PLAY_human_card_not_found",
				"The value provided does not correspond to any card in the indicated player's hand");
		this.d.put("PLAY_human_laying_miss_hand_card", "To pose, choose a card from your hand");
		this.d.put("PLAY_human_laying_miss_lay_card", "To pose, choose a stack of hand cards");

		this.d.put("API_build_deck_duplicated_cards", "The file indicated, seems to contain duplicate card(s)");
		this.d.put("API_build_deck_size", "The file indicated, does not seem to contain the correct number of cards.");
		this.d.put("API_build_deck", "The indicated file, does not seem to be buildable by the api.");

	}

}
