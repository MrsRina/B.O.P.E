package rina.turok.bope.bopemod.guiscreen.settings;

import java.util.*;

// Data.
import rina.turok.bope.bopemod.BopeModule;

/**
 *
 * @author Rina.
 *
 * Created by Rina.
 * 01/06/2020.
 *
 **/
public class BopeSetting<T> {
	String name;
	String tag;
	String type;

	T value;
	T values;
	T min;
	T max;

	// Type button.
	public BopeSetting(BopeModule master, String name, String tag, T value) {
		this.name  = name;
		this.tag   = tag;
		this.value = value;
		this.type  = "boolean";
	}

	// Type combobox.
	public BopeSetting(BopeModule master, String name, String tag, T value, List<T> list) {
		this.name   = name;
		this.tag    = tag;
		this.value  = value;
		this.values = values;
		this.type   = "boolean";
	}

	// Type label.
	public BopeSetting(BopeModule master, String name, String tag, T value) {
		this.name  = name;
		this.tag   = tag;
		this.value = value;
		this.type  = "label";
	}

	// Type slider.
	public BopeSetting(BopeModule master, String name, String tag, T value, T min, T max) {
		this.name  = name;
		this.tag   = tag;
		this.value = value;
		this.min   = min;
		this.max   = max;
		this.type  = "slider";
	}

	public void set_value(T value) {
		this.value = value;
	}

	public List<T> get_values() {
		return this.values;
	}

	public T get_value() {
		return this.value;
	}

	public T get_min() {
		return this.min;
	}

	public T get_max() {
		return this.max;
	}

	public boolean in(T value) {
		if (value.equalsIgnoreCase(value)) {
			return true;
		}

		return false;
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}
}