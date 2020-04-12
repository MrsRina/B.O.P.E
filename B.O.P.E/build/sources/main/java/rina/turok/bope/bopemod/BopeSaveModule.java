package rina.turok.bope.bopemod;

import java.util.*;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author Rina.
 * Created by Rina.
 *
 * 09/04/2020.
 *
 **/
public class BopeSaveModule {
	String name;
	String tag;

	int bind;

	public BopeSaveModule(String name, String tag, int bind) {
		this.name = name;
		this.tag  = tag;
		this.bind = bind;
	}

	public void set_int_bind(int bind) {
		this.bind = bind;
	}

	public String get_name() {
		return this.name;
	} 

	public String get_tag() {
		return this.tag;
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