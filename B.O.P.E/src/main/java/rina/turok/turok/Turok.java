package rina.turok.turok;

// Draw.
import rina.turok.turok.draw.TurokGL;

// Task.
import rina.turok.turok.task.TurokFont;

/**
 * @author Rina
 *
 * Created by Rina.
 * 27/04/20.
 *
 */
public class Turok {
	private String tag;

	private TurokFont font_manager;

	public Turok(String tag) {
		this.tag = tag;
	}

	public void resize(int x, int y, float size) {
		TurokGL.resize(x, y, size);
	}

	public void resize(int x, int y, float size, String tag) {
		TurokGL.resize(x, y, size, "end");
	}

	public TurokFont get_font_manager() {
		return this.font_manager;
	}
}