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
	BopeSetting frame_view = create("info", "HUDStringsList", "Strings");

	BopeSetting strings_r = create("Color R", "HUDStringsColorR", 0, 0, 255);
	BopeSetting strings_g = create("Color G", "HUDStringsColorG", 0, 0, 255);
	BopeSetting strings_b = create("Color B", "HUDStringsColorB", 200, 0, 255);

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