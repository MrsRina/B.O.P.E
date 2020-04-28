package rina.turok.bope.bopemod.guiscreen.render.components;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.components.BopeFrame;
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Hacks.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 24/04/20.
*
*/
public class BopeModuleButton {
	private BopeModule module;
	private BopeFrame  master;

	private ArrayList<BopeSetting> setting;

	private int x;
	private int y;

	private int width;
	private int height;

	private boolean pressed;

	private BopeDraw font = new BopeDraw(0.75f);

	public BopeModuleButton(BopeModule module, BopeFrame master) {
		this.module  = module;
		this.master  = master;
		this.setting = new ArrayList<>();
		this.x       = 0;
		this.y       = 0;
		this.width   = font.get_string_width(module.get_name()) + 5;
		this.height  = 15;
		this.pressed = false;

		// for (BopeSetting settings : Bope.get_setting_manager().get_settings_with_module(module)) {}
	}

	public BopeModule get_module() {
		return this.module;
	}

	public void set_width(int width) {
		this.width = width;
	}

	public void set_height(int height) {
		this.height = height;
	}

	public void set_x(int x) {
		this.x = x;
	}

	public void set_y(int y) {
		this.y = y;
	}

	public int get_width() {
		return this.width;
	}

	public int get_height() {
		return this.height;
	}

	public int get_x() {
		return this.x;
	}

	public int get_y() {
		return this.y;
	}

	public void render(int separe) {
		if (this.pressed) {
			this.pressed = false;
		}

		this.width = font.get_string_width(module.get_name());

		font.draw_string(this.module.get_name(), this.x + separe, this.y, 255, 255, 255);
	}
}