package rina.turok.bope.framework;

// Rina.
public class TurokInt {
	String name;
	String tag;

	int value;
	int max;
	int min;

	public TurokInt(String name, String tag, int turok_int, int max, int min) {
		this.name  = name;
		this.tag   = tag;
		this.value = turok_int;
		this.max   = max;
		this.min   = min;
	}

	public void set_value(int turok_int) {
		this.value = turok_int;
	}

	public void set_value_slider(int turok_int) {
		if (turok_int >= this.max) {
			this.value = this.max;
		} else if (turok_int <= this.min) {
			this.value = this.min;
		} else {
			this.value = turok_int;
		}
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