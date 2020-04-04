package rina.turok.bope.framework;

// Rina.
public class TurokFloat {
	String name;
	String tag;

	float value;

	public TurokFloat(String name, String tag, float turok_float) {
		this.name  = name;
		this.tag   = tag;
		this.value = value;
	}

	public void set_value(float turok_float) {
		this.value = turok_float;
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}

	public float get_value() {
		return this.value;
	}
}