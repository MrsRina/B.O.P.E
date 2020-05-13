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

	@Override
	public void update() {
		float player_speed = 0.02873f;
		float move_forward = mc.player.movementInput.moveForward;
		float move_strafe  = mc.player.movementInput.moveStrafe;
		float rotation_pit = mc.player.rotationPitch;
		float rotation_yaw = mc.player.rotationYaw;

		// If player null.
		if (mc.player == null) {
			return;
		}

		// To ignore the events actuals if happening.
		if (mc.player.isSneaking() || mc.player.isOnLadder() || mc.player.isInWeb || mc.player.isInLava() || mc.player.isInWater() || mc.player.capabilities.isFlying) {
			return;
		}

		// Ground event return or no.
		if (!(ground.get_value(true))) {
			if (mc.player.onGround) {
				return;
			}
		}

		// Start get the values.
		if (move_forward == 0.0f && move_strafe == 0.0f) {
			mc.player.motionX = (0.0d);
			mc.player.motionZ = (0.0d);
		} else {
			// If move strafe is not 0.0f
			if (move_forward != 0.0f) {
				if (move_strafe > 0.0f) {
					// Update to - 45 or 45;
					rotation_yaw += ((move_forward > 0.0f) ? - 45 : 45);
				} else if (move_strafe < 0.0f) {
					// Update to 45 or - 45;
					rotation_yaw += ((move_forward > 0.0f) ? 45 : - 45);
				}

				// Set move forward to 0.0f;
				move_forward = 0.0f;

				// Change move forward.
				if (move_forward > 0.0f) {
					move_forward = 1.0f;
				} else if (move_forward < 0.0f) {
					move_forward = - 1.0f;
				}
			}

			mc.player.motionX = ((move_forward * player_speed) * Math.cos(Math.toRadians((rotation_yaw + 90.0f))) + (move_strafe * player_speed) * Math.sin(Math.toRadians((rotation_yaw + 90.0f))));
			mc.player.motionZ = ((move_forward * player_speed) * Math.sin(Math.toRadians((rotation_yaw + 90.0f))) - (move_strafe * player_speed) * Math.cos(Math.toRadians((rotation_yaw + 90.0f))));
		}
	}
}