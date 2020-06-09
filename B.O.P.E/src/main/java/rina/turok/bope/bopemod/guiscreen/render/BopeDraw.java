package rina.turok.bope.bopemod.guiscreen.render;

import java.util.*;
import java.awt.*;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import org.lwjgl.opengl.GL11;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.BopeString;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

// Turok
import rina.turok.turok.draw.TurokRenderHelp;
import rina.turok.turok.task.TurokRect;
import rina.turok.turok.Turok;

/**
* @author Rina
*
* Created by Rina.
* 24/04/20.
*
*/
public class BopeDraw {
	private static FontRenderer font_renderer = Minecraft.getMinecraft().fontRenderer;
	private static FontRenderer custom_font   = new BopeString(true);

	private float size;

	public BopeDraw(float size) {
		this.size = size;
	}

	public static void draw_rect(int x, int y, int w, int h, int r, int g, int b, int a) {
		Gui.drawRect(x, y, w, h, new TurokColor(r, g, b, a).hex());
	}

	public static void draw_rect(int x, int y, int w, int h, int r, int g, int b, int a, int size, String type) {
		if (((boolean) Arrays.asList(type.split("-")).contains("up"))) {
			draw_rect(x, y, x + w, y + size, r, g, b, a);
		}

		if (((boolean) Arrays.asList(type.split("-")).contains("down"))) {
			draw_rect(x, y + h - size, x + w, y + h, r, g, b, a);
		}

		if (((boolean) Arrays.asList(type.split("-")).contains("left"))) {
			draw_rect(x, y, x + size, y + h, r, g, b, a);
		}

		if (((boolean) Arrays.asList(type.split("-")).contains("right"))) {
			draw_rect(x + w - size, y, x + w, y + h, r, g, b, a);
		}
	}

	public static void draw_rect(TurokRect rect, int r, int g, int b, int a) {
		Gui.drawRect(rect.get_x(), rect.get_y(), rect.get_screen_width(), rect.get_screen_height(), new TurokColor(r, g, b, a).hex());
	}

	public static void draw_string(String string, int x, int y, int r, int g, int b, boolean smoth) {
		if (smoth) {
			custom_font.drawStringWithShadow(string, x, y, new TurokColor(r, g, b).hex());
		} else {
			font_renderer.drawStringWithShadow(string, x, y, new TurokColor(r, g, b).hex());
		}
	}

	public static void draw_string(String string, int x, int y, int r, int g, int b, boolean shadow, boolean smoth) {
		if (smoth) {
			if (shadow) {
				custom_font.drawStringWithShadow(string, x, y, new TurokColor(r, g, b).hex());
			} else {
				custom_font.drawString(string, x, y, new TurokColor(r, g, b).hex());
			}
		} else {
			if (shadow) {
				font_renderer.drawStringWithShadow(string, x, y, new TurokColor(r, g, b).hex());
			} else {
				font_renderer.drawString(string, x, y, new TurokColor(r, g, b).hex());
			}
		}
	}

	public void draw_string_gl(String string, int x, int y, int r, int g, int b, int size) {
		Turok resize_gl = new Turok("Resize");

		resize_gl.resize(x, y, size);

		font_renderer.drawString(string, x, y, new TurokColor(r, g, b).hex());

		resize_gl.resize(x, y, size, "end");

		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);

		GlStateManager.enableBlend();

		GL11.glPopMatrix();

		TurokRenderHelp.release_gl();
	}

	public int get_string_width(String string, boolean smoth) {
		FontRenderer fontRenderer = font_renderer;

		if (smoth) {
			return (int) (custom_font.getStringWidth(string) * this.size);
		} else {
			return (int) (fontRenderer.getStringWidth(string) * this.size);
		}
	}

	public int get_string_height(String string, boolean smoth) {
		FontRenderer fontRenderer = font_renderer;
		FontRenderer customFont   = custom_font;

		if (smoth) {
			return (int) (customFont.FONT_HEIGHT * this.size);
		} else {
			return (int) (fontRenderer.FONT_HEIGHT * this.size);
		}
	}

	public static int get_width() {
		ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());

		return resolution.getScaledWidth();
	}

	public static int get_height() {
		ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());

		return resolution.getScaledHeight();
	}

	public static class TurokColor extends Color {
		public TurokColor(int r, int g, int b, int a) {
			super(r, g, b, a);
		}

		public TurokColor(int r, int g, int b) {
			super(r, g, b);
		}

		public int hex() {
			return getRGB();
		}
	}
}