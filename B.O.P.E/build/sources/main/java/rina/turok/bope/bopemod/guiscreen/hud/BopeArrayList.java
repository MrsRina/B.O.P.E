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
		if (is_on_gui()) {
			background();
		}

		int position_update_y = 2;

		Comparator<BopeModule> comparator = (first, second) -> {
			String first_name  = first.get_tag() + (first.detail_option() == null ? "" : Bope.g + " [" + Bope.r + first.detail_option() + Bope.g + "]" + Bope.r);
			String second_name = second.get_tag() + (second.detail_option() == null ? "" : Bope.g + " [" + Bope.r + second.detail_option() + Bope.g + "]" + Bope.r);
			
			float diff = get(second_name, "width") - get(first_name, "width");

			if (get_dock_y()) {
				return diff != 0 ? (int) diff : second_name.compareTo(first_name);
			} else {
				return (int) diff;
			}
		};

		List<BopeModule> pretty_modules = Bope.get_module_manager().get_array_modules().stream()
			.filter(module -> module.is_active())
			.filter(module -> module.to_show())
			.sorted(comparator)
			.collect(Collectors.toList());

		for (BopeModule modules : pretty_modules) {
			if (modules.get_category().get_tag().equals("BopeGUI")) {
				continue;
			}

			String module_name = (
				modules.get_tag() + (modules.detail_option() == null ? "" : Bope.g + " [" + Bope.r + modules.detail_option() + Bope.g + "]" + Bope.r)
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