package rina.turok.bope.bopemod.hacks;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

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
		super(BopeCategory.BOPE_HIDDEN);
		
		// Info.
		this.name        = "B.O.P.E";
		this.tag         = "B.O.P.E";
		this.description = "A client no?";

		// Release or launch the module. release(String tag); -> You can place what you want in tag.
		release("B.O.P.E - BOPE - B.O.P.E");
	}

	@Override
	public void enable() {}

	@Override
	public void disable() {}

	@Override
	public void update() {}

	@Override
	public void render() {}

	@Override
	public void render(BopeEventRender funtion_for_get_world_event_with_tessallator) {}
}