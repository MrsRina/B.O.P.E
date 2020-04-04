package rina.turok.bope.framework;

// Rina.
public class TurokString {
	String name;
	String tag;
	String value;

	public TurokString(String name, String tag, String string) {
		this.name  = name;
		this.tag   = tag;
		this.value = string;
	}

	public void set_value(String string) {
		this.value = string;
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}

	public String get_value() {
		return this.value;
	}
} 