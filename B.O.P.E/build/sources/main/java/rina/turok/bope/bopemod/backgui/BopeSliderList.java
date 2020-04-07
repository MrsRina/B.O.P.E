package rina.turok.bope.bopemod.backgui;

import rina.turok.bope.framework.TurokDouble;
import rina.turok.bope.framework.TurokFloat;
import rina.turok.bope.framework.TurokInt;

import java.util.*;

public class BopeSliderList {
	ArrayList<TurokDouble> slider_doubles;
	ArrayList<TurokFloat> slider_floats;
	ArrayList<TurokInt> slider_ints;

	HashMap<String, TurokDouble> hash_doubles;
	HashMap<String, TurokFloat> hash_floats;
	HashMap<String, TurokInt> hash_ints;

	public BopeSliderList() {
		this.slider_doubles = new ArrayList<>();
		this.slider_floats  = new ArrayList<>();
		this.slider_ints    = new ArrayList<>();

		this.hash_doubles = new HashMap<>();
		this.hash_floats  = new HashMap<>();
		this.hash_ints    = new HashMap<>();
	}

	public void update_sliders(int type) {
		if (type == 0) {
			hash_doubles.clear();

			for (TurokDouble sliders : slider_doubles) {
				hash_doubles.put(sliders.get_tag().toLowerCase(), sliders);
			}
		} else if (type == 1) {
			hash_floats.clear();

			for (TurokDouble sliders : slider_floats) {
				hash_floats.put(sliders.get_tag().toLowerCase(), sliders);
			}
		} else if (type == 0) {
			hash_ints.clear();

			for (TurokDouble sliders : slider_ints) {
				hash_ints.put(sliders.get_tag().toLowerCase(), sliders);
			}
		}
	}

	public void add_double_slider(String name, String tag, double value, double min, double max) {
		this.slider_doubles.add(new TurokDouble(name, tag, value, min, max));

		update_sliders(0);
	}

	public void add_float_slider(String name, String tag, float value, float min, float max) {
		this.slider_floats.add(new TurokFloat(name, tag, value, min, max));

		update_sliders(1);
	}

	public void add_int_slider(String name, String tag, int value, int min, int max) {
		this.slider_ints.add(new TurokInt(name, tag, value, min, max));

		update_sliders(2);
	}

	public TurokDouble get_double_slider(String slider) {
		this.hash_doubles.get(slider.toLowerCase());
	}

	public TurokFloat get_float_slider(String slider) {
		this.hash_floats.get(slider.toLowerCase());
	}

	public TurokInt get_int_slider(String slider) {
		this.hash_ints.get(slider.toLowerCase());
	}
}