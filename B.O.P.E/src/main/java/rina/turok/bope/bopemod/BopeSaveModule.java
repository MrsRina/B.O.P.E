package rina.turok.bope.bopemod;

import java.util.*;

import org.lwjgl.input.Keyboard;

// Data.
import rina.turok.bope.bopemod.BopeModule; 

/**
 *
 * @author Rina.
 * Created by Rina.
 *
 * 09/04/2020.
 *
 **/
public class BopeSaveModule {
	BopeModule master;

	boolean state;

	String name;
	String tag;

	int bind;

	public BopeSaveModule(BopeModule master, String name, String tag) {
		this.master = master;
		this.state  = master.is_active();
		this.name   = name;
		this.tag    = tag;
		this.bind   = bind;
	}

	public void set_state(boolean new_state) {
		this.state = new_state;
	}

	public void set_int_bind(int bind) {
		this.bind = bind;
	}

	public BopeModule get_master() {
		return this.master;
	}

	public String get_name() {
		return this.name;
	} 

	public String get_tag() {
		return this.tag;
	}

	public boolean get_state() {
		return this.state; 
	}

	public String get_string_bind() {
		if (this.bind < 0) {
			return "null";
		} else {
			String key = Keyboard.getKeyName(this.bind);

			if (key.isEmpty()) {
				return "";
			} else {
				return Character.toUpperCase(key.charAt(0)) + (key.length() != 1 ? key.substring(1).toLowerCase() : "");
			}
		}
	}

	public int get_int_bind() {
		return this.bind;
	}
}