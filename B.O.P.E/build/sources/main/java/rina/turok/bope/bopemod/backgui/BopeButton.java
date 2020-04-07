package rina.turok.bope.bopemod.backgui;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import rina.turok.bope.bopemod.backgui.BopeButtonList;
import rina.turok.bope.framework.TurokBoolean;

// Rina.
public class BopeButton {
	public static ArrayList<BopeButtonList> array_buttons = new ArrayList<>();

	public static HashMap<String, TurokBoolean> hash_buttons = new HashMap<>();

	public static BopeButtonList buttons = new BopeButtonList("Buttons");

	public static void init_buttons() {
		array_buttons.add(buttons);
	}

	public static void update_buttons() {
		hash_buttons.clear();

		for (TurokBoolean buttons : buttons.get_list()) {
			hash_buttons.put(buttons.get_tag(), buttons);
		}
	}

	public static void create_new_button(String name, String tag, boolean default_) {
		buttons.add_button(name, tag, default_);

		update_buttons();
	}

	public static TurokBoolean get_button(String button) {
		return hash_buttons.get(button.toLowerCase());
	}

	public static ArrayList<BopeButtonList> convert_to_list() {
		return array_buttons;
	}
}