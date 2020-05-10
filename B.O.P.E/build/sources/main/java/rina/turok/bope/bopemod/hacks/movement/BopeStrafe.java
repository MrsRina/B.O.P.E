package rina.turok.bope.bopemod.hacks.movement;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

import java.util.*;

// Zero alpine.
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Events.
import rina.turok.bope.bopemod.events.BopeEventPacket;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
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
	int forward = 1;
	int tick;

	public BopeStrafe() {
		super(BopeCategory.BOPE_BETA);

		// Info.
		this.name        = "Strafe";
		this.tag         = "Strafe";
		this.description = "Strafe to movimentation.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void update() {
		if (mc.player != null) {}
	}
}