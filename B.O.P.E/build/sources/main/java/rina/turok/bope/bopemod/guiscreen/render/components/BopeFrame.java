package rina.turok.bope.bopemod.guiscreen.render.components;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.components.BopeModuleButton;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Hacks.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.task.TurokRect;
import rina.turok.turok.draw.TurokGL;
import rina.turok.turok.Turok;

/**
* @author Rina
*
* Created by Rina.
* 24/04/20.
*
*/
public class BopeFrame {
	private BopeCategory category;

	private ArrayList<BopeModuleButton> module_button;

	private int x;
	private int y;

	private int width;
	private int height;

	private int width_name;
	private int width_abs;

	private String frame_name;

	private BopeDraw font = new BopeDraw(0.75f);

	public BopeFrame(BopeCategory category) {
		this.x             = 10;
		this.y             = 10;
		this.width         = 100;
		this.height        = 20;
		this.category      = category;
		this.module_button = new ArrayList<>();
		this.width_name    = font.get_string_width(this.category.get_name());
		this.width_abs     = this.width_name;
		this.frame_name    = category.get_name();


		int button_y = this.x + 10;

		for (BopeModule modules : Bope.get_module_manager().get_modules_with_category(this.category)) {
			BopeModuleButton module_buttons = new BopeModuleButton(modules, this);

			module_buttons.set_y(button_y);

			set_height(module_buttons.get_y());

			this.module_button.add(module_buttons);

			button_y += 10;
		}
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

	public boolean on_widget(int mx, int my) {
		if (mx >= get_x() && my >= get_y() && mx <= get_x() + get_width() && my <= get_y() + get_height()) {
			return true;
		}

		return false;
	}

	public boolean on_widget_name(int mx, int my) {
		if (mx >= get_x() && my >= get_y() && mx <= get_x() + get_width() && my <= get_y() + font.get_string_height(this.frame_name)) {
			return true;
		}

		return false;
	}

	public void render() {
		for (BopeModuleButton module_buttons : this.module_button) {
			module_buttons.render(2);
		}

		this.frame_name = this.category.get_name();

		TurokGL.color(255, 255, 255, 255);
		TurokGL.draw_rect(this.x, this.y, this.width, this.height);

		font.draw_string(this.frame_name, this.x + 2, this.y + 1, 0, 0, 0);

		this.width_name = font.get_string_width(this.category.get_name());
	}
}