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
		this.name        = "§4 Strafe";
		this.tag         = "Strafe";
		this.description = "Strafe to movimentation.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	Listener<BopeEventMove> listener = new Listener<>(event -> {
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
		if (mc.player.onGround && ground.get_value(true)) {
			return;
		}

		// Verify potion.
		if (mc.player.isPotionActive(MobEffects.SPEED)) {
			int amplifier = mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();

			player_speed *= (1.0f + 0.2f * (amplifier + 1));
		} else if (speed.get_value(1) > 1) {
			// Speed.
			int amplifier = speed.get_value(1);

			player_speed *= (1.0f + 0.2f * (amplifier + 1));
		}

		// Start get the values.
		if (move_forward == 0.0f && move_strafe == 0.0f) {
			event.set_x(0.0d);
			event.set_z(0.0d);
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

			// Set event.
			event.set_x((move_forward * player_speed) * Math.cos(Math.toRadians((rotation_yaw + 90.0f))) + (move_strafe * player_speed) * Math.sin(Math.toRadians((rotation_yaw + 90.0f))));
			event.set_z((move_forward * player_speed) * Math.sin(Math.toRadians((rotation_yaw + 90.0f))) - (move_strafe * player_speed) * Math.cos(Math.toRadians((rotation_yaw + 90.0f))));
		}

		event.cancel();
	});
}