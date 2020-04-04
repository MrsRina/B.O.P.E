package rina.turok.bope.mixins;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;

import io.netty.channel.ChannelHandlerContext;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import java.io.IOException;

import rina.turok.bope.bopemod.events.BopeEventPacket;
import rina.turok.bope.external.BopeNoPacketKick;
import rina.turok.bope.external.BopeEventBus;

//
// Author: Rina
// By Rina.
//
// I a module called NoPacketKick like a state and stance for this Mixin.
//

@Mixin(value = NetworkManager.class)
public class BopeMixinNetworkManager {
	// Receive packet.
	@Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
	public void channelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callback) {
		BopeEventPacket event_packet = new BopeEventPacket.ReceivePacket(packet);

		BopeEventBus.ZERO_ALPINE_EVENT_BUS.post(event_packet);

		if (event_packet.isCancelled()) {
			callback.cancel();
		}
	}

	// Send packet.
	@Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
	public void sendPacket(Packet<?> packet, CallbackInfo callback) {
		BopeEventPacket event_packet = new BopeEventPacket.SendPacket(packet);

		BopeEventBus.ZERO_ALPINE_EVENT_BUS.post(event_packet);

		if (event_packet.isCancelled()) {
			callback.cancel();
		}
	}

	// Exception packet.
	@Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
	public void exceptionCaught(ChannelHandlerContext exc, Throwable exc_, CallbackInfo callback) {
		if (exc_ instanceof Exception && BopeNoPacketKick.is_active()) {
			callback.cancel();
		}
	}
}