package rina.turok.bope.bopemod;

import java.util.*;

import rina.turok.bope.bopemod.backgui.BopeButton;

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

	ArrayList<BopeButton> buttons = new ArrayList<>();

	public BopeSaveModule(String name, String tag, int bind) {
		this.name = name;
		this.tag  = tag;
		this.bind = bind;
	}

	public void add_button(BopeButton button) {
		this.buttons.add(button);
	}

	public ArrayList<BopeButton> get_list_buttons() {
		return this.buttons;
	}

	public String get_name() {
		return this.name;
	} 

	public String get_tag() {
		return this.tag;
	}

	public int get_bind() {
		return this.bind;
	}
}