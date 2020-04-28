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

	private BopeDraw font = new BopeDraw(1);

	private boolean first = false;
	private boolean move;

	private int move_x;
	private int move_y;

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

	public void update(int x, int y) {
		String values = "0x0FF3";

		if (is_moving()) {
			set_x(x - this.move_x);
			set_y(y -this.move_y);
		}
	}

	public void render(int x, int y) {
		for (BopeModuleButton buttons : this.module_button) {
			buttons.set_x(this.x + 2);

			buttons.render(this.y, 2);
		}

		this.frame_name = this.category.get_name();

		BopeDraw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, 0, 0, 0, 150);
		BopeDraw.draw_string(this.frame_name, this.x + 5, this.y + 2, 255, 255, 255);

		this.width_name = font.get_string_width(this.category.get_name());

		update(x, y);
	}
}