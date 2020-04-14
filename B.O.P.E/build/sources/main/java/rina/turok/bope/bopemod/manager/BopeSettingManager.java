package rina.turok.bope.bopemod.manager;

import rina.turok.bope.bopemod.BopeSetting;
import rina.turok.bope.bopemod.BopeModule;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FINZ0
 */
public class BopeSettingManager {
    private List<BopeSetting> settings;

    public BopeSettingManager(String tag){
        settings = new ArrayList<>();
    }

    public void register(BopeSetting setting){
        settings.add(setting);
    }

    public BopeSetting getSettingByNameAndMod(String name, BopeModule parent){
        return settings.stream().filter(s -> s.getParent().equals(parent)).filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<BopeSetting> getSettingsForMod(BopeModule parent){
        return settings.stream().filter(s -> s.getParent().equals(parent)).collect(Collectors.toList());
    }

    public List<BopeSetting> convert_to_list(){
        return settings;
    }
}