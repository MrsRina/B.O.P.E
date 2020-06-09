package rina.turok.bope.bopemod.guiscreen.render.components.widgets;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.components.BopeAbstractWidget;
import rina.turok.bope.bopemod.guiscreen.render.components.widgets.BopeLabel;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeModuleButton;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeFrame;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Settings.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/06/2020.
*
*/
public class BopeSubMenu {
	private BopeMenu menu;

	private String name;

	private int x;
	private int y;

	private int save_y;

	private int width;
	private int height;

	private BopeDraw font = new BopeDraw(1);

	private boolean smoth;
	
	public BopeSubMenu(BopeMenu menu, String name_value, int position_y) {
		this.menu = menu;
		this.name = name_value;

		this.x = this.menu.get_x() + 2;
		this.y = position_y;

		this.save_y = y;

		this.smoth = Bope.get_setting_manager().get_setting_with_tag("GUISmothFont").get_value(true);

		this.width  = font.get_string_width(this.name, this.smoth);
		this.height = font.get_string_height(this.name, this.smoth);
	}
	
	public void set_x(int x) {
		this.x = x;
	}

	public void set_y(int y) {
		this.y = y;
	}

	public void set_width(int width) {
		this.width = width;
	}

	public void set_height(int height) {
		this.height = height;
	}

	public int get_x() {
		return this.x;
	}

	public int get_y() {
		return this.y;
	}

	public int get_width() {
		return this.width;
	}

	public int get_height() {
		return this.height;
	}

	public boolean motion(int mx, int my) {
		if (mx >= this.x && my >= this.save_y && mx <= this.x + this.width && my <= this.save_y + this.height) {
			return true;
		}

		return false;
	}

	public void click(int mx, int my, int mouse) {
		if (mouse == 0) {
			if (motion(mx, my) && this.menu.is_open()) {
				this.menu.get_master().get_setting().set_current_value(this.name);

				this.menu.set_open(false);
				this.menu.get_master().set_open(false);
			}
		}
	}

	public void render(int master_y, int mx, int my, int separe) {
		this.save_y = this.y + master_y;
		this.x      = this.menu.get_x() + 2 + separe;
		this.width  = this.menu.get_width() - separe - 4;
		this.smoth  = Bope.get_setting_manager().get_setting_with_tag("GUISmothFont").get_value(true);

		int ns_r = Bope.click_gui.theme_widget_name_r;
		int ns_g = Bope.click_gui.theme_widget_name_g;
		int ns_b = Bope.click_gui.theme_widget_name_b;

		int bg_r = Bope.click_gui.theme_widget_background_r;
		int bg_g = Bope.click_gui.theme_widget_background_g;
		int bg_b = Bope.click_gui.theme_widget_background_b;
		int bg_a = Bope.click_gui.theme_widget_background_a;

		if (motion(mx, my) || this.name.equals(this.menu.get_master().get_setting().get_current_value())) {
			BopeDraw.draw_rect(this.x, this.save_y, this.x + this.width - separe, this.save_y + this.height, bg_r, bg_g, bg_b, bg_a);

			BopeDraw.draw_string(this.name, this.x + separe, this.save_y, ns_r, ns_r, ns_r, this.smoth);
		} else {
			BopeDraw.draw_string(this.name, this.x + separe, this.save_y, ns_r, ns_r, ns_r, this.smoth);
		}
	}
}
