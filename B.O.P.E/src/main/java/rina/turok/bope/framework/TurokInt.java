package rina.turok.bope.framework;

// Rina.
public class TurokInt {
	String name;
	String tag;

	int value;

	public TurokInt(String name, String tag, int turok_int) {
		this.name  = name;
		this.tag   = tag;
		this.value = turok_int;
	}

	public void set_value(int turok_int) {
		this.value = turok_int;
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}

	public int get_value() {
		return this.value;
	}
}