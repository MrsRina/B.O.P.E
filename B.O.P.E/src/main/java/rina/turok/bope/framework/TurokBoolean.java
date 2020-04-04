package rina.turok.bope.framework;

// Rina.
public class TurokBoolean {
	String name;
	String tag;

	boolean value;

	public TurokBoolean(String name, String tag, boolean turok_bool) {
		this.name  = name;
		this.tag   = tag;
		this.value = turok_bool;
	}

	public void set_value(boolean turok_bool) {
		this.value = turok_bool;
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}

	public boolean get_value() {
		return this.value;
	}
}