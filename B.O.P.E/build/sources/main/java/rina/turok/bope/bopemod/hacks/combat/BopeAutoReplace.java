package rina.turok.bope.bopemod.hacks.combat;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 13/05/20.
*
*/
public class BopeAutoReplace extends BopeModule {
	public BopeAutoReplace() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Auto Replace"; 
		this.tag         = "AutoReplace";
		this.description = "Automatically replace any stack.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}
}