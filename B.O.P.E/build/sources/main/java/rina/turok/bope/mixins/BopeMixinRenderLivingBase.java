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

	public boolean friend = false;
	public boolean all    = false;

	// Render.
	@Inject(method = "doRender", at = @At("HEAD"))
	private void doRender(T entity, double x, double y, double z, float yaw, float partial_ticks, CallbackInfo callback) {
		if (Bope.get_module_manager().get_module_with_tag("PlayerESP").is_active() && mc.world != null) {
			if (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPMode").in("Friends")) {
				friend = true;
				all    = false;
			}

			if (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPMode").in("All")) {
				friend = false;
				all    = true;
			}

			if (entity instanceof EntityPlayer && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPDistanceToStopRender").get_value(1)) {
				EntityPlayer player = (EntityPlayer) entity; 

				if (friend) {
					if (Bope.get_friend_manager().is_friend(player.getName())) {
						actual();
					} else {
						callback.cancel();
					}
				}

				if (all) {
					actual();
				}
			}
		}
	}
	
	// Last render.+
	@Inject(method = "doRender", at = @At("RETURN"))
	private void doRenderlast(T entity, double x, double y, double z, float yaw, float partial_ticks, CallbackInfo callback) {
		if (Bope.get_module_manager().get_module_with_tag("PlayerESP").is_active() && mc.world != null) {
			if (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPMode").in("Friends")) {
				friend = true;
				all    = false;
			}

			if (Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPMode").in("All")) {
				friend = false;
				all    = true;
			}

			if (entity instanceof EntityPlayer && mc.player.getDistance(entity) > Bope.get_setting_manager().get_setting_with_tag("PlayerESP", "PlayerESPDistanceToStopRender").get_value(1)) {
				EntityPlayer player = (EntityPlayer) entity;

				if (friend) {
					if (Bope.get_friend_manager().is_friend(player.getName())) {
						last();
					} else {
						callback.cancel();
					}
				}

				if (all) {
					last();
				}
			}
		}
	}

	// Actual.
	public void actual() {
		GlStateManager.pushMatrix();

		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);

		glEnable(GL11.GL_POLYGON_OFFSET_FILL);
		glPolygonOffset(1.0f, -1100000.0f);

		GlStateManager.popMatrix();
	}


	// Last.
	public void last() {
		GlStateManager.pushMatrix();

		glDisable(GL11.GL_POLYGON_OFFSET_FILL);
		glPolygonOffset(1.0f, 1100000.0f);
		glEnable(GL11.GL_TEXTURE_2D);

		GlStateManager.popMatrix();
	}
}