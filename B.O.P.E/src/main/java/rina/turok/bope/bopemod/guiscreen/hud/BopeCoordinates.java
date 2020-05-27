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
public class BopeCoordinates extends BopePinnable {
	ChatFormatting dg = ChatFormatting.DARK_GRAY;
	ChatFormatting db = ChatFormatting.DARK_BLUE;
	ChatFormatting dr = ChatFormatting.DARK_RED;

	boolean state = true;

	public BopeCoordinates() {
		super("Coordinates", "Coordinates", 1, 0, 0);
	}

	@Override
	public void render() {
		int nl_r = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
		int nl_g = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
		int nl_b = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);

		String x = Bope.g + "[" + db + Integer.toString((int) (mc.player.posX)) + Bope.g + " " + Bope.r;
		String y = Bope.g + "," + db + Integer.toString((int) (mc.player.posY)) + Bope.g + " " + Bope.r;
		String z = Bope.g + "," + db + Integer.toString((int) (mc.player.posZ)) + Bope.g + "]" + Bope.r;

		String x_nether = Bope.g + "[" + dr + Integer.toString((int) (mc.player.posX * 0.125f)) + Bope.g + " " + Bope.r;
		String z_nether = Bope.g + "," + dr + Integer.toString((int) (mc.player.posZ * 0.125f)) + Bope.g + "]" + Bope.r;

		String line = "XYZ " + x + y + z + x_nether + z_nether;

		create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b);

		this.set_width(this.get(line, "width"));
		this.set_height(this.get(line, "height") + 2);
	}
}