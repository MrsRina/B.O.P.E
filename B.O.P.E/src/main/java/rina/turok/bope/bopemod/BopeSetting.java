package rina.turok.bope.bopemod;

import java.util.List;

// Data.
import rina.turok.bope.bopemod.BopeSetting;
import rina.turok.bope.bopemod.BopeModule;

/**
 * @author FINZ0
 * 14/04/2020.
 *
 * Update by Rina in 14/04/2020.
 *
 */
public class BopeSetting {
	private final String      name;
    private final BopeModule  parent;
    private final SettingType type;

    public BopeSetting(String name, BopeModule parent, SettingType type){
        this.name   = name;
        this.parent = parent;
        this.type   = type;
    }

    public String getName() {
        return name;
    }

    public BopeModule getParent() {
        return parent;
    }

    public SettingType getType() {
    	return type;
    }

    public String getStringType() {
        if (type == SettingType.INT) {
        	return "integer";
        } else if (type == SettingType.DOUBLE) {
        	return "double";
        } else if (type == SettingType.BUTTON) {
        	return "button";
        } else if (type == SettingType.STRING) {
        	return "string";
        } else if (type == SettingType.COMBOBOX) {
        	return "combobox";
        }

        return null;
    }

    public enum SettingType {
        INT, DOUBLE, BUTTON, STRING, COMBOBOX
    }

    public static class TypeInteger extends BopeSetting {
        private int value;
        private final int min;
        private final int max;

        public TypeInteger(String name, BopeModule parent, int value, int min, int max){
            super(name, parent, SettingType.INT);
            this.value = value;
            this.min = min;
            this.max = max;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }

    public static class TypeDouble extends BopeSetting {
        private double value;

        private final double min;
        private final double max;

        public TypeDouble(String name, BopeModule parent, double value, double min, double max){
            super(name, parent, SettingType.DOUBLE);
            this.value = value;
            this.min = min;
            this.max = max;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }
    }

    public static class TypeButton extends BopeSetting {
        private boolean value;

        public TypeButton(String name, BopeModule parent, boolean value){
            super(name, parent, SettingType.BUTTON);

            this.value = value;
        }

        public boolean getValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }
    }

    public static class TypeString extends BopeSetting {
        private String value;

        public TypeString(String name, BopeModule parent, String value){
            super(name, parent, SettingType.STRING);

            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class TypeCombobox extends BopeSetting {
        private String value;
        
        private final List<String> modes;

        public TypeCombobox(String name, BopeModule parent, List<String> modes, String value){
            super(name, parent, SettingType.COMBOBOX);

            this.value = value;
            this.modes = modes;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<String> getModes(){
            return modes;
        }
    }
}