package rina.turok.bope.bopemod.guiscreen.render.components;

import java.util.*;
import java.awt.*;

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
	private String frame_tag;

	private BopeDraw font = new BopeDraw(1);

	private boolean first = false;
	private boolean move;

	private int move_x;
	private int move_y;
	
	private boolean can;

	private int bd_r = 0;
	private int bd_g = 0;
	private int bd_b = 42;
	private int bd_a = 150;

	private int bg_r = 0;
	private int bg_g = 0;
	private int bg_b = 0;
	private int bg_a = 255;

	private int nc_r = 0;
	private int nc_g = 0;
	private int nc_b = 255;
	private int nc_a = 255;

	private int border_size = 1;

	public BopeFrame(BopeCategory category) {
		// Why space and not aligned? Sorry, was dirty.
		this.x = 10;
		this.y = 10;

		this.width  = 100;
		this.height = 25;

		this.category = category;

		this.module_button = new ArrayList<>();

		this.width_name = font.get_string_width(this.category.get_name());
		this.width_abs  = this.width_name;
		
		this.frame_name = category.get_name();
		this.frame_tag  = category.get_tag();

		this.move_x = 0;
		this.move_y = 0;

		int size  = Bope.get_module_manager().get_modules_with_category(category).size();
		int count = 0;

		for (BopeModule modules : Bope.get_module_manager().get_modules_with_category(category)) {
			BopeModuleButton buttons = new BopeModuleButton(modules, this);

			buttons.set_y(this.height);

			this.module_button.add(buttons);

			count++;

			if (count >= size) {
				this.height += 5;
			} else {
				this.height += 10 + 2;
			}
		}

		this.move = false;
		this.can  = true;
	}

	public void refresh_frame(BopeModuleButton button, int combo_height) {
		this.height = 25;

		int size  = Bope.get_module_manager().get_modules_with_category(this.category).size();
		int count = 0;

		for (BopeModuleButton buttons : this.module_button) {
			buttons.set_y(this.height);

			count++;

			int compare = 0;

			if (count >= size) {
				compare = 5;
			} else {
				compare = 10;
			}

			if (buttons.is_open()) {
				if (compare == 5) {
					this.height += buttons.get_settings_height() - compare;
				} else {
					this.height += buttons.get_settings_height();
				}
			} else {
				this.height += compare;
			}
		}
	}

	public void does_can(boolean value) {
		this.can = value;
	}

	public void set_move(boolean value) {
		this.move = value;
	}

	public void set_move_x(int x) {
		this.move_x = x;
	}

	public void set_move_y(int y) {
		this.move_y = y;
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

	public String get_name() {
		return this.frame_name;
	}

	public String get_tag() {
		return this.frame_tag;
	}

	public boolean is_moving() {
		return this.move;
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

	public boolean can() {
		return this.can;
	}

	public boolean motion(int mx, int my) {
		if (mx >= get_x() && my >= get_y() && mx <= get_x() + get_width() && my <= get_y() + get_height()) {
			return true;
		}

		return false;
	}

	public boolean motion(String tag, int mx, int my) {
		if (mx >= get_x() && my >= get_y() && mx <= get_x() + get_width() && my <= get_y() + font.get_string_height(this.frame_name)) {
			return true;
		}

		return false;
	}

	public boolean is_binding() {
		boolean value_requested = false;

		for (BopeModuleButton buttons : this.module_button) {
			if (buttons.is_binding()) {
				value_requested = true;
			}
		}

		return value_requested;
	}

	public void does_button_for_do_widgets_can(boolean can) {
		for (BopeModuleButton buttons : this.module_button) {
			buttons.does_widgets_can(can);
		}
	}

	public void bind(char char_, int key) {
		for (BopeModuleButton buttons : this.module_button) {
			buttons.bind(char_, key);
		}
	}

	public void mouse(int mx, int my, int mouse) {
		for (BopeModuleButton buttons : this.module_button) {
			buttons.mouse(mx, my, mouse);
		}
	}

	public void mouse_release(int mx, int my, int mouse) {
		for (BopeModuleButton buttons : this.module_button) {
			buttons.button_release(mx, my, mouse);
		}
	}

	public void render(int mx, int my) {
		float[] tick_color = {
			(System.currentTimeMillis() % (360 * 32)) / (360f * 32)
		};

		int color_b = Color.HSBtoRGB(tick_color[0], 1, 1);

		if ((color_b & 0xFF) <= 50) {
			this.bd_b = 50;
		} else if ((color_b & 0xFF) >= 120) {
			this.bd_b = 120;
		} else {
			this.bd_b = (color_b & 0xFF);
		}

		this.frame_name = this.category.get_name();
		this.width_name = font.get_string_width(this.category.get_name());

		BopeDraw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, this.bg_r, this.bg_g, this.bg_b, this.bg_a);
		BopeDraw.draw_rect(this.x - 1, this.y, this.width + 1, this.height, this.bd_r, this.bd_g, this.bd_b, this.bd_a, this.border_size, "left-right");
		
		BopeDraw.draw_string(this.frame_name, this.x + 5, this.y + 4, this.nc_r, this.nc_g, this.nc_b);

		if (is_moving()) {
			set_x(mx - this.move_x);
			set_y(my - this.move_y);
		}

		for (BopeModuleButton buttons : this.module_button) {
			buttons.set_x(this.x + 2);

			buttons.render(mx, my, 2);
		}

		tick_color[0] += 1;
	}
}