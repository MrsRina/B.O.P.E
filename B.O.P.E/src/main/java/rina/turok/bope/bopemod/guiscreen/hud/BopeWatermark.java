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
		super("Watermark", "BopeWatermark", 1, 0, 0);
	}

	@Override
	public void render() {
		String line = ChatFormatting.DARK_GRAY + "Welcome to " + ChatFormatting.DARK_BLUE + "B.O.P.E. " + ChatFormatting.DARK_GRAY + "[" + Bope.get_version() + "]";
	
		create_line(line, 2, 2);

		this.set_width(this.get(line, "width") + 10);
		this.set_height(this.get(line, "height") + 2);
	}
}