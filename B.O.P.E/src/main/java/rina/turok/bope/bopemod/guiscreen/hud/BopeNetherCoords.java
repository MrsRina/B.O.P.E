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
* 07/05/20.
*
*/
public class BopeNetherCoords extends BopePinnable {
	BopeLabel coords = create_line("", "coords");

	public BopeNetherCoords() {
		super("Nether Coords", "BopeNetherCoords", 1, 0, 0);
	}

	@Override
	public void render() {
		String x = " " + Integer.toString((int) (mc.player.posX * 0.125f));
		String y = " " + Integer.toString((int) (mc.player.posY));
		String z = " " + Integer.toString((int) (mc.player.posZ * 0.125f));

		coords.update(ChatFormatting.DARK_RED + x + "x" + y + "y" + z + "z");

		this.draw();
	}
}