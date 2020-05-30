package rina.turok.bope.bopemod.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 06/05/20.
*
*/
public class BopeWatermark extends BopePinnable {
	public BopeWatermark() {
		super("Watermark", "Watermark", 1, 0, 0);
	}

	@Override
	public void render() {
		String line = "Welcome to B.O.P.E. " + Bope.g + "[" + Bope.r + Bope.get_version() + Bope.g + "]" + Bope.r;

		create_line(line, 1, 2);

		set_width(get(line, "width") + 2);
		set_height(get(line, "height") + 2);
	}
}