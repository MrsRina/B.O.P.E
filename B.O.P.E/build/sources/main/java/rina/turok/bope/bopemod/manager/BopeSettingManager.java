package rina.turok.bope.bopemod.manager;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

// Data.
import rina.turok.bope.bopemod.BopeSetting;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

public class BopeSettingManager {
	public ArrayList<BopeSetting> array_setting = new ArrayList<>();
	
	String tag;

	public BopeSettingManager(String tag) {
		this.tag = tag;
	}

	public ArrayList<BopeSetting> get_array_settings() {
		return array_setting;
	}

	public BopeSetting get_setting_with_tag(String tag) {
		BopeSetting setting_requested = null;

		for (BopeSetting settings : get_array_settings()) {
			if (settings.get_tag().equalsIgnoreCase(tag)) {
				setting_requested = settings;
			}
		}

		return setting_requested;
	}
}