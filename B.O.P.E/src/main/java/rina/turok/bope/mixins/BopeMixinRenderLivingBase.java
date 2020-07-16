package rina.turok.bope.mixins;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.Minecraft;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;

// Module.
import rina.turok.bope.bopemod.hacks.render.BopePlayerESP;

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
@Mixin(value = RenderLivingBase.class, priority = 998)
public class BopeMixinRenderLivingBase <T extends EntityLivingBase> extends BopeMixinRender<T> {
	public final Minecraft mc = Minecraft.getMinecraft();

	// Render.
	@Inject(method = "doRender", at = @At("HEAD"))
	private void doRender(T entity, double x, double y, double z, float yaw, float partial_ticks, CallbackInfo callback) {
		if (entity instanceof EntityPlayer && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPRange").get_value(1))) {
			if (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPRenderEntity").in("Chams") && Bope.get_module_manager().get_module_with_tag("PlayerESP").is_active()) {
				BopePlayerESP.distance_player = mc.player.getDistance(entity);

				GlStateManager.pushMatrix();

				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);

				glEnable(GL11.GL_POLYGON_OFFSET_FILL);

				glPolygonOffset(1.0f, -1100000.0f);

				GlStateManager.popMatrix();
			}
		}
	}
	
	// Last render.+
	@Inject(method = "doRender", at = @At("RETURN"))
	private void doRenderlast(T entity, double x, double y, double z, float yaw, float partial_ticks, CallbackInfo callback) {
		if (entity instanceof EntityPlayer && mc.player != null && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPDistanceRender").get_value(1) && mc.player.getDistance(entity) < (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPRange").get_value(1))) {
			if (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPRenderEntity").in("Chams") && Bope.get_module_manager().get_module_with_tag("PlayerESP").is_active()) {
				BopePlayerESP.distance_player = mc.player.getDistance(entity);

				GlStateManager.pushMatrix();

				glDisable(GL11.GL_POLYGON_OFFSET_FILL);
				glPolygonOffset(1.0f, 1100000.0f);
				glEnable(GL11.GL_TEXTURE_2D);

				GlStateManager.popMatrix();
			}
		}
	}
}