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
		this.frame   = new ArrayList<>();
		this.frame_x = 10;

		this.event_start  = true;
		this.event_finish = false;

		for (BopeCategory categorys : BopeCategory.values()) {
			if (categorys.is_hidden()) {
				continue;
			}

			BopeFrame frames = new BopeFrame(categorys);

			frames.set_x(this.frame_x);

			this.frame.add(frames);

			this.frame_x += frames.get_width() + 5;

			this.current = frames;
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void onGuiClosed() {
		// Return to false for not problem with double click.
		Bope.get_module_manager().get_module_with_tag("GUI").set_active(false);
	}

	@Override
	protected void mouseClicked(int x, int y, int mouse) {
		for (BopeFrame frames : this.frame) {
			frames.mouse(x, y, mouse);

			if (mouse == 0) {
				if (frames.motion(x, y) && frames.can()) {
					this.current = frames;

					this.current.set_move(true);

					this.current.set_move_x(x - this.current.get_x());
					this.current.set_move_y(y - this.current.get_y());
				}
			}
		}
	}

	@Override
	protected void mouseReleased(int x, int y, int state) {
		for (BopeFrame frames : this.frame) {
			frames.mouse_release(x, y, state);
			frames.set_move(false);
		}

		set_current(this.current);
	}

	@Override
	public void drawScreen(int x, int y, float tick) {
		for (BopeFrame frames : this.frame) {
			frames.render(x, y);
		}
	}

	public void set_current(BopeFrame current) {
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
		return this.frame;
	}

	public BopeFrame get_frame_with_tag(String tag) {
		BopeFrame frame_requested = null;

		for (BopeFrame frames : get_array_frames()) {
			if (frames.get_tag().equals(tag)) {
				frame_requested = frames;
			}
		}

		return frame_requested;
	}
}