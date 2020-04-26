package rina.turok.bope.bopemod.guiscreen;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.*;

// Guiscreen;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeFrame;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

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

	private int frame_y;

	private final Minecraft mc = Minecraft.getMinecraft();

	public BopeGUI() {
		this.frame   = new ArrayList<>();
		this.frame_y = 30;

		for (BopeModule modules : Bope.get_module_manager().get_array_modules()) {
			if (modules.get_category().is_hidden()) {
				continue;
			}

			BopeFrame frames = new BopeFrame(modules.get_category());

			this.frame_y += 15;

			this.frame.add(frames);
		}
	}

	@Override
	public void initGui() {}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution scale = new ScaledResolution(mc);

		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glLineWidth(1);

		GL11.glPushMatrix();
		GL11.glTranslated(scale.getScaledWidth(), scale.getScaledHeight(), 0);
		GL11.glScaled(0.5f, 0.5f, 0.5f);
		GL11.glPopMatrix();

		for (BopeFrame frames : this.frame) {
			frames.render();
		}

		BopeDraw.draw_rect(80, 90, 100, 200, 255, 255, 255);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		if (keyCode == Bope.BOPE_KEY_GUI_ESCAPE) {
			mc.displayGuiScreen(null);
		}
	}
}