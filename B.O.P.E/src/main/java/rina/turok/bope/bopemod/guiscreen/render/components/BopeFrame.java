package rina.turok.bope.bopemod.guiscreen.render.components;

import java.util.*;

// Guiscreen.
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
public class BopeFrame {
	private BopeCategory category;

	private int width;
	private int height;

	private int x;
	private int y;

	public BopeFrame(BopeCategory category) {
		this.x = 10;
		this.y = 10;

		this.width  = 100;
		this.height = 20;

		this.category = category;		
	}

	public void set_width(int width) {
		this.width = width;
	}

	public void set_height(int height) {
		this.height = height;
	}

	public void set_x(int x) {
		this.x = x;
	}

	public void set_y(int y) {
		this.y = y;
	}

	public void render() {
		BopeDraw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, 255, 255, 255);
	}
}