package rina.turok.bope.bopemod.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.gui.GuiIngame;

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
		if (is_on_gui()) {
			background();
		}

		String x = Bope.g + "["  + db + String.format("%.2f", (double) (mc.player.posX)) + Bope.g + Bope.r;
		String y = Bope.g + ", " + db + String.format("%.2f", (double) (mc.player.posY)) + Bope.g + Bope.r;
		String z = Bope.g + ", " + db + String.format("%.2f", (double) (mc.player.posZ)) + Bope.g + "]" + Bope.r;

		String x_nether = Bope.g + "["  + dr + String.format("%.2f", (Double) (mc.player.posX * 0.125f)) + Bope.g + Bope.r;
		String z_nether = Bope.g + ", " + dr + String.format("%.2f", (Double) (mc.player.posZ * 0.125f)) + Bope.g + "]" + Bope.r;

		String line = "XYZ " + x + y + z + x_nether + z_nether;

		create_line(line, 1, 2);

		boolean in_gui = mc.ingameGUI.getChatGUI().getChatOpen();

		if (in_gui && (get_y() + get_height()) >= (BopeDraw.get_height() - get_height() - 1)) {
			int comparator = BopeDraw.get_height() - get_height() - 17;

			set_y(comparator);

			state = true;
		}

		if (!in_gui && state) {
			set_y(BopeDraw.get_height() - get_height() - 1);

			state = false;
		}

		set_width(get(line, "width"));
		set_height(get(line, "height") + 2 + 2);
	}
}