package rina.turok.bope.bopemod.events;

import net.minecraft.network.Packet;

import rina.turok.bope.bopemod.BopeEvent;

public class BopeEventPacket extends BopeEvent {
	private final Packet packet;

	public BopeEventPacket(Packet packet) {
		super();

		this.packet = packet;
	}

	public Packet get_packet() {
		return this.packet;
	}

	public static class Receive extends BopeEventPacket {
		public Receive(Packet packet) {
			super(packet);
		}
	}

	public static class Send extends BopeEventPacket {
		public Send(Packet packet) {
			super(packet);
		}
	}
}