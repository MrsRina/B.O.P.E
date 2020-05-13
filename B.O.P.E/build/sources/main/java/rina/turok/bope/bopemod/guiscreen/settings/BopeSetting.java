package rina.turok.bope.bopemod.guiscreen.settings;

import java.util.*;

// Data.
import rina.turok.bope.bopemod.BopeModule;

/**
 *
 * @author Rina.
 *
 * Created by Rina.
 * 22/04/2020.
 *
 * - I used like reference the code from Finzo.
 * - and the code from Finzo is Herocode...
 * - 
 * - Thanks Herocode and Finzo. :)
 *
 **/
public class BopeSetting {
	private BopeModule master;

	private String name;
	private String tag;

	private boolean button;

	private List<String> combobox;
	private      String  current;

	private String label;

	private double slider;
	private double min;
	private double max;

	private String type;

	public BopeSetting(BopeModule master, String name, String tag, boolean value) {
		this.master = master;
		this.name   = name;
		this.tag    = tag;
		this.button = value;
		this.type   = "button";
	}

	public BopeSetting(BopeModule master, String name, String tag, List<String> values, String value) {
		this.master   = master;
		this.name     = name;
		this.tag      = tag;
		this.combobox = values;
		this.current  = value;
		this.type     = "combobox";
	}

	public BopeSetting(BopeModule master, String name, String tag, String value) {
		this.master = master;
		this.name   = name;
		this.tag    = tag;
		this.label  = value;
		this.type   = "label";
	}

	public BopeSetting(BopeModule master, String name, String tag, double value, double min, double max) {
		this.master = master;
		this.name   = name;
		this.tag    = tag;
		this.slider = value;
		this.min    = min;
		this.max    = max;
		this.type   = "doubleslider";
	}

	public BopeSetting(BopeModule master, String name, String tag, int value, int min, int max) {
		this.master = master;
		this.name   = name;
		this.tag    = tag;
		this.slider = (double) value;
		this.min    = (double) min;
		this.max    = (double) max;
		this.type   = "integerslider";
	}

	public BopeModule get_master() {
		return this.master;
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}

	public void set_value(boolean value) {
		this.button = value;
	}

	public void set_current_value(String value) {
		this.current = value;
	}

	public void set_value(String value) {
		this.label = value;
	}

	public void set_value(double value) {
		if (value >= get_max(value)) {
			this.slider = get_max(value);
		} else if (value <= get_min(value)) {
			this.slider = get_min(value);
		} else {
			this.slider = value;
		}
	}

	public void set_value(int value) {
		if (value >= get_max(value)) {
			this.slider = (double) get_max(value);
		} else if (value <= get_min(value)) {
			this.slider = (double) get_min(value);
		} else {
			this.slider = (double) value;
		}
	}

	public boolean is_info() {
		if (this.name.equalsIgnoreCase("info")) {
			return true;
		}

		return false;
	}

	public boolean in(String value) {
		for (String values : this.combobox) {
			if (values.equalsIgnoreCase(value)) {
				return true;
			}
		}

		return false;
	}

	public boolean get_value(boolean type) {
		return this.button;
	}

	public List<String> get_values() {
		return this.combobox;
	}

	public String get_current_value() {
		return this.current;
	}

	public String get_value(String type) {
		return this.label;
	}

	public double get_value(double type) {
		return this.slider;
	}

	public int get_value(int type) {
		int converted = ((int) Math.round(this.slider));

		return converted;
	}

	public double get_min(double type) {
		return this.min;
	}

	public double get_max(double type) {
		return this.max;
	}

	public int get_min(int type) {
		int converted = ((int) this.min);

		return converted;
	}

	public int get_max(int type) {
		int converted = ((int) this.max);

		return converted;
	}

	public String get_type() {
		return this.type;
	}
}