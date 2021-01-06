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

	}

}
