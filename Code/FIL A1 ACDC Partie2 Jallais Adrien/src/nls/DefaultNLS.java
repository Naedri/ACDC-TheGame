package nls;

import java.util.Dictionary;
import java.util.Hashtable;

public abstract class DefaultNLS {
	protected final Dictionary<String, String> d;

	public DefaultNLS() {
		this.d = new Hashtable<String, String>();
		this.makeNLS();
	}

	public String get(String key) {
		return this.d.get(key);
	}

	protected abstract void makeNLS();
}
