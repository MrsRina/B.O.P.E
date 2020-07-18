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
		if (is_on_gui()) {
			background();
		}

		String ms   = mc.getCurrentServerData() != null ? Long.toString(Long.valueOf(mc.getCurrentServerData().pingToServer)) : "-1";
		String info = "MS " + ms;

		create_line(info, 1, 1);

		set_width(get(info, "width") + 1);
		set_height(get(info, "height") + 2);
	}
}