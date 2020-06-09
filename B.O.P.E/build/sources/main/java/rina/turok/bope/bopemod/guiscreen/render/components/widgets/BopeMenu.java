package rina.turok.bope.bopemod.guiscreen.render.components.widgets;

import net.minecraft.client.Minecraft;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.components.widgets.BopeCombobox;
import rina.turok.bope.bopemod.guiscreen.render.components.widgets.BopeSubMenu;
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
public class BopeMenu {
	private BopeCombobox master;
	private ArrayList<BopeSubMenu> sub_menu;

	private int x;
	private int y;

	private int save_y;

	private int width;
	private int height;

	private boolean opened;

	public final Minecraft mc = Minecraft.getMinecraft();

	public BopeMenu(BopeCombobox master, List<String> items) {
		this.master    = master;
		this.sub_menu = new ArrayList<>();

		this.x = 0;
		this.y = 0;

		this.save_y = y;

		this.width  = 100;
		this.height = 5;

		this.opened = false;

		int count = 0;
		int size  = items.size();

		for (String values : items) {
			BopeSubMenu sub_menus = new BopeSubMenu(this, values, this.height);

			this.sub_menu.add(sub_menus);

			count++;

			if (count >= size) {
				this.height += 10 + 5;
			} else {
				this.height += 10;
			}
		}
	}

	public void set_open(boolean value) {
		this.opened = value;
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

	public BopeCombobox get_master() {
		return this.master;
	}

	public boolean is_open() {
		return this.opened;
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

	public int get_actual_side() {
		if (this.master.frame.get_x() + this.master.frame.get_width() + this.width > BopeDraw.get_width()) {
			return this.master.frame.get_x() - this.master.frame.get_width();
		} else {
			return this.master.frame.get_x() + this.master.frame.get_width();
		}
	}

	public boolean motion(int mx, int my) {
		if (mx >= this.x && my >= this.save_y && mx <= this.x + this.width && my <= this.save_y + this.height) {
			return true;
		}

		return false;
	}

	public void click(int mx, int my, int mouse) {
		for (BopeSubMenu sub_menus : this.sub_menu) {
			sub_menus.click(mx, my, mouse);
		}
	}

	public void render(boolean event_pass, int master_y, int mx, int my) {
		this.save_y = this.y + master_y;
		this.x      = get_actual_side();

		int bg_r = Bope.click_gui.theme_frame_background_r;
		int bg_g = Bope.click_gui.theme_frame_background_g;
		int bg_b = Bope.click_gui.theme_frame_background_b;
		int bg_a = Bope.click_gui.theme_frame_background_a;

		if (is_open() || event_pass) {
			BopeDraw.draw_rect(this.x, this.save_y, this.x + this.width, this.save_y + this.height, bg_r, bg_g, bg_b, bg_a);
		}

		for (BopeSubMenu sub_menus : this.sub_menu) {
			if (is_open() || event_pass) {
				sub_menus.render(master_y, mx, my, 2);
			}
		}
	}
}
