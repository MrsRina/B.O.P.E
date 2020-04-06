package rina.turok.bope.bopemod.backgui;

import rina.turok.bope.framework.TurokDouble;
import rina.turok.bope.framework.TurokFloat;
import rina.turok.bope.framework.TurokInt;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

//
// Author: Rina.
// Rina.
// 1:2:3 TurokDouble TurokFloat TurokInt
//
public class BopeSlider {
	public static ArrayList<TurokDouble> sliders_double_list = new ArrayList<>();
	public static ArrayList<TurokFloat>  sliders_float_list  = new ArrayList<>();
	public static ArrayList<TurokInt>    sliders_int_list    = new ArrayList<>();

	static HashMap<String, TurokDouble> list_sliders_double = new HashMap<>();
	static HashMap<String, TurokFloat>  list_sliders_float  = new HashMap<>();
	static HashMap<String, TurokInt>    list_sliders_int    = new HashMap<>();

	public BopeSlider(String tag) {}

	public static void update_sliders(int type) {
		// Why I dont used elif (else if)?
		if (type == 1) {
			list_sliders_double.clear();

			for (TurokDouble sliders : sliders_double_list) {
				list_sliders_double.put(sliders.get_tag().toLowerCase(), sliders);
			}
		}

		// R: I want.
		if (type == 2) {
			list_sliders_float.clear();

			for (TurokFloat sliders : sliders_float_list) {
				list_sliders_float.put(sliders.get_tag().toLowerCase(), sliders);
			}
		}

		// Yes look it -> <!>.
		if (type == 3) {
			list_sliders_int.clear();

			for (TurokInt sliders : sliders_int_list) {
				list_sliders_int.put(sliders.get_tag().toLowerCase(), sliders);
			}
		}
	}

	public static void create_new_slider_double(String name, String tag, double value, double min, double max) {
		if (value > max || value < min) {
			System.out.println("You cant create a default value > max or < min.");
		} else {
			sliders_double_list.add(new TurokDouble(name, tag, value, min, max));

			update_sliders(1);
		}
	}

	public static void create_new_slider_float(String name, String tag, float value, float min, float max) {
		if (value > max || value < min) {
			System.out.println("You cant create a default value > max or < min.");
		} else {
			sliders_float_list.add(new TurokFloat(name, tag, value, min, max));

			update_sliders(2);
		}
	}

	public static void create_new_slider_int(String name, String tag, int value, int min, int max) {
		if (value > max || value < min) {
			System.out.println("You cant create a default value > max or < min.");
		} else {
			sliders_int_list.add(new TurokInt(name, tag, value, min, max));

			update_sliders(3);
		}
	}

	public static TurokDouble get_slider_double(String slider) {
		return list_sliders_double.get(slider.toLowerCase());
	}

	public static TurokFloat get_slider_float(String slider) {
		return list_sliders_float.get(slider.toLowerCase());
	}

	public static TurokInt get_slider_int(String slider) {
		return list_sliders_int.get(slider.toLowerCase());
	}
}