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
	public BopeArrayList() {
		super("Array List", "ArrayList", 1, 0, 0);
	}

	@Override
	public void render() {
		ScaledResolution scaled_resolution = new ScaledResolution(mc);

		int position_update_y = 2;

		List<BopeModule> pretty_modules = Bope.get_module_manager().get_array_active_modules().stream()
			.filter(module -> module.to_show())
			.sorted(Comparator.comparing(modules -> get(modules.detail_option() == null ? modules.get_tag() : modules.get_tag() + Bope.g + " [" + Bope.r + modules.detail_option() + Bope.g + "]" + Bope.r, "width")))
			.collect(Collectors.toList());

		for (BopeModule modules : pretty_modules) {
			if (modules.get_category().get_tag().equals("BopeGUI")) {
				continue;
			}

			String module_name = (
				modules.detail_option() == null ? modules.get_tag() :
				modules.get_tag() + Bope.g + " [" + Bope.r + modules.detail_option() + Bope.g + "]" + Bope.r
			);

			create_line(module_name, 1, position_update_y);

			position_update_y += get(module_name, "height") + 2;

			if (get(module_name, "width") >= get_width()) {
				set_width(get(module_name, "width") + 2);
			}

			set_height(position_update_y);
		}
	}
}