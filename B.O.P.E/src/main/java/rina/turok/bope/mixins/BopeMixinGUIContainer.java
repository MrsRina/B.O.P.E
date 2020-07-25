package rina.turok.bope.mixins;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.GL11;

import org.lwjgl.opengl.Display;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mixin;

// Events.
import rina.turok.bope.bopemod.events.BopeEventGUIScreen;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 24/07/20.
*
*/
@Mixin(value = GuiContainer.class, priority = 998)
public abstract class BopeMixinGUIContainer extends GuiScreen {
//  	@Inject(method = "drawScreen", at = @At("HEAD"), cancellable = true)
//  	private void drawScreen(int x, int y, float partial_ticks) {
//  		GL11.glPushMatrix();
//  		GL11.glEnable(GL11.GL_BLEND);
//  		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
//  		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//  		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
//  
//  		drawTexturedModalRect();
//  
//  		GL11.glPopMatrix();
//  	}
}