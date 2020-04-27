package rina.turok.turok;

// Draw.
import rina.turok.turok.draw.TurokGL;

/**
* @author Rina
*
* Created by Rina.
* 27/04/20.
*
*/
public class Turok {
	private String tag;

	public Turok(String tag) {
		this.tag = tag;
	}

	public void resize(int x, int y, float size) {
		TurokGL.resize(x, y, size);
	}

	public void resize(int x, int y, float size, String tag) {
		TurokGL.resize(x, y, size, "end");
	}
}