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
public class BopeNetherCoords extends BopePinnable {
	public BopeNetherCoords() {
		super("Nether Coords", "BopeNetherCoords", 1, 0, 0);
	}

	@Override
	public void render() {
		String x = " " + Integer.toString((int) (mc.player.posX * 0.125f));
		String y = " " + Integer.toString((int) (mc.player.posY));
		String z = " " + Integer.toString((int) (mc.player.posZ * 0.125f));

		String line = ChatFormatting.DARK_RED + x + "x" + y + "y" + z + "z";

		create_line(line, 2, 2);

		this.set_width(this.get(line, "width"));
		this.set_height(this.get(line, "height"));
	}
}