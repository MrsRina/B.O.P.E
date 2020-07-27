package rina.turok.bope.mixins;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;

import io.netty.channel.ChannelHandlerContext;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import java.io.IOException;

// Events.
import rina.turok.bope.bopemod.events.BopeEventPacket;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Rina
 *
 * Created by Rina.
 * 08/04/20.
 *
 */
@Mixin(value = NetworkManager.class)
public class BopeMixinNetworkManager {
	// Receive packet.
	@Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
	private void receive(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callback) {
		BopeEventPacket event_packet = new BopeEventPacket.ReceivePacket(packet);

		Bope.ZERO_ALPINE_EVENT_BUS.post(event_packet);

		if (event_packet.isCancelled()) {
			callback.cancel();
		}
	}

	// Send packet.
	@Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
	private void send(Packet<?> packet, CallbackInfo callback) {
		BopeEventPacket event_packet = new BopeEventPacket.SendPacket(packet);

		Bope.ZERO_ALPINE_EVENT_BUS.post(event_packet);

		if (event_packet.isCancelled()) {
			callback.cancel();
		}
	}
}