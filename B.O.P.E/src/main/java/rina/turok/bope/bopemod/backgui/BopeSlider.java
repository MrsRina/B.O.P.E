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
	public static HashMap<String, TurokDouble> hash_doubles = new HashMap<>();
	public static HashMap<String, TurokFloat> hash_floats   = new HashMap<>();
	public static HashMap<String, TurokInt> hash_ints       = new HashMap<>();

	public static ArrayList<BopeSliderList> array_sliders = new ArrayList<>();

	public static BopeSliderList sliders = new BopeSliderList();

	public static void init_sliders() {
		array_sliders.add(sliders);
	}

	public static void update_hash_map(int type) {
		if (type == 0) {
			hash_doubles.clear();

			for (TurokDouble sliders_double : sliders.get_double_list()) {
				hash_doubles.put(sliders_double.get_tag(), sliders_double);
			}
		} else if (type == 1) {
			hash_floats.clear();

			for (TurokFloat sliders_float : sliders.get_float_list()) {
				hash_floats.put(sliders_float.get_tag(), sliders_float);
			}
		} else if (type == 3) {
			hash_ints.clear();

			for (TurokInt sliders_int : sliders.get_int_list()) {
				hash_ints.put(sliders_int.get_tag(), sliders_int);
			}
		}
	}

	public static void create_new_double_slider(String name, String tag, double value, double min, double max) {
		sliders.add_double_slider(name, tag, value, min, max);

		update_hash_map(0);
	}

	public static void create_new_float_slider(String name, String tag, float value, float min, float max) {
		sliders.add_float_slider(name, tag, value, min, max);

		update_hash_map(1);
	}

	public static void create_new_int_slider(String name, String tag, int value, int min, int max) {
		sliders.add_int_slider(name, tag, value, min, max);

		update_hash_map(2);
	}

	public static TurokDouble get_double_slider(String slider) {
		return hash_doubles.get(slider.toLowerCase());
	}

	public static TurokFloat get_float_slider(String slider) {
		return hash_floats.get(slider.toLowerCase());
	}

	public static TurokInt get_int_slider(String slider) {
		return hash_ints.get(slider.toLowerCase());
	}

	public static ArrayList<BopeSliderList> convert_to_list() {
		return array_sliders;
	}
}