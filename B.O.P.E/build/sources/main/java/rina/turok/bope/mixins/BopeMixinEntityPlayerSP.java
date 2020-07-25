package rina.turok.bope.mixins;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;

// Events.
import rina.turok.bope.bopemod.events.BopeEventMove;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 12/05/20.
*
*/
@Mixin(value = EntityPlayerSP.class)
public abstract class BopeMixinEntityPlayerSP extends Entity {
	public BopeMixinEntityPlayerSP(World world) {
		super(world);
	}

	private double motion_x;
	private double motion_y;
	private double motion_z;

	// Move event.
	@Inject(method = "move(Lnet/minecraft/entity/MoverType;DDD)V", at = @At("HEAD"))
	private void move(MoverType type, double x, double y, double z, CallbackInfo info) {
		BopeEventMove event = new BopeEventMove(x, y, z);

		Bope.ZERO_ALPINE_EVENT_BUS.post(event);
	}
}