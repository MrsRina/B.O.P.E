package rina.turok.bope.bopemod.guiscreen.render.components;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.components.widgets.BopeCombobox;
import rina.turok.bope.bopemod.guiscreen.render.components.widgets.BopeSlider;
import rina.turok.bope.bopemod.guiscreen.render.components.widgets.BopeButton;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeAbstractWidget;
import rina.turok.bope.bopemod.guiscreen.render.components.widgets.BopeLabel;
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

	private ArrayList<BopeAbstractWidget> widget;

	private String module_name;

	private boolean opened;

	private int x;
	private int y;

	private int width;
	private int height;

	private int opened_height;

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

 	private	int nb_r = 255;
 	private int nb_g = 255;
 	private int nb_b = 255;
 	private int nb_a = 255;

	private int border_size = 1;

	private int master_height_cache;

	public int settings_height;

	public BopeModuleButton(BopeModule module, BopeFrame master) {
		/**
		 * A value to save the y. When move the frame the save_y does the work.
		 * @param save_y;
		 **/

		this.module = module;
		this.master = master;

		this.widget = new ArrayList();

		this.module_name = module.get_name();

		this.x = 0;
		this.y = 0;

		this.width  = font.get_string_width(module.get_name()) + 5;
		this.height = font.get_string_height(module.get_name());

		this.opened_height = this.height;

		this.save_y = 0;

		this.opened = false;

		this.master_height_cache = master.get_height();

		this.settings_height = this.y + 10;

		for (BopeSetting settings : Bope.get_setting_manager().get_settings_with_module(module)) {
			if (settings.get_type().equals("button")) {
				this.widget.add(new BopeButton(master, this, settings.get_tag(), this.settings_height));

				this.settings_height += 10;
			}

			if (settings.get_type().equals("combobox")) {
				this.widget.add(new BopeCombobox(master, this, settings.get_tag(), this.settings_height));

				this.settings_height += 10;
			}

			if (settings.get_type().equals("label")) {
				this.widget.add(new BopeLabel(master, this, settings.get_tag(), this.settings_height));

				this.settings_height += 10;
			}

			if (settings.get_type().equals("doubleslider") || settings.get_type().equals("integerslider")) {
				this.widget.add(new BopeSlider(master, this, settings.get_tag(), this.settings_height));

				this.settings_height += 10;
			}
		}
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

	public void set_open(boolean value) {
		this.opened = value;
	}

	public boolean get_state() {
		return this.module.is_active();
	}

	public int get_settings_height() {
		return this.settings_height;
	}

	public int get_cache_height() {
		return this.master_height_cache;
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

	public boolean is_open() {
		return this.opened;
	}

	public boolean motion(int mx, int my) {
		if (mx >= get_x() && my >= get_save_y() && mx <= get_x() + get_width() && my <= get_save_y() + get_height()) {
			return true;
		}

		return false;
	}

	public void does_widgets_can(boolean can) {
		for (BopeAbstractWidget widgets : this.widget) {
			widgets.does_can(can);
		}
	}

	public void mouse(int mx, int my, int mouse) {
		for (BopeAbstractWidget widgets : this.widget) {
			widgets.mouse(mx, my, mouse);
		}

		if (mouse == 0) {
			if (motion(mx, my)) {
				this.master.does_can(false);

				set_pressed(!get_state());
			}
		}

		if (mouse == 1) {
			if (motion(mx, my)) {
				this.master.does_can(false);

				set_open(!is_open());

				this.master.refresh_frame(this, 0);
			}
		}
	}

	public void button_release(int mx, int my, int mouse) {
		for (BopeAbstractWidget widgets : this.widget) {
			widgets.release(mx, my, mouse);
		}

		this.master.does_can(true);
	}

	public void render(int mx, int my, int separe) {
		set_width(this.master.get_width() - separe);

		this.save_y = this.y + this.master.get_y() - 10;

		if (this.module.is_active()) {
			BopeDraw.draw_rect(this.x, this.save_y, this.x + this.width - separe, this.save_y + this.height, this.bg_r, this.bg_g, this.bg_b, this.bg_a);

			font.draw_string(this.module_name, this.x + separe, this.save_y, this.nb_r, this.nb_g, this.nb_b);
		} else {
			font.draw_string(this.module_name, this.x + separe, this.save_y, this.nb_r, this.nb_g, this.nb_b);
		}

		for (BopeAbstractWidget widgets : this.widget) {
			widgets.set_x(get_x());

			boolean is_passing_in_widget = this.opened ? widgets.motion_pass(mx, my) : false;

			if (motion(mx, my) || is_passing_in_widget) {
				BopeDraw.draw_rect(this.master.get_x() - 1, this.save_y, this.master.get_width() + 1, this.opened_height, this.bd_r, this.bd_g, this.bd_b, this.bd_a, this.border_size, "right-left");
			}

			if (this.opened) {
				this.nb_r = 255;
				this.nb_g = 255;
				this.nb_b = 255;

				this.opened_height = this.height + this.settings_height - 10;

				widgets.render(get_save_y(), separe, mx, my);
			} else {
				this.nb_r = 255;
				this.nb_g = 255;
				this.nb_b = 255;

				this.opened_height = this.height;
			}
		}
	}
}