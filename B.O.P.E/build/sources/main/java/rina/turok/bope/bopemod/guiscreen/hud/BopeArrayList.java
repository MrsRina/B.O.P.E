package rina.turok.bope.bopemod.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.gui.ScaledResolution;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 07/05/20.
*
*/
public class BopeArrayList extends BopePinnable {
	ChatFormatting g = ChatFormatting.GRAY;
	ChatFormatting r = ChatFormatting.RESET;

	public BopeArrayList() {
		super("Array List", "BopeArrayList", 1, 0, 0);
	}

	@Override
	public void render() {
		ScaledResolution scaled_resolution = new ScaledResolution(mc);

		int position_update_y = 2;

		int nl_r = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayListColorR").get_value(1);
		int nl_g = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayListColorG").get_value(1);
		int nl_b = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayListColorB").get_value(1);

		for (BopeModule modules : Bope.get_module_manager().get_array_active_modules()) {
			if (modules.get_category().get_tag().equals("BopeGUI")) {
				continue;
			}

			String module_name = (
				modules.get_name() + modules.get_detail_option() == null ? "" :
				g + " [" + r + modules.get_detail_option() + g + "]" + r
			);

			create_line(modules.get_name(), 2, position_update_y, nl_r, nl_g, nl_b);

			position_update_y += get(module_name, "height") + 2;

			if (get(modules.get_name(), "width") > this.get_width()) {
				this.set_width(get(modules.get_name(), "width") + 5);
			} else {
				this.set_width(50);
			}

			this.set_height(position_update_y);
		}
	}
}