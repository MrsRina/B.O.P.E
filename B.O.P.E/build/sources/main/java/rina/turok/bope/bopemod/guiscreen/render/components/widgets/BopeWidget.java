package rina.turok.bope.bopemod.guiscreen.render.components.widgets;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.components.BopeModuleButton;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeFrame;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Settings.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Core.
import rina.turok.bope.Bope;

public class BopeWidget {
	private BopeFrame        frame;
	private BopeModuleButton master;
	private BopeSetting      setting;

	private String setting_name;
	private String setting_type;

	private int x;
	private int y;

	private int width;
	private int height;

	private int setting_save_y;

	private BopeDraw font = new BopeDraw(1);

	private int bg_r = 0;
	private int bg_g = 0;
	private int bg_b = 0;
	private int bg_a = 255;

	private int ns_r = 255;
	private int ns_g = 255;
	private int ns_b = 255;
	private int ns_a = 255;

	private int bd_r = 0;
	private int bd_g = 0;
	private int bd_b = 42;
	private int bd_a = 150;

	private int border_size = 0;

	public BopeWidget(BopeFrame frame, BopeModuleButton master, BopeSetting setting, int update_position) {
		this.frame = frame;

		this.setting_name = setting.get_name();
		this.setting_type = setting.get_type();

		this.setting = setting;

		this.master = master;

		this.x = master.get_x();
		this.y = update_position;

		this.width  = master.get_width();
		this.height = master.get_height();

		this.setting_save_y = this.y;
	}

	public void set_save_y(int y) {
		this.setting_save_y = y;
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

	public int get_save_y() {
		return this.setting_save_y;
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
		if (mx <= get_x() && my <= get_save_y() && mx >= get_x() + get_width() && my >= get_save_y() + font.get_string_height(this.setting_name)) {
			return true;
		}

		return false;
	}

	public void release(int x, int y, int mouse) {
		this.frame.does_can(true);
	}

	public void mouse(int x, int y, int mouse) {
		if (this.setting_type.equals("button")) {
			if (mouse == 0 && this.master.is_open()) {
				if (motion(x, y)) {
					this.frame.does_can(false);

					this.setting.set_value(this.setting.get_value(true));
				}
			}
		}
	}

	public void render() {
		if (this.setting_type.equals("button")) {
			this.setting_save_y = this.y + this.master.get_y() - 10;

			if (this.setting.get_value(true)) {
				BopeDraw.draw_rect(this.x, this.setting_save_y, this.x + this.width, this.setting_save_y + this.height, this.bg_r, this.bg_g, this.bg_b, this.bg_a);

				BopeDraw.draw_string(this.setting_name, this.x, this.setting_save_y, this.ns_r, this.ns_g, this.ns_b);
			} else {
				BopeDraw.draw_string(this.setting_name, this.x, this.setting_save_y, this.ns_r, this.ns_g, this.ns_b);
			}
		}
	}
}