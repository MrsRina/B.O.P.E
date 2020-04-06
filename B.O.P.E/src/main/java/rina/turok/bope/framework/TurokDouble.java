package rina.turok.bope.framework;

// Rina.
public class TurokDouble {
	String name;
	String tag;

	double value;
	double max;
	double min;

	public TurokDouble(String name, String tag, double turok_double, double max, double min) {
		this.name  = name;
		this.tag   = tag;
		this.value = turok_double;
		this.max   = max;
		this.min   = min;
	}

	public void set_value(double turok_double) {
		this.value = turok_double;
	}

	public void set_value_slider(double turok_double) {
		if (turok_double >= this.max) {
			this.value = this.max;
		} else if (turok_double <= this.min) {
			this.value = this.min;
		} else {
			this.value = turok_double;
		}
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