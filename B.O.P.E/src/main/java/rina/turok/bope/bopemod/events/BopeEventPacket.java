package rina.turok.bope.bopemod.events;

import net.minecraft.network.Packet;

// External.
import rina.turok.bope.external.BopeEventCancellable;

/**
 * @author Rina
 *
 * Created by Rina.
 * 08/04/20.
 *
 **/
public class BopeEventPacket extends BopeEventCancellable {
	private final Packet packet;

	public BopeEventPacket(Packet packet) {
		super();

		this.packet = packet;
	}

	public Packet get_packet() {
		return this.packet;
	}

	public static class ReceivePacket extends BopeEventPacket {
		public ReceivePacket(Packet packet) {
			super(packet);
		}
	}

	public static class SendPacket extends BopeEventPacket {
		public SendPacket(Packet packet) {
			super(packet);
		}
	}
}