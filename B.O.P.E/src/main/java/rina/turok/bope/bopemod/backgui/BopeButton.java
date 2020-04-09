package rina.turok.bope.bopemod.backgui;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import rina.turok.bope.framework.TurokBoolean;
import rina.turok.bope.bopemod.BopeModule;

/**
 * @author Rina.
 *
 * Created by Rina.
 * 08/04/2020.
 *
 */
public class BopeButton {
	BopeModule master;

	String name;
	String tag;

	TurokBoolean value;

	public BopeButton(BopeModule master, String name, String tag, boolean default_value) {
		this.master = master;
		this.name   = name;
		this.tag    = tag;

		this.value = new TurokBoolean(name, tag, default_value);
	}

	public void set_value(boolean new_value) {
		this.value.set_value(new_value);
	}

	public boolean get_value() {
		return this.value.get_value();
	}

	public String get_master() {
		return this.master.get_name_tag();
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}
}