package rina.turok.bope.bopemod.hacks.render;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

/**
* @author Rina
*
* Created by Rina.
* 24/05/2020.
*
*/
public class BopeNoHurtCam extends BopeModule {
	public BopeNoHurtCam() {
		super(BopeCategory.BOPE_RENDER, false);

		// Info.
		this.name        = "No Hurt Cam";
		this.tag         = "NoHurtCam";
		this.description = "Disable effect hurt of player.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}
}