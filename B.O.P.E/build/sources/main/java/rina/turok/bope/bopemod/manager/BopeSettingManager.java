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
	private static ArrayList<BopeSetting> array_settings;

	private static HashMap<String, BopeSetting> hash_settings;

	public BopeSettingManager(String tag) {
		this.array_settings = new ArrayList<>();
		this.hash_settings  = new HashMap<>();
	};

	public void update_hash_settings() {
		this.hash_settings.clear();

		for (BopeSetting setting : this.array_settings) {
			this.hash_settings.put(setting.get_tag(), setting);
		}
	}

	public static BopeSetting get_setting(String setting) {
		return hash_settings.get(setting.toLowerCase());
	}

	public void create_setting(BopeSetting setting) {
		this.array_settings.add(setting);

		update_hash_settings();
	}

	public static ArrayList<BopeSetting> convert_to_list() {
		return array_settings;
	}
}