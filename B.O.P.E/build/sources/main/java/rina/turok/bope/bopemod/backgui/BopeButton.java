package rina.turok.bope.bopemod.backgui;

import rina.turok.bope.framework.TurokBoolean;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class BopeButton {
	public static ArrayList<TurokBoolean> buttons_list = new ArrayList<>();
	static HashMap<String, TurokBoolean>  list_buttons = new HashMap<>();

	public BopeButton(String tag) {}

	public static void update_buttons() {
		list_buttons.clear();

		for (TurokBoolean buttons : buttons_list) {
			list_buttons.put(buttons.get_tag().toLowerCase(), buttons);
		}
	}

	public static void create_new_button(String name, String tag, boolean default_) {
		add_button(new TurokBoolean(name, tag, default_));

		update_buttons();
	}

	public static void add_button(TurokBoolean button) {
		buttons_list.add(button);
	}

	public static TurokBoolean get_button(String name) {
		return list_buttons.get(name.toLowerCase());
	}
}