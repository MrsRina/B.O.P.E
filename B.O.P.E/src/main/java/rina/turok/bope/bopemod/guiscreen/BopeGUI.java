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

	private final Minecraft mc = Minecraft.getMinecraft();

	public BopeGUI() {
		this.frame   = new ArrayList<>();
		this.frame_x = 10; 

		for (BopeCategory categorys : BopeCategory.values()) {
			if (categorys.is_hidden()) {
				continue;
			}

			BopeFrame frames = new BopeFrame(categorys);

			frames.set_x(this.frame_x);

			this.frame.add(frames);

			this.frame_x += frames.get_width() + 5;
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void mouseClicked(int x, int y, int mouse) {
		for (BopeFrame frames : this.frame) {
			frames.mouse(x, y, mouse);


			if (mouse == 0 && frames.can) {
				if (frames.motion("name", x, y)) {
					frames.set_move(true);

					frames.set_move_x(x - frames.get_x());
					frames.set_move_y(y - frames.get_y());
				}
			}
		}
	}

	@Override
	public void drawScreen(int x, int y, float tick) {
		for (BopeFrame frames : this.frame) {
			frames.render(x, y);
		}
	}
}