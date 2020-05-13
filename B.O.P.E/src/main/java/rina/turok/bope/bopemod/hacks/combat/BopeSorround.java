package rina.turok.bope.bopemod.hacks.combat;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// External.
import rina.turok.bope.external.BopeEventCancellable;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 13/05/20.
*
*/
public class BopeSorround extends BopeModule {
	BopeSetting tick_time = create("Tick");

	public BopeSorround() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Sorround"; 
		this.tag         = "Sorround"
		this.description = "A barrier into player with obsidian.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}
}