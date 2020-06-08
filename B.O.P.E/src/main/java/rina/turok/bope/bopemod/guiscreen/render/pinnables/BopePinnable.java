package rina.turok.bope.bopemod.guiscreen.render.pinnables;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Mouse;

import java.util.*;

// Guiscreen;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Core.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.draw.TurokRenderHelp;

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

	private boolean state;
	private boolean move;

	public BopeDraw font;

	private int x;
	private int y;

	private int width;
	private int height;

	public int screen_width;
	public int screen_height;

	private int move_x;
	private int move_y;

	private int tolerance;

	private boolean dock            = true;
	public  boolean smoth           = false; // Bope.get_setting_manager().get_setting_with_tag("HUDSmothFont").get_value(true);
	public  boolean event_is_resize = false;

	public final Minecraft mc = Minecraft.getMinecraft();

	public boolean pass;

	public BopePinnable(String title, String tag, float font_, int x, int y) {
		this.title = title;
		this.tag   = tag;
		this.font  = new BopeDraw(font_);

		this.x = x;
		this.y = y;

		this.width  = 1;
		this.height = 10;

		this.move = false;

		this.screen_width  = (mc.displayWidth / 2);
		this.screen_height = (mc.displayHeight / 2);

		this.tolerance = this.x;
		this.pass      = false;
	}

	public void background() {
		create_rect(0, 0, this.get_width(), this.get_height(), 0, 0, 0, 60);
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

	public void set_x(int x, String mult) {
		if (mult.equals("+")) {
			this.x += x;
		} else if (mult.equals("-")) {
			this.x -= x;
		}
	}

	public void set_y(int y, String mult) {
		if (mult.equals("+")) {
			this.y += y;
		} else if (mult.equals("-")) {
			this.y -= y;
		}
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

	public void set_dock(boolean value) {
		this.dock = value;
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
		return this.font.get_string_height(this.title, this.smoth);
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

	public boolean get_dock() {
		return this.dock;
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

	public void update() {
		this.screen_width  = (mc.displayWidth / 2);
		this.screen_height = (mc.displayHeight / 2);
	}

	public void fix_screen() {
		if (get_x() <= 0) {
			set_dock(true);
			set_x(1);
		}

		if (get_x() + get_width() >= this.screen_width) {
			set_dock(false);
			set_x(this.screen_width - get_width() - 1);
		}

		if (get_y() <= 0) {
			set_y(1);
		}

		if (get_y() + get_height() >= this.screen_height) {
			set_y(this.screen_height - get_height() - 1);
		}

		if (get_x() % 2 != 0) {
			set_x(get_x() % 2, "+");
		}

		if (get_y() % 2 != 0) {
			set_y(get_y() % 2, "+");
		}
	}

	public void crush(int mx, int my) {
		set_x(mx - this.move_x);
		set_y(my - this.move_y);

		this.tolerance = get_x() + get_width() - this.screen_width;

		fix_screen();
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
		if (is_moving()) {
			crush(mx, my);

			event_is_resize = false;
		}

		if (is_active()) {
			render();

			GL11.glPushMatrix();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);

			GlStateManager.enableBlend();

			GL11.glPopMatrix();

			TurokRenderHelp.release_gl();

			if (motion(mx, my)) {
				BopeDraw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, 255, 255, 255, 20);
			} else if (this.pass) {
				BopeDraw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, 190, 190, 190, 20);
			}
		}
	}

	protected void create_line(String string, int pos_x, int pos_y) {
		// this.smoth = Bope.get_setting_manager().get_setting_with_tag("HUDSmothFont").get_value(true);
		this.smoth = false;

		int nl_r = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
		int nl_g = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
		int nl_b = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);

		boolean shadow = Bope.get_setting_manager().get_setting_with_tag("HUDStringsShadow").get_value(true);

		BopeDraw.draw_string(string, this.x + docking(pos_x, string), this.y + pos_y, nl_r, nl_g, nl_b, shadow, this.smoth);
	}

	protected void create_line(String string, int pos_x, int pos_y, int r, int g, int b) {
		// this.smoth = Bope.get_setting_manager().get_setting_with_tag("HUDSmothFont").get_value(true);
		this.smoth = false;

		boolean shadow = Bope.get_setting_manager().get_setting_with_tag("HUDStringsShadow").get_value(true);

		BopeDraw.draw_string(string, this.x + docking(pos_x, string), this.y + pos_y, r, g, b, shadow, this.smoth);
	}

	protected void create_rect(int pos_x, int pos_y, int width, int height, int r, int g, int b, int a) {
		BopeDraw.draw_rect(this.x + pos_x, this.y + pos_y, this.x + width, this.y + height, r, g, b, a);
	}

	protected int get(String string, String type) {
		int value_to_request = 0;

		if (type.equals("width")) {
			value_to_request = this.font.get_string_width(string, this.smoth);
		}

		if (type.equals("height")) {
			value_to_request = this.font.get_string_height(string, this.smoth);
		}

		return value_to_request;
	}

	public int docking(int position_x, String string) {
		if (get_dock()) {
			return position_x;
		} else {
			return (this.width - get(string, "width") - position_x); 
		}
	}

	public int docking(int position_x, int width) {
		if (get_dock()) {
			return position_x;
		} else {
			return (this.width - width - position_x);
		}
	}

	protected boolean is_on_gui() {
		return Bope.click_hud.on_gui;
	}
}