package rina.turok.bope.mixins;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;

import com.google.common.base.Predicate;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;

import java.util.*;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 12/05/20.
*
* - It were referenced with KAMI mixins, 086 thanks for help me.
*
*/
@Mixin(value = EntityRenderer.class)
public class BopeMixinEntityRender {
	// Entity Trace.
	@Redirect(method = "getMouseOver", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
	private List<Entity> getEntitiesInAABBexcluding(WorldClient world_client, Entity entity, AxisAlignedBB bouding_box, Predicate predicate) {
		BopeModule module_requested = Bope.get_module_manager().get_module_with_tag("NoEntityTrace");

		if (module_requested.is_active() && module_requested.boolean_state()) {
			return new ArrayList<>();
		} else {
			return world_client.getEntitiesInAABBexcluding(entity, bouding_box, predicate);
		}
	}

	// Camera effect.
	@Inject(method = "hurtCameraEffect", at = @At("HEAD"), cancellable = true)
	private void hurtCameraEffect(float ticks, CallbackInfo info) {
		if (Bope.get_module_manager().get_module_with_tag("NoHurtCam").is_active()) {
			info.cancel();
		}
	}
}