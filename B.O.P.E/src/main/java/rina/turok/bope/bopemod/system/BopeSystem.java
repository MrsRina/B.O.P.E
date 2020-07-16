package rina.turok.bope.bopemod.system;

// Events.
import rina.turok.bope.bopemod.events.BopeEventRender;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Rina
 *
 * Created by Rina.
 * 13/07/2020.
 *
 */
public class BopeSystem extends BopeModule {
	int callback = 0;

	public BopeSystem() {
		super(BopeCategory.BOPE_SYS, false);

		// Info.
		this.name        = "System";
		this.tag         = "System";
		this.description = "Any HUD or update background is here.";

		set_active(true);
	}

	@Override
	public void update() {
		Bope.client_r = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
		Bope.client_g = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
		Bope.client_b = Bope.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
	}
}