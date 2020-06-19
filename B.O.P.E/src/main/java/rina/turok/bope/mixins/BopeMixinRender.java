package rina.turok.bope.mixins;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 23/04/20.
*
* - It were referenced with KAMI mixins, 086 thanks for help me.
*
*/
@Mixin(value = Render.class)
public abstract class BopeMixinRender <T extends Entity> {
	// Renmove name.
	@Inject(method = "renderLivingLabel", at = @At("HEAD"), cancellable = true)
	private void renderLivingLabel(T entity_generic, String string, double x, double y, double z, int max, CallbackInfo callback) {
		//if (Bope.get_module_manager().get_module_with_tag("NameTags").is_active() && entity_generic instanceof EntityPlayer) {
		//	callback.cancel();
		//}
	}
}