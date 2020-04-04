package rina.turok.bope.framework;

// Rina.
public class TurokDouble {
	String name;
	String tag;

	double value;

	public TurokDouble(String name, String tag, double turok_double) {
		this.name  = name;
		this.tag   = tag;
		this.value = turok_double;
	}

	public void set_value(double turok_double) {
		this.value = turok_double;
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}

	public double get_value() {
		return this.value;
	}
}