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
* 11/05/20.
*
*/
public class BopeKillAura extends BopeModule {
	BopeSetting range = create("Range", "KillAuraRange", 5.0, 0.5, 6.0);
	BopeSetting speed = create("Speed", "KillAuraSpeed", 4.0, 0.1, 4.0);

	public BopeKillAura() {
		super(BopeCategory.BOPE_COMBAT);

		this.name        = "Kill Aura"; 
		this.tag         = "KillAura";
		this.description = "To able hit enemies in a range.";
	}
}