package rina.turok.bope.bopemod.hacks.render;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 22/05/2020.
*
*/
public class BopeNameTag extends BopeModule {
	public BopeNameTag() {
		super(BopeCategory.BOPE_RENDER);

		// Info.
		this.name        = "Name Tag";
		this.tag         = "NameTag";
		this.description = "For see the items using of others players.";

		// Release.
		release("B.O.P.E - module - B.O.P.E"); 
	}
}