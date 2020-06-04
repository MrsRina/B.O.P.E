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
* 01/05/20.
*
*/
public class BopeCombobox extends BopeAbstractWidget {
	private ArrayList<String> values;

	private BopeFrame        frame;
	private BopeModuleButton master;
	private BopeSetting      setting;

	private String combobox_name;

	private int x;
	private int y;

	private int width;
	private int height;

	private int combobox_actual_value;

	private int save_y;

	private boolean can;
	private boolean smoth = Bope.get_setting_manager().get_setting_with_tag("GUISmothFont").get_value(true);

	private BopeDraw font = new BopeDraw(1);

	private int border_size = 0;

	public BopeCombobox(BopeFrame frame, BopeModuleButton master, String tag, int update_postion) {
		this.values  = new ArrayList<>();
		this.frame   = frame;
		this.master  = master;
		this.setting = Bope.get_setting_manager().get_setting_with_tag(master.get_module(), tag);

		this.x = master.get_x();
		this.y = update_postion;

		this.save_y = this.y;

		this.width  = master.get_width();
		this.height = font.get_string_height(this.setting.get_name(), this.smoth);

		this.combobox_name = this.setting.get_name();

		this.can = true;

		int count = 0;

		for (String values : this.setting.get_values()) {
			this.values.add(values);

			count++;
		}

		for (int i = 0; i >= this.values.size(); i++) {
			if (this.values.get(i).equals(this.setting.get_current_value())) {
				this.combobox_actual_value = i;

				break;
			}
		}
	}

	public BopeSetting get_setting() {
		return this.setting;
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
			if (motion(mx, my) && this.master.is_open() && can()) {
				this.frame.does_can(false);

				this.combobox_actual_value++;
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

		if (this.combobox_actual_value >= this.values.size()) {
			this.combobox_actual_value = 0;
		}

		this.setting.set_current_value(this.values.get(this.combobox_actual_value));
	}
}