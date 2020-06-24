package rina.turok.bope.bopemod.guiscreen.render.components.widgets;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.components.widgets.BopeCombobox;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeAbstractWidget;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeModuleButton;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeFrame;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Settings.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Core.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.draw.TurokRenderHelp;

/**
* @author Rina
*
* Created by Rina.
* 01/05/20.
*
*/
public class BopeLabel extends BopeAbstractWidget {
	private BopeFrame        frame;
	private BopeModuleButton master;
	private BopeSetting      setting;

	private String label_name;

	private GuiTextField entry;

	private int x;
	private int y;

	private int width;
	private int height;

	private int save_y;

	private boolean can;
	private boolean info;
	private boolean smoth = Bope.get_setting_manager().get_setting_with_tag("GUISmothFont").get_value(true);

	private BopeDraw font = new BopeDraw(1);

	private int border_size = 0;

	public final Minecraft mc = Minecraft.getMinecraft();

	public BopeLabel(BopeFrame frame, BopeModuleButton master, String tag, int update_postion) {
		this.frame   = frame;
		this.master  = master;
		this.setting = Bope.get_setting_manager().get_setting_with_tag(master.get_module(), tag);

		this.x = master.get_x();
		this.y = update_postion;

		this.save_y = this.y;

		this.entry = new GuiTextField(0, font.custom_font, this.x, this.save_y, this.width, this.height);

		this.entry.writeText(this.setting.get_value("true"));
		this.entry.setCursorPosition(0);
		this.entry.setMaxStringLength(24);

		this.width  = master.get_width();
		this.height = font.get_string_height(this.setting.get_name(), this.smoth);

		this.label_name = this.setting.get_name();

		if (this.setting.get_name().equalsIgnoreCase("info")) {
			this.info = true;
		}

		this.can  = true;
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

	@Override
	public boolean can() {
		return this.can;
	}

	@Override
	public void input(char char_, int key) {
		this.entry.textboxKeyTyped(char_, key);
	}

	@Override
	public void mouse(int mx, int my, int mouse) {
		this.entry.mouseClicked(mx, my, mouse);

		if (mouse == 0) {
			if (motion(mx, my) && this.master.is_open() && can()) {
				this.frame.does_can(false);
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

		if (this.info) {
			BopeDraw.draw_string(this.setting.get_value(zbob), this.x + 2, this.save_y, ns_r, ns_g, ns_b, this.smoth);
		} else {
			this.entry.width  = this.width;
			this.entry.height = this.height;

			this.entry.x = this.x;
			this.entry.y = this.save_y;

			this.setting.set_value(this.entry.getText());

			if (this.entry.isFocused()) {		
				this.entry.drawTextBox();
			} else {
				BopeDraw.draw_string(this.label_name, this.x + 2, this.save_y, ns_r, ns_g, ns_b, this.smoth);
			}
		}
	}
}