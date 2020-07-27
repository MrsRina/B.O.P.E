package rina.turok.turok.values;

import java.math.*;

/**
 * @author Rina
 *
 * Created by Rina.
 * 08/04/20.
 *
 */
public class TurokDouble {
	private String name;
	private String tag;

	private double value;
	private double max;
	private double min;

	public TurokDouble(String name, String tag, double turok_double, double min, double max) {
		this.name  = name;
		this.tag   = tag;
		this.value = turok_double;
		this.max   = max;
		this.min   = min;
	}

	public void set_value(double turok_double) {
		this.value = turok_double;
	}

	public void set_slider_value(double turok_double) {
		if (turok_double >= this.max) {
			this.value = this.max;
		} else if (turok_double <= this.min) {
			this.value = this.min;
		} else {
			this.value = turok_double;
		}
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}

	public double get_value() {
		return this.value;
	}

	public static double round(double abs_1) {
		if (2 < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal decimal = new BigDecimal(abs_1);

		decimal = decimal.setScale(2, RoundingMode.HALF_UP);

		return decimal.doubleValue();
	}

	// With reference in KAMI.
	public static double floor(double abs_1, double abs_2) {
		abs_1 = Math.floor(abs_1);
		abs_2 = Math.floor(abs_2);

		if (abs_1 == 0 || abs_2 == 0) {
			return abs_1 + abs_2;
		}

		// If not.
		return floor(abs_1, abs_1 % abs_2);
	}

	public static double step(double abs_1, double abs_2) {
		double floor_requested = floor(abs_1, abs_2);

		if (floor_requested > abs_2) {
			floor_requested = abs_2 / 20;
		}

		if (abs_2 > 10) {
			floor_requested = Math.round(floor_requested);
		}

		if (floor_requested == 0) {
			floor_requested = abs_2;
		}

		return floor_requested;
	}
}