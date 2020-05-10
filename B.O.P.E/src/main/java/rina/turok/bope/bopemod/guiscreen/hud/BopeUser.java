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
		super("User", "BopeUser", 1, 0, 0);
	}

	@Override
	public void render() {
		String line = ChatFormatting.DARK_BLUE + mc.player.getName();

		create_line(line, 2, 2);

		this.set_width(this.get(line, "width"));
		this.set_height(this.get(line, "height"));
	}
}