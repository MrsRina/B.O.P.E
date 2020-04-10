package rina.turok.bope.bopemod.hacks;

import rina.turok.bope.bopemod.hacks.BopeCategory.Category;
import rina.turok.bope.bopemod.events.BopeEventRender;
import rina.turok.bope.bopemod.BopeSaveModule;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeFinderModule extends BopeModule {
	BopeSaveModule module_info;

	public BopeFinderModule() {
		super(Category.BOPE_HIDDEN);

		Bope.get_module_manager().register_module(new BopeSaveModule (
			"B.O.P.E",
			"B.O.P.E",
			-1
		));

		set_module_info (
			"B.O.P.E",
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
	public void onWorldRender(BopeEventRender funtion_for_get_world_event_with_tressallator) {}
}