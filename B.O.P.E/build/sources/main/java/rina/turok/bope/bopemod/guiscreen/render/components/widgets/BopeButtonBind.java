package rina.turok.bope.bopemod.guiscreen.render.components.widgets;

import java.awt.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.components.BopeAbstractWidget;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeModuleButton;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeFrame;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Settings.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Managers.
import rina.turok.bope.bopemod.manager.BopeEventManager;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 01/05/20.
*
*/
public class BopeButtonBind extends BopeAbstractWidget {
	private BopeFrame        frame;
	private BopeModuleButton master;

	private String button_name;
	private String points;

	private int x;
	private int y;

	private int width;
	private int height;

	private int save_y;

	private float tick;

	private boolean can;
	private boolean waiting;

	private BopeDraw font = new BopeDraw(1);

	private int bg_r = 0;
	private int bg_g = 0;
	private int bg_b = 100;
	private int bg_a = 255;

	private int nb_r = 255;
	private int nb_g = 255;
	private int nb_b = 255;
	private int nb_a = 255;

	private int bd_r = 0;
	private int bd_g = 0;
	private int bd_b = 200;
	private int bd_a = 150;

	private int border_size = 0;

	public BopeButtonBind(BopeFrame frame, BopeModuleButton master, String tag, int update_postion) {
		this.frame   = frame;
		this.master  = master;

		this.x = master.get_x();
		this.y = update_postion;

		this.save_y = this.y;

		this.width  = master.get_width();
		this.height = font.get_string_height(tag);

		this.button_name = tag;

		this.can    = true;
		this.points = ".";
		this.tick   = 0;
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
	public boolean is_binding() {
		return this.waiting;
	}

	@Override
	public void bind(char char_, int key) {
		if (this.waiting) {
			switch (key) {
				case Bope.BOPE_KEY_GUI_ESCAPE : {
					this.waiting = false;

					break;
				}

				case Bope.BOPE_KEY_DELETE : {
					this.master.get_module().set_bind(0);

					this.waiting = false;

					break;
				}

				default : {
					this.master.get_module().set_bind(key);

					this.waiting = false;

					break;
				}
			}
		}
	}

	@Override
	public void mouse(int mx, int my, int mouse) {
		if (mouse == 0) {
			if (motion(mx, my) && this.master.is_open() && can()) {
				this.frame.does_can(false);

				this.waiting = true;
			}
		}
	}

	@Override
	public void render(int master_y, int separe, int absolute_x, int absolute_y) {
		set_width(this.master.get_width() - separe);

		float[] tick_color = {
			(System.currentTimeMillis() % (360 * 32)) / (360f * 32)
		};

		int color_b = Color.HSBtoRGB(tick_color[0], 1, 1);

		if ((color_b & 0xFF) <= 100) {
			this.bg_a = 100;
		} else if ((color_b & 0xFF) >= 200) {
			this.bg_a = 200;
		} else {
			this.bg_a = (color_b & 0xFF);
		}

		if (this.waiting) {
			if (this.tick >= 15) {
				this.points = "..";
			}

			if (this.tick >= 30) {
				this.points = "...";
			}

			if (this.tick >= 45) {
				this.points = ".";
				this.tick   = 0.0f;
			}
		}

		boolean zbob = true;

		this.save_y = this.y + master_y;

		if (this.waiting) {
			BopeDraw.draw_rect(get_x(), this.save_y, get_x() + this.width, this.save_y + this.height, this.bg_r, this.bg_g, this.bg_b, this.bg_a);

			this.tick += 0.1f;

			BopeDraw.draw_string("Listening " + this.points, this.x + 2, this.save_y, this.nb_r, this.nb_g, this.nb_b);
		} else {
			BopeDraw.draw_string("Bind <" + this.master.get_module().get_bind("string") + ">", this.x + 2, this.save_y, this.nb_r, this.nb_g, this.nb_b);
		}

		tick_color[0] += 5;
	}
}