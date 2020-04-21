package rina.turok.bope.bopemod;

import java.util.List;

// Data.
import rina.turok.bope.bopemod.BopeSetting;
import rina.turok.bope.bopemod.BopeModule;

public class BopeSetting {
	private String name;
	private String tag;

	private BopeModule master;
	private BopeTypeSetting type;

	private boolean button_value;

	public BopeSetting(BopeModule master, String name, String tag, boolean value) {
		this.master       = master;
		this.name         = name;
		this.tag          = tag;
		this.button_value = value;

		this.type = BopeTypeSetting.SETTING_BOOLEAN;
	}

	public void set_button_value(boolean value) {
		this.button_value = value;
	}

	public BopeModule get_master() {
		return this.master;
	}

	public boolean get_button_value() {
		return this.button_value;
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}

	public enum BopeTypeSetting {
		SETTING_BOOLEAN("Button"),
		SETTING_DOUBLE("Double"),
		SETTING_INTEGER("Integer"),
		SETTING_CUSTOM("String"),
		SETTING_COMBOBOX("ComboString");

		String name_setting;

		BopeTypeSetting(String name_setting) {
			this.name_setting = name_setting;
		}

		public String get_name() {
			return this.name_setting;
		}
	}

	public BopeTypeSetting get_type() {
		return this.type;
	}
}