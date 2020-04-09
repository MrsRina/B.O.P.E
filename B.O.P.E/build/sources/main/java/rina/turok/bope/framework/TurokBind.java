package rina.turok.bope.framework;

import org.lwjgl.input.Keyboard;

import rina.turok.bope.framework.TurokInt;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class TurokBind {
	TurokInt bind;

	public TurokBind(String name, String tag, int bind_turok) {
		this.bind = new TurokInt(name, tag, bind_turok, 0, 0);
	}

	public boolean pressed(int pressed) {
		return (!is_bind() && pressed == this.bind.get_value());
	}

	public void set_key(int key) {
		this.bind.set_value(key);
	}

	public int get_key() {
		return this.bind.get_value();
	}

	public boolean is_bind() {
		return this.bind.get_value() < 0;
	}

	public TurokInt get_int() {
		return this.bind;
	}
}