package rina.turok.bope.bopemod.backgui;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import rina.turok.bope.bopemod.backgui.BopeSliderList;
import rina.turok.bope.framework.TurokDouble;
import rina.turok.bope.framework.TurokFloat;
import rina.turok.bope.framework.TurokInt;

//
// Author: Rina.
// Rina.
// 1:2:3 TurokDouble TurokFloat TurokInt
//
public class BopeSlider {
	public static ArrayList<BopeSliderList> sliders_list = new ArrayList<>();

	public static BopeSliderList list_sliders = new BopeSliderList();

	public static void init_sliders() {
		sliders_list.add(list_sliders);
	}

	public static void create_new_slider_double(String name, String tag, double value, double min, double max) {
		list_sliders.add_double_slider(name, tag, value, min, max);
	}

	public static void create_new_slider_float(String name, String tag, float value, float min, float max) {
		list_sliders.add_float_slider(name, tag, value, min, max);
	}

	public static void create_new_slider_int(String name, String tag, int value, int min, int max) {
		list_sliders.add_int_slider(name, tag, value, min, max);
	}

	public static TurokDouble get_double_slider(String slider) {
		return list_sliders.get(slider.toLowerCase());
	}

	public static TurokFloat get_float_slider(String slider) {
		return list_sliders.get_float_slider(slider);
	}

	public static TurokInt get_int_slider(String slider) {
		return list_sliders.get_int_slider(slider);
	}

	public static ArrayList<BopeSliderList> get_sliders() {
		return sliders_double_list;
	}
}