package rina.turok.bope.bopemod.hacks.movement;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.init.MobEffects;

import java.util.*;

// Zero alpine.
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Events.
import rina.turok.bope.bopemod.events.BopeEventMove;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Modify: Rina.
 *
 * Created by Rina.
 * 09/05/20.
 *
 * I used like reference the strafe from Seppuku.
 * It a better strafe. 
 *
 */
public class BopeStrafe extends BopeModule {
	BopeSetting speed  = create("Speed", "StrafeSpeed", 1, 1, 6);
	BopeSetting ground = create("Event Ground", "StrafeEventGround", true);

	public BopeStrafe() {
		super(BopeCategory.BOPE_MOVEMENT);

		// Info.
		this.name        = "Strafe";
		this.tag         = "Strafe";
		this.description = "Strafe to movimentation.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}
}