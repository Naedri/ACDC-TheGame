package nls;

import java.util.Dictionary;
import java.util.Hashtable;

public abstract class DefaultNLS {
	protected final Dictionary<String, String> d;
	private Language lang;

	public DefaultNLS(Language lang) {
		this.d = new Hashtable<String, String>();
		this.makeNLS();
		this.lang = lang;
	}

	public String get(String key) {
		return this.d.get(key);
	}

	public Language getLanguage() {
		return lang;
	}

	protected abstract void makeNLS();
}
