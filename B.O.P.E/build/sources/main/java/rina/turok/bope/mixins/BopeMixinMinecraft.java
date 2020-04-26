package rina.turok.bope.mixins;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.crash.CrashReport;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

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
@Mixin(Minecraft.class)
public class BopeMixinMinecraft {
	@Inject(method = "shutdown", at = @At("HEAD"))
	public void shutdown(CallbackInfo info) {
		Bope.send_client_log("The client were saved.");

		Bope.get_config_manager().save();
	}

	@Redirect(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayCrashReport(Lnet/minecraft/crash/CrashReport;)V"))
	public void crash(Minecraft minecraft, CrashReport crashReport) {
		Bope.send_client_log("The client were saved before crash.");

		Bope.get_config_manager().save();
	}
}