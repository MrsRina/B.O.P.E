package rina.turok.bope.bopemod.backgui;

import rina.turok.bope.framework.TurokDouble;
import rina.turok.bope.framework.TurokFloat;
import rina.turok.bope.framework.TurokInt;

import java.util.*;

public class BopeSliderList {
	ArrayList<TurokDouble> SLIDER_TYPE_DOUBLE;
	ArrayList<TurokFloat> SLIDER_TYPE_FLOAT;
	ArrayList<TurokInt> SLIDER_TYPE_INT;

	public BopeSliderList() {
		this.SLIDER_TYPE_DOUBLE = new ArrayList<>();
		this.SLIDER_TYPE_FLOAT  = new ArrayList<>();
		this.SLIDER_TYPE_INT    = new ArrayList<>();
	}

	public void add_double_slider(String name, String tag, double value, double min, double max) {
		this.SLIDER_TYPE_DOUBLE.add(new TurokDouble(name, tag, value, min, max));
	}

	public void add_float_slider(String name, String tag, float value, float min, float max) {
		this.SLIDER_TYPE_FLOAT.add(new TurokFloat(name, tag, value, min, max));
	}

	public void add_int_slider(String name, String tag, int value, int min, int max) {
		this.SLIDER_TYPE_INT.add(new TurokInt(name, tag, value, min, max));
	}

	public ArrayList<TurokDouble> get_double_list() {
		return this.SLIDER_TYPE_DOUBLE;
	}

	public ArrayList<TurokFloat> get_float_list() {
		return this.SLIDER_TYPE_FLOAT;
	}

	public ArrayList<TurokInt> get_int_list() {
		return this.SLIDER_TYPE_INT;
	}
}