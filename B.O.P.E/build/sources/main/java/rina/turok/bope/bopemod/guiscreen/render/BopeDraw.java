package rina.turok.bope.bopemod.guiscreen.render;

import java.awt.*;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

// Guiscreens.


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
public class BopeDraw {
	public static FontRenderer font_renderer = Minecraft.getMinecraft().fontRenderer;

	public static void draw_rect(int x, int y, int w, int h, int r, int g, int b) {
		Gui.drawRect(x, y, w, h, new TurokColor(r, g, b).hex());
	}

	public static void draw_string(String string, int x, int y, int r, int g, int b) {
		font_renderer.drawString(string, x, y, new TurokColor(r, g, b).hex());
	}

	public static int get_string_width(String string) {
		return font_renderer.getStringWidth(string);
	}

	public static class TurokColor extends Color {
		public TurokColor(int r, int g, int b) {
			super(r, g, b);
		}
	
		public int hex() {
			return convert_to_hex(getRed(), getGreen(), getBlue());
		}
	
		public static int convert_to_hex(int r, int g, int b) {
			return (r << 16 | g << 8 | b);
		}
	}
}