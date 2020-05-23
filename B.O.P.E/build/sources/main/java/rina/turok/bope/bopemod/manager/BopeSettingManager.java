package rina.turok.bope.bopemod.manager;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Data.
import rina.turok.bope.bopemod.BopeModule;

/**
 *
 * @author Rina.
 *
 * Created by Rina.
 * 22/04/2020.
 *
 **/
public class BopeSettingManager {
	private String tag;

	public ArrayList<BopeSetting> array_setting;

	public BopeSettingManager(String tag) {
		this.tag           = tag;
		this.array_setting = new ArrayList<>();
	}

	public void register(String tag, BopeSetting setting) {
		this.array_setting.add(setting);
	}

	public ArrayList<BopeSetting> get_array_settings() {
		return this.array_setting;
	}

	public BopeSetting get_setting_with_tag(BopeModule module, String tag) {
		BopeSetting setting_requested = null;

		for (BopeSetting settings : get_array_settings()) {
			if (settings.get_master().equals(module) && settings.get_tag().equalsIgnoreCase(tag)) {
				setting_requested = settings;
			}
		}

		return setting_requested;
	}

	public BopeSetting get_setting_with_tag(String tag) {
		BopeSetting setting_requested = null;

		for (BopeSetting settings : get_array_settings()) {
			if (settings.get_tag().equalsIgnoreCase(tag)) {
				setting_requested = settings;

				break;
			}
		}

		return setting_requested;
	}

	public BopeSetting get_setting_with_tag(String tag, String tag_) {
		BopeSetting setting_requested = null;

		for (BopeSetting settings : get_array_settings()) {
			if (settings.get_master().get_tag().equalsIgnoreCase(tag) && settings.get_tag().equalsIgnoreCase(tag_)) {
				setting_requested = settings;

				break;
			}
		}

		return setting_requested;
	}

	public ArrayList<BopeSetting> get_settings_with_module(BopeModule module) {
		ArrayList<BopeSetting> setting_requesteds = new ArrayList<>();

		for (BopeSetting settings : get_array_settings()) {
			if (settings.get_master().equals(module)) {
				setting_requesteds.add(settings);
			}
		}

		return setting_requesteds;
	}
}