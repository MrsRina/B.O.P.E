package rina.turok.bope.bopemod.hacks.player;

import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.entity.Entity;

import org.lwjgl.input.Keyboard;

// Zero alpine manager.
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

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
 * 08/04/20.
 *
 **/
public class BopeFreecam extends BopeModule {
	BopeSetting speed_moveme = create("Speed Movement", "FreecamSpeedMovement", 1.0, 1.0, 4.0);
	BopeSetting speed_updown = create("Speed Up/Down", "FreecamSpeedUpDown", 0.5, 0.0, 1.0);

	double x;
	double y;
	double z;

	float pitch;
	float yaw;

	EntityOtherPlayerMP soul;

	Entity riding_entity;

	boolean is_riding;

	public BopeFreecam() {
		super(BopeCategory.BOPE_PLAYER);

		// Info.
		this.name        = "Freecam";
		this.tag         = "Freecam";
		this.description = "Project Astral in Minecraft, a thing really insane.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@EventHandler
	Listener<BopeEventMove> listener_move = new Listener<>(event -> {
		mc.player.noClip = true;
	});

	@EventHandler
	Listener<PlayerSPPushOutOfBlocksEvent> listener_push = new Listener<>(event -> {
		event.setCanceled(true);
	});

	@EventHandler
	Listener<BopeEventPacket.SendPacket> listener_packet = new Listener<>(event -> {
		if (event.get_packet() instanceof CPacketPlayer || event.get_packet() instanceof CPacketInput) {
			event.cancel();
		}
	});

	@Override
	public void enable() {
		if (mc.player != null && mc.world != null) {
			is_riding = mc.player.getRidingEntity() != null;

			if (mc.player.getRidingEntity() == null) {
				x = mc.player.posX;
				y = mc.player.posY;
				z = mc.player.posZ;
			} else {
				riding_entity = mc.player.getRidingEntity();

				mc.player.dismountRidingEntity();
			}

			pitch = mc.player.rotationPitch;
			yaw   = mc.player.rotationYaw;

			soul = new EntityOtherPlayerMP(mc.world, mc.getSession().getProfile());

			soul.copyLocationAndAnglesFrom(mc.player);

			soul.rotationYawHead = mc.player.rotationYawHead;

			mc.world.addEntityToWorld(-100, soul);

			mc.player.noClip = true;
		}
	}

	@Override
	public void disable() {
		if (mc.player != null && mc.world != null) {
			mc.player.setPositionAndRotation(x, y, z, yaw, pitch);

			mc.world.removeEntityFromWorld(-100);

			// Remove soul power;
			soul  = null;
			x     = 0;
			y     = 0;
			z     = 0;
			yaw   = 0;
			pitch = 0;

			mc.player.motionX = mc.player.motionY = mc.player.motionZ = 0;

			if (is_riding) {
				mc.player.startRiding(riding_entity, true);
			}
		}
	}

	@Override
	public void update() {
		if (mc.player != null && mc.world != null) {
			mc.player.noClip                = true;

			mc.player.setVelocity(0, 0, 0);

			mc.player.renderArmPitch     = 5000;
			mc.player.jumpMovementFactor = 0.5f;

			final double[] static_mov = movement_speed(speed_moveme.get_value(1.0d) / 2);

			if (mc.player.movementInput.moveStrafe != 0 || mc.player.movementInput.moveForward != 0) {
				mc.player.motionX = static_mov[0];
				mc.player.motionZ = static_mov[1];
			} else {
				mc.player.motionX = 0;
				mc.player.motionZ = 0;
			}

			mc.player.setSprinting(false);

			if (mc.gameSettings.keyBindJump.isKeyDown()) {
				mc.player.motionY += speed_updown.get_value(1.0d);
			}

			if (mc.gameSettings.keyBindSneak.isKeyDown()) {
				mc.player.motionY -= speed_updown.get_value(1.0d);
			}
		}
	}
}
