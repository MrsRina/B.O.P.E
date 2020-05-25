package rina.turok.bope.bopemod.hacks.movement;

import net.minecraftforge.client.event.InputUpdateEvent;

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
* @author Rina
*
* Created by Rina.
* 15/05/20.
*
*/
public class BopeNoSlowDown extends BopeModule {
	BopeSetting mode = create("Mode", "NoSlowDown", "Packet", combobox("Packet"));

	@EventHandler
	private Listener<InputUpdateEvent> listener = new Listener<>(event -> {
		if (mc.player.isHandActive() && !mc.player.isRiding()) {
			event.getMovementInput().moveStrafe  *= 5;
			event.getMovementInput().moveForward *= 5;
		}
	});

	public BopeNoSlowDown() {
		super(BopeCategory.BOPE_MOVEMENT);

		// Info.
		this.name        = "No Slow Down";
		this.tag         = "NoSlowDown";
		this.description = "Is able to modify the event eat.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public String detail_option() {
		return mode.get_current_value();
	}
}