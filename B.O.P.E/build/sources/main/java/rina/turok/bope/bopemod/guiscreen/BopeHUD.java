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
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnableButton;
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopeFrame;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeClickGUI;

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

	public BopeHUD() {
		// First I created a simple HUD frame.
		this.frame  = new BopeFrame("B.O.P.E HUD", "BopeHUD", 40, 40);
		this.back   = false;
		this.on_gui = false;
	}

	// For get the master frame used and created.
	public BopeFrame get_frame_hud() {
		return this.frame;
	}

	@Override
	public boolean doesGuiPauseGame() {
		// For not stop game ahahahha.
		return false;
	}

	// Just a sync a event.
	@Override
	public void initGui() {
		this.on_gui = true;

		// Rinnaaaaaaaaaaaa noooo!. yes/ ^^
		BopeFrame.nc_r = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameR").get_value(1);
		BopeFrame.nc_g = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameG").get_value(1);
		BopeFrame.nc_b = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameB").get_value(1);
		
		BopeFrame.bg_r = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameR").get_value(1);
		BopeFrame.bg_g = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameG").get_value(1);
		BopeFrame.bg_b = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameB").get_value(1);
		BopeFrame.bg_a = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameA").get_value(1);
		
		BopeFrame.bd_r = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameR").get_value(1);
		BopeFrame.bd_g = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameG").get_value(1);
		BopeFrame.bd_b = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameB").get_value(1);
		BopeFrame.bd_a = 0;
		
		BopeFrame.bdw_r = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetR").get_value(1);
		BopeFrame.bdw_g = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetG").get_value(1);
		BopeFrame.bdw_b = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetB").get_value(1);
		BopeFrame.bdw_a = 255;

		// A?
		BopePinnableButton.nc_r = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameWidgetR").get_value(1);
		BopePinnableButton.nc_g = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameWidgetG").get_value(1);
		BopePinnableButton.nc_b = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameWidgetB").get_value(1);
	
		BopePinnableButton.bg_r = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetR").get_value(1);
		BopePinnableButton.bg_g = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetG").get_value(1);
		BopePinnableButton.bg_b = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetB").get_value(1);
		BopePinnableButton.bg_a = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetA").get_value(1);
	
		BopePinnableButton.bd_r = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetR").get_value(1);
		BopePinnableButton.bd_g = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetG").get_value(1);
		BopePinnableButton.bd_b = Bope.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetB").get_value(1);
	}

	// When close save the client HUD.
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

		// For save the client with the HUD.
		Bope.get_config_manager().save_client();
	}

	@Override
	protected void mouseClicked(int mx, int my, int mouse) {
		// A event to mouse click.		
		this.frame.mouse(mx, my, mouse);

		// Just if click move.
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
		// Release mouse.
		this.frame.release(mx, my, state);

		this.frame.set_move(false);
	}

	@Override
	public void drawScreen(int mx, int my, float tick) {
		// For draw backscreen.
		this.drawDefaultBackground();

		// To render frames.
		this.frame.render(mx, my, 2);
	}
}