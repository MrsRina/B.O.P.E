package rina.turok.bope.bopemod.guiscreen.render.components.widgets;

import net.minecraft.util.math.MathHelper;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.components.BopeAbstractWidget;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeModuleButton;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeFrame;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Settings.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Core.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.values.TurokDouble;

/**
* @author Rina
*
* Created by Rina.
* 01/05/20.
*
*/
public class BopeSlider extends BopeAbstractWidget {
	private BopeFrame        frame;
	private BopeModuleButton master;
	private BopeSetting      setting;

	private String slider_name;

	private double double_;
	private int    intenger;

	private int x;
	private int y;

	private int width;
	private int height;

	private int save_y;

	private boolean can;
	private boolean compare;
	private boolean click;

	private BopeDraw font = new BopeDraw(1);

	private int bg_r = 0;
	private int bg_g = 0;
	private int bg_b = 42;
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

	public BopeSlider(BopeFrame frame, BopeModuleButton master, String tag, int update_postion) {
		this.frame   = frame;
		this.master  = master;
		this.setting = Bope.get_setting_manager().get_setting_with_tag(master.get_module(), tag);

		this.x = master.get_x();
		this.y = update_postion;

		this.save_y = this.y;

		this.width  = master.get_width();
		this.height = font.get_string_height(this.setting.get_name());

		this.slider_name = this.setting.get_name();

		this.can = true;

		this.double_  = 256;
		this.intenger = 256;

		if (this.setting.get_type().equals("doubleslider")) {
			this.double_ = this.setting.get_value(1.0);
		} else if (this.setting.get_type().equals("integerslider")) {
			this.intenger = this.setting.get_value(1);
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

				this.click = true;
			}
		}
	}

	@Override
	public void release(int mx, int my, int mouse) {
		this.click = false;
	}

	@Override
	public void render(int master_y, int separe, int absolute_x, int absolute_y) {
		set_width(this.master.get_width() - separe);

		this.save_y = this.y + master_y;

		if (this.double_ != 256 && this.intenger == 256) {
			this.compare = false;
		}

		if (this.double_ == 256 && this.intenger != 256) {
			this.compare = true;
		}

		double mouse = Math.min(this.width, Math.max(0, absolute_x - get_x()));

		if (this.click) {
			if (mouse != 0) {
				this.setting.set_value(TurokDouble.round(((mouse / this.width) * (this.setting.get_max(1.0) - this.setting.get_min(1.0)) + this.setting.get_min(1.0))));
			} else {
				this.setting.set_value(this.setting.get_min(1.0));
			}
		}

		String slider_value = this.compare == false ? Double.toString(this.setting.get_value(this.double_)) : Integer.toString((int) this.setting.get_value(this.intenger));

		BopeDraw.draw_rect(this.x, this.save_y, this.x + (this.width) * (this.setting.get_value(1) - this.setting.get_min(1)) / (this.setting.get_max(1) - this.setting.get_min(1)), this.save_y + this.height, this.bg_r, this.bg_g, this.bg_b, this.bg_a);

		BopeDraw.draw_string(this.slider_name, this.x + 2, this.save_y, this.ns_r, this.ns_g, this.ns_b);
		BopeDraw.draw_string(slider_value, this.x + this.width - separe - font.get_string_width(slider_value) + 2, this.save_y, this.ns_r, this.ns_g, this.ns_b);
	}
}