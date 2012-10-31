package de.raulin.rosario.helloCloud.sessions;

import java.util.Hashtable;
import java.util.Map;

public class Session {

	protected final String id;
	protected final Map<String, Object> values;
	
	public Session(final String id) {
		this.id = id;
		this.values = new Hashtable<String, Object>();
	}
	
	public String getId() {
		return id;
	}
	
	public void addValue(final String key, final Object value) {
		values.put(key, value);
	}
	
	public Object getValue(final String key) {
		return values.get(key);
	}
}
