package rina.turok.bope.bopemod.hacks;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory.Category;

// Events.
import rina.turok.bope.bopemod.events.BopeEventRender;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeFinderModule extends BopeModule {
	public BopeFinderModule() {
		super("B.O.PE", Category.BOPE_HIDDEN);

		module_info (
			"B.O.P.E",
			"B.O.P.E official client."
		);
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

	@Override
	public void onUpdate() {}

	@Override
	public void onRender() {}

	@Override
	public void onWorldRender(BopeEventRender funtion_for_get_world_event_with_tessallator) {}
}