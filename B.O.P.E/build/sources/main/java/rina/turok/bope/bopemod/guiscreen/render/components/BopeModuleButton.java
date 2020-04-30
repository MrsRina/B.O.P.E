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

	//private ArrayList<BopeAbstractWidget> widget;

	private String module_name;

	private int x;
	private int y;

	private int width;
	private int height;

	private int save_y;

	private BopeDraw font = new BopeDraw(1);

	private int bg_r = 0;
	private int bg_g = 0;
	private int bg_b = 42;
	private int bg_a = 255;

	private int bd_r = 0;
	private int bd_g = 0;
	private int bd_b = 255;
	private int bd_a = 255;

	public BopeModuleButton(BopeModule module, BopeFrame master) {
		/**
		 * A value to save the y. When move the frame the save_y does the work.
		 * @param save_y;
		 **/

		this.module = module;
		this.master = master;

		this.module_name = module.get_name();

		this.x = 0;
		this.y = 0;

		this.width  = font.get_string_width(module.get_name()) + 5;
		this.height = font.get_string_height(module.get_name());

		this.save_y = 0;

		int widgets_y = this.y + 10;

//		for (BopeSetting settings : Bope.get_setting_manager().get_settings_with_module(module)) {
//			if (settings.get_type().equals("button")) {
//				buttons = new BopeButton(this, settings.get_name(), settings.get_tag(), settings.get_value(false));

//				buttons.set_y(widgets_y);

//				this.widget.add(buttons);

//				widgets_y += 10;
//			}
//		}
	}

	public BopeModule get_module() {
		return this.module;
	}

	public void set_pressed(boolean value) {
		this.module.set_active(value);
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

	public boolean get_state() {
		return this.module.is_active();
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

	public int get_save_y() {
		return this.save_y;
	}

	public boolean motion(int x, int y) {
		if (x >= get_x() && y >= get_save_y() && x <= get_x() + get_width() && y <= get_save_y() + get_height()) {
			return true;
		}

		return false;
	}

	public void mouse(int x, int y, int mouse) {
		if (mouse == 0) {
			if (motion(x, y)) {
				this.master.does_can(false);

				set_pressed(!get_state());
			}
		}
	}

	public void button_release(int x, int y, int mouse) {
		this.master.does_can(true);
	}

	public void render(int separe) {
		set_width(this.master.get_width() - separe);

		this.save_y = this.y + this.master.get_y() - 10;

		if (this.module.is_active()) {
			draw_background(separe, this.bg_r, this.bg_g, this.bg_b, this.bg_a);
			draw_border(this.bd_r, this.bd_g, this.bd_b, this.bd_a, 2, "right-left");

			font.draw_string(this.module_name, this.x + separe, this.save_y, 255, 255, 255);
		} else {
			font.draw_string(this.module_name, this.x + separe, this.save_y, 255, 255, 255);
		}
	}

	public void draw_background(int separe, int r, int g, int b, int a) {
		BopeDraw.draw_rect(this.x, this.save_y, this.x + this.width - separe, this.save_y + this.height, r, g, b, a);
	}

	public void draw_border(int r, int g, int b, int a, int size, String coords) {
		BopeDraw.draw_rect(this.master.get_x() - 1, this.save_y, this.master.get_width() + 1, this.height, r, g, b, a, size, coords);
	}
}