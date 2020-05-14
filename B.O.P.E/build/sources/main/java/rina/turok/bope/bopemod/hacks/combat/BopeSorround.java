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
	BopeSetting slider_mode     = create("Blocks", "SorroundBlocks", 3, 3, 4);
	BopeSetting placing_tick    = create("Placing Tick", "SorroundPlacingTick", 13, 1, 30);
	BopeSetting blocks_per_tick = create("Blocks Per Tick", "SorroundBlocksPerTick", 4, 1, 6);

	public BopeSorround() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Sorround"; 
		this.tag         = "Sorround";
		this.description = "A barrier into player with obsidian.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}
}