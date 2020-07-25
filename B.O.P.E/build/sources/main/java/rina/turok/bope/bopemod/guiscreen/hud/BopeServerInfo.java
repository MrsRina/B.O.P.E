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

		String ms   = Integer.toString(get_ping());
		String tps  = String.format("%.2f", Bope.get_event_handler().get_tick_rate());
		String info = "MS " + ms + " TPS " + tps;

		create_line(info, 1, 1);

		set_width(get(info, "width") + 1);
		set_height(get(info, "height") + 2);
	}

	public int get_ping() {
		int ping = -1;

		if (mc.player == null || mc.getConnection() == null || mc.getConnection().getPlayerInfo(mc.player.getName()) == null) {
			return -1;
		} else {
			ping = mc.getConnection().getPlayerInfo(mc.player.getName()).getResponseTime();
		}

		return ping;
	}
}