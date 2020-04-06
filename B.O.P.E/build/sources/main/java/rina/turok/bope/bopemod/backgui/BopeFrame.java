package rina.turok.bope.bopemod.backgui;

import rina.turok.bope.bopemod.backgui.BopeButton;
import rina.turok.bope.framework.TurokBoolean;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class BopeFrame {
	public static ArrayList<TurokBoolean> buttons_list = new ArrayList<>();
	static HashMap<String, TurokBoolean>  list_buttons = new HashMap<>();

	public BopeFrame(String tag) {}

	public static void update_components() {
		list_buttons.clear();

		for (TurokBoolean buttons : BopeButton.get_buttons()) {
			list_buttons.put(buttons.get_tag().toLowerCase(), buttons);
		}
	}

	public static void add_new_button(TurokBoolean button) {
		buttons_list.add(button);

		update_components();
	}

	public static void add_button(TurokBoolean button) {
		add_new_button(button);

		update_components();
	}

	public static TurokBoolean get_button(String name) {
		return list_buttons.get(name.toLowerCase());
	}
}