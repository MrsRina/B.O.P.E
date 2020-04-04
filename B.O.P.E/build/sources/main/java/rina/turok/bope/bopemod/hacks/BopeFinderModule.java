package rina.turok.bope.bopemod.hacks;

import rina.turok.bope.bopemod.events.BopeEventRender;
import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.bopemod.BopeModule;

public class BopeFinderModule extends BopeModule {
	public BopeFinderModule() {
		super(
			"B.O.P.E",
			"B.O.P.E",
			"B.O.P.E finder module.",
			- 1,
			BopeCategory.Category.BOPE_HIDDEN
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
	public void onWorldRender(BopeEventRender funtion_for_get_world_event_with_tressallator) {}
}