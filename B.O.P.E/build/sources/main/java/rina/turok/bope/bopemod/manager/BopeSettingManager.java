package rina.turok.bope.bopemod.manager;

import java.util.*;

import rina.turok.bope.bopemod.BopeSetting;
import rina.turok.bope.bopemod.BopeModule;

/**
 *
 * @author Rina.
 * Created by Rina.
 *
 * 12/04/2020.
 *
 **/
public class BopeSettingManager {
	private static ArrayList<BopeSetting> array_settings = new ArrayList<BopeSetting>();

	private static HashMap<String, BopeSetting> hash_settings = new HashMap<String, BopeSetting>();

	public BopeSettingManager(String tag) {};

	public void update_hash_settings() {
		hash_settings.clear();

		for (BopeSetting setting : array_settings) {
			hash_settings.put(setting.get_tag(), setting);
		}
	}

	public BopeSetting get_setting(String setting) {
		return hash_settings.get(setting.toLowerCase());
	}

	public void create_setting(BopeModule master, String name, String tag, boolean button) {
		array_settings.add(new BopeSetting(master, name, tag, button));

		update_hash_settings();
	}

	public void create_setting(BopeModule master, String name, String tag, double slider, double min, double max) {
		array_settings.add(new BopeSetting(master, name, tag, slider, min, max));

		update_hash_settings();
	}

	public void create_setting(BopeModule master, String name, String tag, float slider, float min, float max) {
		array_settings.add(new BopeSetting(master, name, tag, slider, min, max));

		update_hash_settings();
	}	

	public void create_setting(BopeModule master, String name, String tag, int slider, int min, int max) {
		array_settings.add(new BopeSetting(master, name, tag, slider, min, max));

		update_hash_settings();
	}

	public void create_setting(BopeModule master, String name, String tag, String combobox) {
		array_settings.add(new BopeSetting(master, name, tag, "combobox"));

		update_hash_settings();
	}
}