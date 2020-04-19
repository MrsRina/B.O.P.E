package rina.turok.bope.bopemod.manager;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

// Data.
import rina.turok.bope.bopemod.BopeSetting;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
 * @author FINZ0
 *
 * Update by Rina in 15/04/2020.
 */
public class BopeSettingManager {
    String tag;

    private List<BopeSetting> settings;

    public BopeSettingManager(String tag) {
        this.tag = tag;

        settings = new ArrayList<>();
    }

    public void register(BopeSetting setting) {
        settings.add(setting);
    }

    public BopeSetting get_setting_by_name_in(String name, BopeModule parent) {
        return settings.stream().filter(s -> s.getParent().equals(parent)).filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<BopeSetting> get_settings_from(BopeModule parent, BopeSetting.SettingType type) {
        return settings.stream().filter(s -> s.getParent().equals(parent)).filter(s -> s.getType().equals(type)).collect(Collectors.toList());
    }

    public List<BopeSetting> convert_to_list(){
        return settings;
    }

    public String get_tag() {
        return this.tag;
    }
}