package rina.turok.bope.bopemod.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.label.BopeLabel;
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
	ArrayList<BopeLabel> labels_modules = new ArrayList<>();

	public BopeArrayList() {
		super("Array List", "BopeArrayList", 1, 0, 0);
	}

	@Override
	public void render() {
		for (BopeModule modules : Bope.get_module_manager().get_array_modules()) {
			if (modules.is_active()) {
				BopeLabel module_label = create_line(modules.get_name(), modules.get_tag());

				labels_modules.add(module_label);
			}
		}

		final Comparator<BopeLabel> comparator = (label_1, label_2) -> {
			final String label_1_for_compare = label_1.get_tag();
			final String label_2_for_compare = label_2.get_tag();

			return label_2_for_compare.compareTo(label_1_for_compare);
		};

		labels_modules.sort(comparator);

		for (BopeLabel labels : labels_modules) {
			labels.render();
		}
	}
}