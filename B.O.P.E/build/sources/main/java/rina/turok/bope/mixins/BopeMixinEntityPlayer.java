package rina.turok.bope.mixins;

import net.minecraft.entity.player.EntityPlayer;
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
@Mixin(value = EntityPlayer.class)
public abstract class BopeMixinEntityPlayer extends BopeMixinEntity {}