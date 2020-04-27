package rina.turok.turok.values;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class TurokBoolean {
	private String name;
	private String tag;

	private boolean value;

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