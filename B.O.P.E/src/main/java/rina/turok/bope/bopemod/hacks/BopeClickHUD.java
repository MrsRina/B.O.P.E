package rina.turok.bope.bopemod.hacks;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Hacks.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Rina
 *
 * Created by Rina.
 * 05/05/20.
 *
 */
public class BopeClickHUD extends BopeModule {
	BopeSetting arraylist_r = create("Array Color R", "HUDArrayListColorR", 0, 0, 255);
	BopeSetting arraylist_g = create("Array Color G", "HUDArrayListColorG", 0, 0, 255);
	BopeSetting arraylist_b = create("Array Color B", "HUDArrayListColorB", 0, 0, 255);

	public BopeClickHUD() {
		super(BopeCategory.BOPE_GUI);

		// Info.
		this.name        = "HUD";
		this.tag         = "HUD";
		this.description = "B.O.P.E HUD for setting pinnables.";

		release("B.O.P.E");
	}

	@Override
	public void enable() {
		if (mc.world != null && mc.player != null) {
			Bope.get_module_manager().get_module_with_tag("GUI").set_active(false);
				
			Bope.click_hud.back = false;

			mc.displayGuiScreen(Bope.click_hud);
		}
	}
}