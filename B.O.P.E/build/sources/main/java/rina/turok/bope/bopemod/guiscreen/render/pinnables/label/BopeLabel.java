package rina.turok.bope.bopemod.guiscreen.render.pinnables.label;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Core.
import rina.turok.bope.Bope;

public class BopeLabel {
	private BopePinnable master;

	private String label;

	private int x;
	private int y;

	private int save_y;

	private int width;
	private int height;

	private BopeDraw font = new BopeDraw(1);

	public BopeLabel(BopePinnable master, String label, float size, int initial_x, int initial_y) {
		this.master = master;
		this.label  = label;
		
		this.x = initial_x;
		this.y = master.get_y() + master.get_title_height() + 10 + initial_y;

		this.save_y = this.y;

		this.width  = font.get_string_width(label);
		this.height = font.get_string_height(label);
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

	public String get_label() {
		return this.label;
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

	public boolean motion(int mx, int my) {
		if (mx >= get_x() && my >= get_save_y() && mx <= get_x() + get_width() && my <= get_save_y() + get_height()) {
			return true;
		}

		return false;
	}
	public void update(String label) {
		this.label = label;
	}

	public void update(int separate) {
		this.x = this.master.get_x() + separate;

		if (this.width > this.master.get_width()) {
			this.master.set_width(this.width + 50);
		}
	}

	public void render() {
		BopeDraw.draw_string(this.label, this.x, this.save_y, 255, 255, 255);
	}
}