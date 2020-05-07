package rina.turok.bope.bopemod.guiscreen.render.pinnables;

import net.minecraft.client.Minecraft;

import java.util.*;

// Guiscreen;
import rina.turok.bope.bopemod.guiscreen.render.pinnables.label.BopeLabel;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

/**
* @author Rina
*
* Created by Rina.
* 04/05/20.
*
*/
public class BopePinnable {
	private String title;
	private String tag;

	private ArrayList<BopeLabel> label;

	private boolean state;
	private boolean move;

	private BopeDraw font;

	private int x;
	private int y;
	private int w;
	private int h;

	private int move_x;
	private int move_y;

	public final Minecraft mc = Minecraft.getMinecraft();

	public BopePinnable(String title, String tag, float font_, int x, int y) {
		this.title = title;
		this.tag   = tag;
		this.label = new ArrayList<>();
		this.font  = new BopeDraw(font_);

		this.x = x;
		this.y = y;
		this.w = font.get_string_width(title) + 50;
		this.h = font.get_string_height(title);

		this.move = false;
	}

	public void set_move(boolean value) {
		this.move = value;
	}

	public void set_active(boolean value) {
		this.state = value;
	}

	public void set_x(int x) {
		this.x = x;
	}

	public void set_y(int y) {
		this.y = y;
	}

	public void set_width(int w) {
		this.w = w;
	}

	public void set_height(int h) {
		this.h = h;
	}

	public void set_move_x(int x) {
		this.move_x = x;
	}

	public void set_move_y(int y) {
		this.move_y = y;
	}

	public boolean is_moving() {
		return this.move;
	}

	public String get_title() {
		return this.title;
	}

	public String get_tag() {
		return this.tag;
	}

	public int get_title_height() {
		return this.font.get_string_height(this.title);
	}

	public int get_x() {
		return this.x;
	}

	public int get_y() {
		return this.y;
	}

	public int get_width() {
		return this.w;
	}

	public int get_height() {
		return this.h;
	}

	public boolean is_active() {
		return this.state;
	}

	public boolean motion(int mx, int my) {
		if (mx >= get_x() && my >= get_y() && mx <= get_x() + get_width() && my <= get_y() + get_height()) {
			return true;
		}

		return false;
	}

	public void render() {}

	public void click(int mx, int my, int mouse) {
		if (mouse == 0) {
			if (is_active() && motion(mx, my)) {
				set_move(true);

				set_move_x(mx - get_x());
				set_move_y(my - get_y());
			}
		}
	}

	public void release(int mx, int my, int mouse) {
		set_move(false);
	}

	public void render(int mx, int my, int tick) {
		for (BopeLabel labels : this.label) {
			labels.set_save_y(this.y + 14 + labels.get_y() - (this.h + this.h));

			set_width(labels.get_width() + 5);

			if (is_active() && labels.motion(mx, my)) {
				BopeDraw.draw_rect(this.labels.get_x(), this.labels.get_y(), this.labels.get_x() + this.labels.get_width(), this.labels.get_y() + this.labels.get_height(), 190, 190, 190, 100);
			}

			labels.update(2);
		}

		if (is_moving()) {
			set_x(mx - this.move_x);
			set_y(my - this.move_y);
		}
	}

	protected void draw() {
		for (BopeLabel labels : this.label) {
			labels.render();
		}
	}

	protected BopeLabel create_line(String line_to_add) {
		BopeLabel label_created = new BopeLabel(this, line_to_add, 1, this.x + 2, this.h);

		this.label.add(label_created);

		this.h += 10;

		return label_created;
	}

	protected void draw_string(String string, int pos_x, int pos_y, int r, int g, int b) {
		BopeDraw.draw_string(string, this.x + pos_x, this.y + pos_y, r, g, b);
	}

	protected void draw_rect(int pos_x, int pos_y, int width, int height, int r, int g, int b, int a) {
		BopeDraw.draw_rect(this.x + pos_x, this.y + pos_y, this.x + this.w + width, this.y + this.h + height, r, g, b, a);
	}
}