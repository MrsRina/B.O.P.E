package rina.turok.bope.bopemod.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.gui.ScaledResolution;


import java.util.function.Function;
import java.util.stream.Collectors;
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
	ChatFormatting g = ChatFormatting.DARK_GRAY;
	ChatFormatting r = ChatFormatting.RESET;

	public BopeArrayList() {
		super("Array List", "ArrayList", 1, 0, 0);
	}

	@Override
	public void render() {
		ScaledResolution scaled_resolution = new ScaledResolution(mc);

		int position_update_y = 2;

		int nl_r = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayListColorR").get_value(1);
		int nl_g = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayListColorG").get_value(1);
		int nl_b = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayListColorB").get_value(1);

		List<BopeModule> pretty_modules = Bope.get_module_manager().get_array_active_modules().stream()
			.sorted(Comparator.comparing(modules -> get(modules.detail_option() == null ? modules.get_tag() : modules.get_tag() + r + " [" + g + modules.detail_option() + r + "]", "width")))
			.collect(Collectors.toList());

		for (BopeModule modules : pretty_modules) {
			if (modules.get_category().get_tag().equals("BopeGUI")) {
				continue;
			}

			String module_name = (
				modules.detail_option() == null ? modules.get_tag() :
				modules.get_tag() + r + " [" + g + modules.detail_option() + r + "]"
			);

			create_line(module_name, this.docking(2, module_name), position_update_y, nl_r, nl_g, nl_b);

			position_update_y += get(module_name, "height") + 2;

			if (get(module_name, "width") > this.get_width()) {
				this.set_width(get(module_name, "width") + 2);
			}

			this.set_height(position_update_y);
		}
	}
}