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
import rina.turok.bope.bopemod.guiscreen.render.components.BopeFrame;

// Hacks.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 24/04/20.
*
*/
public class BopeGUI extends GuiScreen {
	private ArrayList<BopeFrame> frame;

	private int frame_x;

	private BopeFrame current;

	private boolean event_start;
	private boolean event_finish;

	private final Minecraft mc = Minecraft.getMinecraft();

	public BopeGUI() {
		// First we start all variables.
		this.frame   = new ArrayList<>();
		this.frame_x = 10;

		this.event_start  = true;
		this.event_finish = false;

		// Verify the categorys in a list and replace with all frames.
		for (BopeCategory categorys : BopeCategory.values()) {
			if (categorys.is_hidden() || categorys.get_tag().equals("BopeHUD")) {
				continue;
			}

			// Create variable with frame.
			BopeFrame frames = new BopeFrame(categorys);

			// Set x to divide.
			frames.set_x(this.frame_x);

			// Add in list.
			this.frame.add(frames);

			// And replace with width more 5.
			this.frame_x += frames.get_width() + 5;

			// For just update a current.
			this.current = frames;
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		// For not stop game ahahahha.
		return false;
	}

	@Override
	public void onGuiClosed() {
		// Return to false for not problem with double click.
		Bope.get_module_manager().get_module_with_tag("GUI").set_active(false);
	}

	@Override
	protected void keyTyped(char char_, int key) {
		// If some module get bind.
		for (BopeFrame frames : this.frame) {
			frames.bind(char_, key);

			if (key == Bope.BOPE_KEY_GUI_ESCAPE && !frames.is_binding()) {
				mc.displayGuiScreen(null);
			}
		}
	}

	@Override
	protected void mouseClicked(int mx, int my, int mouse) {
		// Get a list with the frames and update the all mouse events.
		for (BopeFrame frames : this.frame) {
			frames.mouse(mx, my, mouse);

			// If left click.
			if (mouse == 0) {
				if (frames.motion(mx, my) && frames.can()) {
					// Just a conenction in module buttons with widgets.
					frames.does_button_for_do_widgets_can(false);

					// Place the current for the current actual frame.
					this.current = frames;

					// I set for start move.
					this.current.set_move(true);

					// Now update for move gui.
					this.current.set_move_x(mx - this.current.get_x());
					this.current.set_move_y(my - this.current.get_y());
				}
			}
		}
	}

	@Override
	protected void mouseReleased(int mx, int my, int state) {
		// For stop mouse events before.
		for (BopeFrame frames : this.frame) {
			frames.does_button_for_do_widgets_can(true);
			frames.mouse_release(mx, my, state);
			frames.set_move(false);
		}

		// For set current frame.
		set_current(this.current);
	}

	@Override
	public void drawScreen(int mx, int my, float tick) {
		// For draw in screen.
		for (BopeFrame frames : this.frame) {
			frames.render(mx, my);
		}
	}

	public void set_current(BopeFrame current) {
		/*
		 * I dont found a good thing to replace,
		 * So I used it like replace for focus
		 * the module frames.
		 */

		this.frame.remove(current);
		this.frame.add(current);
	}

	public BopeFrame get_current() {
		return this.current;
	}

	/**
	 * GUI manager.
	 * 
	 * @author Rina.
	 * Created by Rina.
	 *
	 * 29/04/2020.
	 *
	 **/

	public ArrayList<BopeFrame> get_array_frames() {
		// Get array frames.
		return this.frame;
	}

	public BopeFrame get_frame_with_tag(String tag) {
		// Get frame with tag requesting a frame null if not return null.

		BopeFrame frame_requested = null;

		for (BopeFrame frames : get_array_frames()) {
			if (frames.get_tag().equals(tag)) {
				frame_requested = frames;
			}
		}

		return frame_requested;
	}
}