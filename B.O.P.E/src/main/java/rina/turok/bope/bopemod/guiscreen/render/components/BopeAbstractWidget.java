package rina.turok.bope.bopemod.guiscreen.render.components;

/**
* @author Rina
*
* Created by Rina.
* 30/04/20.
*
*/
public abstract class BopeAbstractWidget {
	// Setters.
	public void set_x(int x) {}
	public void set_y(int y) {}

	public void set_width(int width) {}
	public void set_height(int height) {}

	// Getters.
	public int get_x() {
		return 0;
	}

	public int get_y() {
		return 0;
	}

	public int get_width() {
		return 0;
	}

	public int get_height() {
		return 0;
	}

	// Motion.
	public boolean motion_pass(int mx, int my) {
		return false;
	}

	// Can.
	public void does_can(boolean value) {}

	// Mouse click.
	public void mouse(int mx, int my, int mouse) {}

	// Release.
	public void release(int mx, int my, int mouse) {}

	// Render abstract.
	public void render(int master_y, int separe, int x, int y) {}
}