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
* 07/05/20.
*
*/
public class BopeOverworldCoords extends BopePinnable {
	public BopeOverworldCoords() {
		super("Overworld Coords", "BopeOverworldCoords", 1, 0, 0);
	}

	@Override
	public void render() {
		String x = " " + Integer.toString((int) (mc.player.posX));
		String y = " " + Integer.toString((int) (mc.player.posY));
		String z = " " + Integer.toString((int) (mc.player.posZ));

		String line = ChatFormatting.DARK_BLUE + x + "x" + y + "y" + z + "z";

		create_line(line, 2, 2);

		this.set_width(this.get(line, "width"));
		this.set_height(this.get(line, "height"));
	}
}