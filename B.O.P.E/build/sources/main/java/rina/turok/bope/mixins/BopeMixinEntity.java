package rina.turok.bope.mixins;

import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;

// Events.
import rina.turok.bope.bopemod.events.BopeEventEntity;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 12/05/20.
*
*/
@Mixin(value = Entity.class)
public abstract class BopeMixinEntity {
	// Inject.
	@Redirect(method = "applyEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
	public void velocity(Entity entity, double x, double y, double z) {
		BopeEventEntity.BopeEventColision event = new BopeEventEntity.BopeEventColision(entity, x, y, z);

		Bope.ZERO_ALPINE_EVENT_BUS.post(event);

		if (event.isCancelled()) {
			return;
		}

		entity.motionX += x;
		entity.motionY += y;
		entity.motionZ += z;

		entity.isAirBorne = true;
	}	
}