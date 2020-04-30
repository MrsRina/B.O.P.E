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
	private String frame_tag;

	private BopeDraw font = new BopeDraw(1);

	private boolean first = false;
	private boolean move;

	private int move_x;
	private int move_y;
	
	private boolean can;

	private int bd_r = 0;
	private int bd_g = 0;
	private int bd_b = 255;
	private int bd_a = 150;

	private int bg_r = 0;
	private int bg_g = 0;
	private int bg_b = 0;
	private int bg_a = 255;

	private int nc_r = 0;
	private int nc_g = 0;
	private int nc_b = 255;
	private int nc_a = 255;

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

	public void mouse(int x, int y, int mouse) {
		for (BopeModuleButton buttons : this.module_button) {
			buttons.mouse(x, y, mouse);
		}
	}

	public void mouse_release(int x, int y, int mouse) {
		for (BopeModuleButton buttons : this.module_button) {
			buttons.button_release(x, y, mouse);
		}
	}

	public void render(int x, int y) {
		this.frame_name = this.category.get_name();
		this.width_name = font.get_string_width(this.category.get_name());

		draw_background(this.bg_r, this.bg_g, this.bg_b, this.bg_a);
		draw_border(this.bd_r, this.bd_g, this.bd_b, this.bd_a, 2, "left-right");
		draw_name(this.frame_name, this.nc_r, this.nc_g, this.nc_b);

		if (is_moving()) {
			set_x(x - this.move_x);
			set_y(y - this.move_y);
		}

		for (BopeModuleButton buttons : this.module_button) {
			buttons.set_x(this.x + 2);

			buttons.render(2);
		}
	}

	public void draw_background(int r, int g, int b, int a) {
		BopeDraw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, r, g, b, a);
	}

	public void draw_border(int r, int g, int b, int a, int size, String coords) {
		BopeDraw.draw_rect(this.x - 1, this.y, this.width + 1, this.height, r, g, b, a, size, coords);
	}

	public void draw_name(String name, int r, int g, int b) {
		BopeDraw.draw_string(name, this.x + 5, this.y + 2, r, g, b);
	}
}