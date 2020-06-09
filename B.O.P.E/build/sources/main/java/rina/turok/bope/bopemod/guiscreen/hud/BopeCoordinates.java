package rina.turok.bope.bopemod.guiscreen.hud;

import net.minecraft.client.gui.GuiIngame;
import com.mojang.realmsclient.gui.ChatFormatting;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 07/05/20.
*
*/
public class BopeCoordinates extends BopePinnable {
	ChatFormatting db = Bope.r;
	ChatFormatting dr = Bope.r;

	boolean state = false;

	public BopeCoordinates() {
		super("Coordinates", "Coordinates", 1, 0, 0);
	}

	@Override
	public void render() {
		String x = Bope.g + "["  + db + Integer.toString((int) (mc.player.posX)) + Bope.g + Bope.r;
		String y = Bope.g + ", " + db + Integer.toString((int) (mc.player.posY)) + Bope.g + Bope.r;
		String z = Bope.g + ", " + db + Integer.toString((int) (mc.player.posZ)) + Bope.g + "]" + Bope.r;

		String x_nether = Bope.g + "["  + dr + Integer.toString((int) (mc.player.posX * 0.125f)) + Bope.g + Bope.r;
		String z_nether = Bope.g + ", " + dr + Integer.toString((int) (mc.player.posZ * 0.125f)) + Bope.g + "]" + Bope.r;

		String line = "XYZ " + x + y + z + x_nether + z_nether;

		create_line(line, 1, 2);

		boolean in_gui = mc.ingameGUI.getChatGUI().getChatOpen();

		if (in_gui && (get_y()) == (BopeDraw.get_height() - get_height() - 1)) {
			set_y(10, "-");

			state = true;
		}

		if (!in_gui && state) {
			set_y(BopeDraw.get_height() - get_height() - 1);

			state = false;
		}

		set_width(get(line, "width"));
		set_height(get(line, "height") + 2);
	}
}