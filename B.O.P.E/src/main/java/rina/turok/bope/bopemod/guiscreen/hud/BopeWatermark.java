package rina.turok.bope.bopemod.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.label.BopeLabel;
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
	BopeLabel welcome = create_line("");

	public BopeWatermark() {
		super("BOPE Watermark", "BopeWatermark", 1, 20, 20);
	}

	@Override
	public void render() {
		welcome.update(ChatFormatting.DARK_GRAY + "Welcome to " + ChatFormatting.DARK_BLUE + "B.O.P.E. " + ChatFormatting.DARK_GRAY + "[" + Bope.get_version() + "]");

		this.draw();
	}
}