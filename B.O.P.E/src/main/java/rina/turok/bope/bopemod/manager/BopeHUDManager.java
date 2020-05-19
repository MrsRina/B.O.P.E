package rina.turok.bope.bopemod.manager;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;
import rina.turok.bope.bopemod.guiscreen.hud.*;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 04/05/20.
*
*/
public class BopeHUDManager {
	private String tag;

	public static ArrayList<BopePinnable> array_hud = new ArrayList<>();

	public BopeHUDManager(String tag) {
		this.tag = tag;

		add_component_pinnable(new BopeWatermark());
		add_component_pinnable(new BopeArrayList());
		add_component_pinnable(new BopeNetherCoords());
		add_component_pinnable(new BopeOverworldCoords());
		add_component_pinnable(new BopeInventoryPreview());
		add_component_pinnable(new BopeArmorPreview());
		add_component_pinnable(new BopeUser());
		add_component_pinnable(new BopeTotemCount());
		add_component_pinnable(new BopeCrystalCount());
		add_component_pinnable(new BopeEXPCount());
		add_component_pinnable(new BopeGappleCount());

		array_hud.sort(Comparator.comparing(BopePinnable::get_title));
	}

	public void add_component_pinnable(BopePinnable module) {
		array_hud.add(module);
	}

	public ArrayList<BopePinnable> get_array_huds() {
		return array_hud;
	}

	public void render() {
		for (BopePinnable pinnables : get_array_huds()) {
			if (pinnables.is_active()) {
				pinnables.render();
			}
		}
	}

	public BopePinnable get_pinnable_with_tag(String tag) {
		BopePinnable pinnable_requested = null;

		for (BopePinnable pinnables : get_array_huds()) {
			if (pinnables.get_tag().equalsIgnoreCase(tag)) {
				pinnable_requested = pinnables;
			}
		}

		return pinnable_requested;
	}

	public String get_tag() {
		return this.tag;
	}
}