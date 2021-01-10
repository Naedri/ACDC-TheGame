/**
 * 
 */
package nls;

/**
 * @author Adrien Jallais
 *
 */
public enum LanguageApp {
	FRENCH("Français"), ENGLISH("English");

	private String name;

	private LanguageApp(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
