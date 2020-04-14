package rina.turok.bope.bopemod;

import java.util.*;

import rina.turok.bope.bopemod.BopeModule;

/**
 *
 * @author Rina.
 * Created by Rina.
 *
 * 12/04/2020 -> Offline. 11:28.
 *
 **/
public class BopeSetting {
	BopeModule master;

	String name;
	String tag;

	boolean           button;        // Button.
	double            slider_double; // Slider type double.
	float             slider_float;  // Slider type float.
	int               slider_int;    // Slider type int.
	ArrayList<String> combobox;      // Combobox.

	double slider_double_max;
	double slider_double_min;

	float slider_float_max;
	float slider_float_min;

	int slider_int_max;
	int slider_int_min;

	String current_value_combobox;

	int type;

	public BopeSetting(BopeModule master, String name, String tag, boolean default_) {
		this.master = master;
		this.name   = name;
		this.tag    = tag;

		this.button = default_;

		this.type = 0;
	}

	public BopeSetting(BopeModule master, String name, String tag, 	double value, double min, double max) {
		this.master = master;
		this.name   = name;
		this.tag    = tag;

		this.slider_double     = value;
		this.slider_double_max = max;
		this.slider_double_min = min;

		this.type = 1;
	}

	public BopeSetting(BopeModule master, String name, String tag, 	float value, float min, float max) {
		this.master = master;
		this.name   = name;
		this.tag    = tag;

		this.slider_float     = value;
		this.slider_float_max = max;
		this.slider_float_min = min;

		this.type = 2;
	}

	public BopeSetting(BopeModule master, String name, String tag, 	int value, int min, int max) {
		this.master = master;
		this.name   = name;
		this.tag    = tag;

		this.slider_int     = value;
		this.slider_int_max = max;
		this.slider_int_min = min;

		this.type = 3;
	}

	public BopeSetting(BopeModule master, String name, String tag, String combobox) {
		this.master = master;
		this.name   = name;
		this.tag    = tag;

		this.type = 4;
	}

	public void add_item(String item) {
		if (this.type != 4) {
			return;
		}

		this.combobox.add(item.toLowerCase());
	}

	public void set_current_item(String item) {
		if (!(this.type != 4)) {
			for (String items : this.combobox) {
				if (items.equalsIgnoreCase(item)) {
					this.current_value_combobox = item.toLowerCase();

					break;
				} else {
					break;
				}
			}
		}
	}

	public void set_button_value(boolean state) {
		this.button = true;
	}

	public void set_slider_double_value(double value) {
		if (value >= this.slider_double_max) {
			this.slider_double = this.slider_double_max;
		} else if (value <= this.slider_double_min) {
			this.slider_double = this.slider_double_min;
		} else {
			this.slider_double = value;
		}
	}

	public void set_slider_float_value(float value) {
		if (value >= this.slider_float_max) {
			this.slider_float = this.slider_float_max;
		} else if (value <= this.slider_float_min) {
			this.slider_float = this.slider_float_min;
		} else {
			this.slider_float = value;
		}
	}

	public void set_slider_int_value(int value) {
		if (value >= this.slider_int_max) {
			this.slider_int = this.slider_int_max;
		} else if (value <= this.slider_int_min) {
			this.slider_int = this.slider_int_min;
		} else {
			this.slider_int = value;
		}
	}

	public boolean get_button_value() {
		return this.button;
	}

	public double get_slider_double_value() {
		return this.slider_double;
	}

	public float get_slider_float_value() {
		return this.slider_float;
	}

	public int get_slider_int_value() {
		return this.slider_int;
	}

	public BopeModule get_master() {
		return this.master;
	}

	public String get_combobox_value() {
		return this.current_value_combobox;
	}

	public boolean current_value_equals(String item) {
		if (get_combobox_value().equalsIgnoreCase(item)) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<String> get_list() {
		if (!(this.type != 4)) {
			return this.combobox;
		}

		return null;
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}

	public boolean get_button_state() {
		return this.button;
	}

	public double get_slider_double_max() {
		return this.slider_double_max;
	}

	public double get_slider_double_min() {
		return this.slider_double_min;
	}

	public float get_slider_float_max() {
		return this.slider_float_max;
	}

	public float get_slider_float_min() {
		return this.slider_float_min;
	}

	public int get_slider_int_max() {
		return this.slider_int_max;
	}

	public int get_slider_int_min() {
		return this.slider_int_min;
	}

	public String get_type() {
		if (type == 0) {
			return ("button");
		} else if (type == 1) {
			return ("sliderdouble");
		} else if (type == 2) {
			return ("sliderfloat");
		} else if (type == 3) {
			return ("sliderint");
		} else if (type == 4) {
			return ("combobox");
		}

		return null;
	}

	public boolean is_button() {
		return (type == 0);
	}

	public boolean is_slider_double() {
		return (type == 1);
	}

	public boolean is_slider_float() {
		return (type == 2);
	}

	public boolean is_slider_int() {
		return (type == 3);
	}

	public boolean is_combobox() {
		return (type == 4);
	}
}