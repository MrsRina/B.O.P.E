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
* 06/05/20.
*
*/
public class BopeUser extends BopePinnable {
	public BopeUser() {
		super("User", "User", 1, 0, 0);
	}

	@Override
	public void render() {
		if (is_on_gui()) {
			background();
		}

		String line = "Welcome " + mc.player.getName() + " :^)";

		create_line(line, 1, 2);

		set_width(get(line, "width") + 2);
		set_height(get(line, "height") + 2 + 2);
	}
}