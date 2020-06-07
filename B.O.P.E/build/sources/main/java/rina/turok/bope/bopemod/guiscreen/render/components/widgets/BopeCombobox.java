package rina.turok.bope.bopemod.guiscreen.render.components.widgets;

import net.minecraft.client.Minecraft;

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
* 01/05/20.
*
*/
public class BopeCombobox extends BopeAbstractWidget {
	private ArrayList<BopeCombobox.BopeMenuCombobox> values;

	private BopeFrame        frame;
	private BopeModuleButton master;
	private BopeSetting      setting;

	private String combobox_name;

	private int x;
	private int y;

	private int menu_x;
	private int menu_y;

	private int width;
	private int height;

	private int menu_width;
	private int menu_height;

	private int combobox_actual_value;

	private int save_y;

	private int border_size = 0;

	private int side = 0;

	private boolean can;
	private boolean smoth  = Bope.get_setting_manager().get_setting_with_tag("GUISmothFont").get_value(true);
	private boolean opened = false;

	private BopeDraw font = new BopeDraw(1);

	public final Minecraft mc = Minecraft.getMinecraft();

	public BopeCombobox(BopeFrame frame, BopeModuleButton master, String tag, int update_postion) {
		this.values  = new ArrayList<>();
		this.frame   = frame;
		this.master  = master;
		this.setting = Bope.get_setting_manager().get_setting_with_tag(master.get_module(), tag);

		this.x = master.get_x();
		this.y = update_postion;

		this.menu_x = frame.get_x() + frame.get_width();
		this.menu_y = this.y;

		this.save_y = this.y;

		this.width  = master.get_width();
		this.height = font.get_string_height(this.setting.get_name(), this.smoth);

		this.menu_width  = 0;
		this.menu_height = 0;

		this.combobox_name = this.setting.get_name();

		this.combobox_actual_value = this.setting.get_values().indexOf(this.setting.get_current_value());

		this.can = true;

		int count = 0;
		int size  = this.setting.get_values().size();

		for (String values : this.setting.get_values()) {
			int off_y = 0;

			if (count == 0) {
				off_y = 5;
			}

			BopeCombobox.BopeMenuCombobox values_requested = new BopeMenuCombobox(this, values, this.menu_height + off_y);

			this.values.add(values_requested);

			if (count >= size) {
				this.menu_height += 2;
			} else {
				this.menu_height += 10 + 2;
			}

			count++;
		}
	}

	public BopeSetting get_setting() {
		return this.setting;
	}

	public void set_open(boolean value) {
		this.opened = value;
	}

	@Override
	public void does_can(boolean value) {
		this.can = value;
	}

	@Override
	public void set_x(int x) {
		this.x = x;
	}

	@Override
	public void set_y(int y) {
		this.y = y;
	}

	@Override
	public void set_width(int width) {
		this.width = width;
	}

	@Override
	public void set_height(int height) {
		this.height = height;
	}

	@Override
	public int get_x() {
		return this.x;
	}

	@Override
	public int get_y() {
		return this.y;
	}

	@Override
	public int get_width() {
		return this.width;
	}

	@Override
	public int get_height() {
		return this.height;
	}

	public int get_save_y() {
		return this.save_y;
	}

