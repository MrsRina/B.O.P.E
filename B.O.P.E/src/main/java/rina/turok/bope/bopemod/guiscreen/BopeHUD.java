package rina.turok.bope.bopemod.guiscreen;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.*;
import java.io.*;

// Guiscreen;
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopeFrame;

// Managers.
import rina.turok.bope.bopemod.manager.BopeHUDManager;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 04/05/20.
*
*/
public class BopeHUD extends GuiScreen {
	private BopeFrame frame;

	private int frame_height;

	private final Minecraft mc = Minecraft.getMinecraft();

	public boolean on_gui;
	public boolean back;

	public int theme_arraylist_r = 0;
	public int theme_arraylist_g = 0;
	public int theme_arraylist_b = 0;

	public BopeHUD() {
		this.frame  = new BopeFrame("B.O.P.E HUD", "BopeHUD", 40, 40);
		this.back   = false;
		this.on_gui = false;
	}

	public BopeFrame get_frame_hud() {
		return this.frame;
	}

	@Override
	public boolean doesGuiPauseGame() {
		// For not stop game ahahahha.
		return false;
	}

	@Override
	public void initGui() {
		this.on_gui = true;
	}

	@Override
	public void onGuiClosed() {
		if (this.back) {
			Bope.get_module_manager().get_module_with_tag("GUI").set_active(true);
			Bope.get_module_manager().get_module_with_tag("HUD").set_active(false);
		} else {
			Bope.get_module_manager().get_module_with_tag("HUD").set_active(false);
			Bope.get_module_manager().get_module_with_tag("GUI").set_active(false);
		}

		this.on_gui = false;
	}

	@Override
	protected void mouseClicked(int mx, int my, int mouse) {
		this.frame.mouse(mx, my, mouse);

		if (mouse == 0) {
			if (this.frame.motion(mx, my) && this.frame.can()) {
				this.frame.set_move(true);

				this.frame.set_move_x(mx - this.frame.get_x());
				this.frame.set_move_y(my - this.frame.get_y());
			}
		}
	}

	@Override
	protected void mouseReleased(int mx, int my, int state) {
		this.frame.release(mx, my, state);

		this.frame.set_move(false);
	}

	@Override
	public void drawScreen(int mx, int my, float tick) {
		this.frame.render(mx, my, 2);
	}
}