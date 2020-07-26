package rina.turok.bope.bopemod.hacks.misc;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Events.
import rina.turok.bope.bopemod.events.BopeEventPacket;
import rina.turok.bope.bopemod.events.BopeEventMove;

// Util.
import static rina.turok.bope.bopemod.util.BopeUtilMath.movement_speed;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Rina
 *
 * Created by Rina.
 * 26/07/2020.
 *
 **/
public class BopeSmallHand extends BopeModule {
	BopeSetting offset = create("Offset", "SmallHandOffset", 90, 0, 360);

	public BopeSmallHand() {
		super(BopeCategory.BOPE_MISC);

		// Info.
		this.name        = "Small Hand";
		this.tag         = "SmallHand";
		this.description = "Small hand in game.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void update() {
		mc.player.renderArmPitch = offset.get_value(1);
	}
}