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
	private int width;
	private int height;

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

		this.width  = 1;
		this.height = 10;

		this.move = false;
	}

	public void refresh_pinnable() {
		this.height = 10;

		int size  = this.label.size();
		int count = 0;

		for (BopeLabel labels : this.label) {
			count++;

			if (count >= size) {
				labels.set_x(this.x + this.height + 2);
			} else {
				labels.set_x(this.x + this.height);
			}
		}
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

	public void set_width(int width) {
		this.width = width;
	}

	public void set_height(int height) {
		this.height = height;
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
		return this.width;
	}

	public int get_height() {
		return this.height;
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

			if (is_active() && motion(mx, my)) {
				BopeDraw.draw_rect(this.x, this.y, this.width, this.height - 5, 0, 0, 0, 50, 2, "right-left-down-up");
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

	protected BopeLabel create_line(String line_to_add, String tag) {
		BopeLabel label_created = new BopeLabel(this, line_to_add, tag, 1);

		label_created.set_x(this.x + 2);
		label_created.set_y(this.y + this.height + 2);

		this.label.add(label_created);

		this.height += 10;

		return label_created;
	}

	protected void draw_string(String string, int pos_x, int pos_y, int r, int g, int b) {
		BopeDraw.draw_string(string, this.x + pos_x, this.y + pos_y, r, g, b);
	}

	protected void draw_rect(int pos_x, int pos_y, int width, int height, int r, int g, int b, int a) {
		BopeDraw.draw_rect(this.x + pos_x, this.y + pos_y, this.x + this.width + width, this.y + this.height + height, r, g, b, a);
	}
}