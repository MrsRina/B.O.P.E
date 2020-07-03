package rina.turok.bope.bopemod.hacks.misc;

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
* 17/05/2020.
*
*/
public class BopeNoEntityTrace extends BopeModule {
	public BopeNoEntityTrace() {
		super(BopeCategory.BOPE_MISC, false);

		// Info.
		this.name        = "No Entity Trace";
		this.tag         = "NoEntityTrace";
		this.description = "Is able to active mining while in entity trace mouse.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public boolean value_boolean_0() {
		return mc.playerController.isHittingBlock;
	}
}