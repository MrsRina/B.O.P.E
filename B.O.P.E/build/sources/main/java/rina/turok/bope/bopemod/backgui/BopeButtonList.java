package rina.turok.bope.bopemod.backgui;

import rina.turok.bope.framework.TurokBoolean;

import java.util.*;

public class BopeButtonList {
	ArrayList<TurokBoolean> buttons;

	HashMap<String, TurokBoolean> hash;

	public BopeButtonList(String name) {
		this.buttons = new ArrayList<>();
		this.hash    = new HashMap<>();
	}

	public void update_hash() {
		this.hash.clear();

		for (TurokBoolean buttons : this.buttons) {
			this.hash.put(buttons.get_tag().toLowerCase(), buttons);
		}
	}

	public void add_button(String name, String tag, boolean default_) {
		this.buttons.add(new TurokBoolean(name, tag, default_));

		update_hash();
	}

	public TurokBoolean get_button(String button) {
		return this.hash.get(button.toLowerCase());
	}

	public ArrayList get_list() {
		return this.buttons;
	}
}