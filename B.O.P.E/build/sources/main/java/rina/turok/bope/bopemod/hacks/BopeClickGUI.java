package rina.turok.bope.bopemod.hacks;

// Hacks.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

public class BopeClickGUI extends BopeModule {
	public BopeClickGUI() {
		super(BopeCategory.BOPE_HIDDEN);

		// Info.
		this.name        = "B.O.P.E GUI";
		this.tag         = "GUI";
		this.description = "B.O.P.E GUI for enbable or disable modules.";

		release("B.O.P.E");

		set_bind(Bope.BOPE_KEY_GUI);
	}

	@Override
	public void update() {
		mc.displayGuiScreen(Bope.click_gui);
	}
}