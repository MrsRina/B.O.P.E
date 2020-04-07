package rina.turok.bope.bopemod.backgui;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import rina.turok.bope.bopemod.backgui.BopeButtonList;
import rina.turok.bope.framework.TurokBoolean;

// Rina.
public class BopeButton {
	public static ArrayList<BopeButtonList> buttons_list = new ArrayList<>();

	public static BopeButtonList list_buttons = new BopeButtonList("Buttons");

	public static void init_buttons() {
		buttons_list.add(list_buttons);
	}

	public static void update_buttons() {
		list_buttons.update_hash();
	}

	public static void create_new_button(String name, String tag, boolean default_) {
		list_buttons.add_button(name, tag, default_);

		update_buttons();
	}

	public static TurokBoolean get_button(String name) {
		return list_buttons.get_button(name);
	}

	public static ArrayList<BopeButtonList> get_buttons() {
		return buttons_list;
	}
}