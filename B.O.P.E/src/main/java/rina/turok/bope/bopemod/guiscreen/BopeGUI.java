package rina.turok.bope.bopemod.guiscreen;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.*;

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
	private int frame_y;

	private final Minecraft mc = Minecraft.getMinecraft();

	public BopeGUI() {
		this.frame   = new ArrayList<>();
		this.frame_x = 10;
		this.frame_y = 30;

		for (BopeModule modules : Bope.get_module_manager().get_array_modules()) {
			if (modules.get_category().is_hidden()) {
				continue;
			}

			BopeFrame frames = new BopeFrame(modules.get_category());

			this.frame_x += frames.get_width() + 10;

			frames.set_x(this.frame_x);

			this.frame.add(frames);
		}
	}

	@Override
	public void initGui() {}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int x, int y, float tick) {	
		for (BopeFrame frames : this.frame) {
			frames.render();
		}
	}

	@Override
	protected void keyTyped(char char_, int key) {}
}