package rina.turok.bope.bopemod.guiscreen.render.components;

import net.minecraft.client.Minecraft;

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
	private boolean smoth = Bope.get_setting_manager().get_setting_with_tag("GUISmothFont").get_value(true);

	private int move_x;
	private int move_y;
	
	private boolean can;

	private int border_a    = 0;
	private int border_size = 1;

	public final Minecraft mc = Minecraft.getMinecraft();

	public BopeFrame(BopeCategory category) {
		// Why space and not aligned? Sorry, was dirty.
		this.x = 10;
		this.y = 10;

		this.width  = 100;
		this.height = 25 + 2;

		this.category = category;

		this.module_button = new ArrayList<>();

		this.width_name = font.get_string_width(this.category.get_name(), this.smoth);
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
				this.height += 10;
			}
		}

		this.move = false;
		this.can  = true;
	}

	public void refresh_frame(BopeModuleButton button, int combo_height) {
		this.height = 25 + 2;

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
		if (mx >= get_x() && my >= get_y() && mx <= get_x() + get_width() && my <= get_y() + font.get_string_height(this.frame_name, this.smoth)) {
			return true;
		}

		return false;
	}

	public void crush(int mx, int my) {
		// Get current screen real length.
		int screen_x = (mc.displayWidth / 2);
		int screen_y = (mc.displayHeight / 2);

		set_x(mx - this.move_x);
		set_y(my - this.move_y);

		if (this.x + this.width >= screen_x) {
			this.x = screen_x - this.width - 1;
		}

		if (this.x <= 0) {
			this.x = 1;
		}

		if (this.y + this.height >= screen_y) {
			this.y = screen_y - this.height - 1;
		}

		if (this.y <= 0) {
			this.y = 1;
		}

		if (this.x % 2 != 0) {
			this.x += this.x % 2;
		}

		if (this.y % 2 != 0) {
			this.y += this.y % 2;
		}
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
		this.smoth = Bope.get_setting_manager().get_setting_with_tag("GUISmothFont").get_value(true);

		float[] tick_color = {
			(System.currentTimeMillis() % (360 * 32)) / (360f * 32)
		};

		int color_a = Color.HSBtoRGB(tick_color[0], 1, 1);

		if ((color_a) <= 50) {
			border_a = 50;
		} else if ((color_a) >= 120) {
			border_a = 120;
		} else {
			border_a = (color_a);
		}

		int nc_r = Bope.click_gui.theme_frame_name_r;
		int nc_g = Bope.click_gui.theme_frame_name_g;
		int nc_b = Bope.click_gui.theme_frame_name_b;

		int bg_r = Bope.click_gui.theme_frame_background_r;
		int bg_g = Bope.click_gui.theme_frame_background_g;
		int bg_b = Bope.click_gui.theme_frame_background_b;
		int bg_a = Bope.click_gui.theme_frame_background_a;

		int bd_r = Bope.click_gui.theme_frame_border_r;
		int bd_g = Bope.click_gui.theme_frame_border_g;
		int bd_b = Bope.click_gui.theme_frame_border_b;
		int bd_a = border_a;

		this.frame_name = this.category.get_name();
		this.width_name = font.get_string_width(this.category.get_name(), this.smoth);

		BopeDraw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, bg_r, bg_g, bg_b, bg_a);
		BopeDraw.draw_rect(this.x - 1, this.y, this.width + 2 + 1, this.height, bd_r, bd_g, bd_b, bd_a, this.border_size, "left-right");
		
		BopeDraw.draw_string(this.frame_name, this.x + 4, this.y + 3, nc_r, nc_g, nc_b, this.smoth);

		if (is_moving()) {
			crush(mx, my);
		}

		for (BopeModuleButton buttons : this.module_button) {
			buttons.set_x(this.x + 2);

			buttons.render(mx, my, 2);
		}

		tick_color[0] += 1;
	}
}