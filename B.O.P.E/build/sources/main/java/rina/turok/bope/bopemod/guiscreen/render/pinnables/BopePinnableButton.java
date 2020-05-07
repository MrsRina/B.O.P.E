package rina.turok.bope.bopemod.guiscreen.render.pinnables;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopeFrame;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 05/05/20.
*
*/
public class BopePinnableButton {
	private BopePinnable pinnable;
	private BopeFrame master;

	private String name;
	private String tag;

	private int x;
	private int y;

	private int save_y;

	private int width;
	private int height;

	private BopeDraw font = new BopeDraw(1);

	private int bg_r = 0;
	private int bg_g = 0;
	private int bg_b = 100;
	private int bg_a = 255;

	private int nb_r = 255;
	private int nb_g = 255;
	private int nb_b = 255;
	private int nb_a = 255;

	public BopePinnableButton(BopeFrame master, String name, String tag) {
		this.master = master;

		this.name = name;
		this.tag  = tag;

		this.pinnable = Bope.get_hud_manager().get_pinnable_with_tag(tag);

		this.x = master.get_x();
		this.y = master.get_y();

		this.save_y = this.y;

		this.width  = this.master.get_width();
		this.height = font.get_string_height(this.pinnable.get_title());
	}

	public void set_x(int x) {
		this.x = x;
	}

	public void set_y(int y) {
		this.y = y;
	}

	public void set_save_y(int y) {
		this.save_y = y;
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

	public int get_save_y() {
		return this.save_y;
	}

	public int get_width() {
		return this.width;
	}

	public int get_height() {
		return this.height;
	}

	public boolean motion(int mx, int my, int p_x, int p_y, int p_w, int p_h) {
		if (mx >= p_x && my >= p_y && mx <= p_x + p_w && my <= p_y + p_h) {
			return true;
		}

		return false;
	}

	public boolean motion(int mx, int my) {
		if (mx >= get_x() && my >= get_save_y() && mx <= get_x() + get_width() && my <= get_save_y() + get_height()) {
			return true;
		}

		return false;
	}

	public void click(int mx, int my, int mouse) {
		this.pinnable.click(mx, my, mouse);

		if (mouse == 0) {
			if (motion(mx, my)) {
				this.master.does_can(false);

				this.pinnable.set_active(!this.pinnable.is_active());
			}
		}
	}

	public void release(int mx, int my, int mouse) {
		this.pinnable.release(mx, my, mouse);

		this.master.does_can(true);
	}

	public void render(int mx, int my, int separate) {
		set_width(this.master.get_width() - separate);

		this.save_y = this.y + this.master.get_y() - 10;

		if (this.pinnable.is_active()) {
			BopeDraw.draw_rect(this.x, this.save_y, this.x + this.width - separate, this.save_y + this.height, this.bg_r, this.bg_g, this.bg_b, this.bg_a);
		
			BopeDraw.draw_string(this.pinnable.get_title(), this.x + separate, this.save_y, this.nb_r, this.nb_g, this.nb_b);
		} else {
			BopeDraw.draw_string(this.pinnable.get_title(), this.x + separate, this.save_y, this.nb_r, this.nb_g, this.nb_b);
		}

		this.pinnable.render(mx, my, 0);
	}
}