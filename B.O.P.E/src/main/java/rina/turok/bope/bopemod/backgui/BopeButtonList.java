package rina.turok.bope.bopemod.backgui;

import rina.turok.bope.framework.TurokBoolean;

import java.util.*;

public class BopeButtonList {
	ArrayList<TurokBoolean> BUTTON_TYPE_BOOLEAN;

	public BopeButtonList(String name) {
		this.BUTTON_TYPE_BOOLEAN = new ArrayList<>();
	}

	public void add_button(String name, String tag, boolean default_) {
		this.BUTTON_TYPE_BOOLEAN.add(new TurokBoolean(name, tag, default_));
	}

	public ArrayList<TurokBoolean> get_list() {
		return this.BUTTON_TYPE_BOOLEAN;
	}
}