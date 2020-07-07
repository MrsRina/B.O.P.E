package rina.turok.bope.bopemod.hacks.render;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

/**
* @author Rina
*
* Created by Rina.
* 06/07/2020.
*
*/
public class BopeFreeCameraOrient extends BopeModule {
	public BopeFreeCameraOrient() {
		super(BopeCategory.BOPE_RENDER, false);

		// Info.
		this.name        = "Free Camera Orient";
		this.tag         = "FreeCameraOrient";
		this.description = "Bypass camera.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}
}