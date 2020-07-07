package rina.turok.bope.bopemod.system.event;

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
 * 07/07/2020.
 *
 */
public class BopeEventRender0 extends BopeModule {
	float event_partial = 0.0f;

	public BopeEventRender0() {
		super(BopeCategory.BOPE_SYS, false);

		// Info.
		this.name        = "System Event Render 0";
		this.tag         = "SystemEventRender0";
		this.description = "Events render to HUD and stuff.";

		set_active(true);
	}

	@Override
	public void render(BopeEventRender event) {
		event_partial = event.get_partial_ticks();
	}

	@Override
	public double value_double_0() {
		return (float) event_partial;
	}
}