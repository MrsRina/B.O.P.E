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
* 27/05/20.
*
*/
public class BopeServerInfo extends BopePinnable {
	public BopeServerInfo() {
		super("Server Info", "ServerInfo", 1, 0, 0);
	}

	@Override
	public void render() {
		int nl_r = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
		int nl_g = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
		int nl_b = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);

		String ms   = mc.getCurrentServerData() != null ? Long.toString(Long.valueOf(mc.getCurrentServerData().pingToServer)) : "-1";
		String tps  = Integer.toString((int) Bope.get_event_handler().get_tick_rate());
		String info = "MS " + ms + " TPS " + tps;

		create_line(info, 1, 1, nl_r, nl_g, nl_b);

		this.set_width(get(info, "width") + 1);
		this.set_height(get(info, "height") + 1);
	}
}