	@Override
	public boolean motion_pass(int mx, int my) {
		return motion(mx, my);
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

	public boolean can() {
		return this.can;
	}

	@Override
	public void mouse(int mx, int my, int mouse) {
		if (mouse == 0) {
			if (motion(mx, my) && this.master.is_open() && !is_open() && can()) {
				this.frame.does_can(false);

				set_open(true);
			} else if (!motion(mx, my) && this.master.is_open() && is_open() && can()) {
				set_open(false);
			}
		}
	}

	@Override
	public void render(int master_y, int separe, int absolute_x, int absolute_y) {
		this.smoth = Bope.get_setting_manager().get_setting_with_tag("GUISmothFont").get_value(true);
		
		set_width(this.master.get_width() - separe);

		String zbob = "rina";

		this.save_y = this.y + master_y;

		int ns_r = Bope.click_gui.theme_widget_name_r;
		int ns_g = Bope.click_gui.theme_widget_name_g;
		int ns_b = Bope.click_gui.theme_widget_name_b;

		int bg_r = Bope.click_gui.theme_widget_background_r;
		int bg_g = Bope.click_gui.theme_widget_background_g;
		int bg_b = Bope.click_gui.theme_widget_background_b;
		int bg_a = Bope.click_gui.theme_widget_background_a;

		int bd_r = Bope.click_gui.theme_widget_border_r;
		int bd_g = Bope.click_gui.theme_widget_border_g;
		int bd_b = Bope.click_gui.theme_widget_border_b;
		int bd_a = 100;

		BopeDraw.draw_string(this.combobox_name, this.x + 2, this.save_y, ns_r, ns_g, ns_b, this.smoth);
		BopeDraw.draw_string(this.setting.get_current_value(), this.x + this.width - separe  - this.font.get_string_width(this.setting.get_current_value(), this.smoth) + 2, this.save_y, ns_r, ns_g, ns_b, this.smoth);
	
		for (BopeCombobox.BopeMenuCombobox menus : this.values) {
			if (is_open()) {
				if ((this.frame.get_x() + this.frame.get_width() + this.menu_x + this.menu_width) >= ((mc.displayWidth / 2))) {
					this.side = 0;

					BopeDraw.draw_rect(this.frame.get_x() - this.menu_width, get_save_y(), this.frame.get_x() - this.menu_width + this.menu_width, get_save_y() + this.menu_height, bg_r, bg_g, bg_b, bg_a);
				} else {
					this.side = 1;

					BopeDraw.draw_rect(this.frame.get_x() + this.frame.get_width(), get_save_y(), this.frame.get_x() + this.frame.get_width() + this.menu_width, get_save_y() + this.menu_height, bg_r, bg_g, bg_b, bg_a);
				}
			}
		}
	}

	public class BopeMenuCombobox {
		private String value;

		private int x;
		private int y;

		private int save_y;

		private int width;
		private int height;

		private boolean smoth  = Bope.get_setting_manager().get_setting_with_tag("GUISmothFont").get_value(true);

		private BopeDraw font = new BopeDraw(1);

		public BopeMenuCombobox(BopeCombobox combobox, String value, int position_y) {
			this.value = value;

			this.x = 0;
			this.y = position_y;

			this.save_y = y;

			this.width  = font.get_string_width(value, this.smoth) + 2;
			this.height = font.get_string_height(value, this.smoth);
		}

		public void render(int mx, int my, int x, int y, int separate) {
			this.smoth = Bope.get_setting_manager().get_setting_with_tag("GUISmothFont").get_value(true);
		
			int ns_r = Bope.click_gui.theme_widget_name_r;
			int ns_g = Bope.click_gui.theme_widget_name_g;
			int ns_b = Bope.click_gui.theme_widget_name_b;

			int bg_r = Bope.click_gui.theme_widget_background_r;
			int bg_g = Bope.click_gui.theme_widget_background_g;
			int bg_b = Bope.click_gui.theme_widget_background_b;
			int bg_a = Bope.click_gui.theme_widget_background_a;

			int bd_r = Bope.click_gui.theme_widget_border_r;
			int bd_g = Bope.click_gui.theme_widget_border_g;
			int bd_b = Bope.click_gui.theme_widget_border_b;
			int bd_a = 100;

			this.save_y = y + this.y;
			this.x      = x;

			BopeDraw.draw_rect(this.x + separate, this.save_y, this.width + separate, this.height, 255, 255, 255, 255);
			BopeDraw.draw_string(this.value, this.x + separate, this.save_y, ns_r, ns_g, ns_b, this.smoth);
		}
	}
}