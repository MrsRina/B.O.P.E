package rina.turok.bope.external;

import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.util.math.MathHelper;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

import java.util.EventListener;
import java.util.Arrays;

import rina.turok.bope.bopemod.events.BopeEventPacket;
import rina.turok.bope.Bope;

//
// Author: Rina.
// A event handler for events packet into B.O.P.E.
// This update the ticks on EventListener.
// I used like reference the LagCompressor from KAMI.
//

public class BopeEventHandler implements EventListener {
	public static BopeEventHandler INSTANCE;

	static final float[] tick_rates = new float[20];

	private long last_update_tick;
	private int next_index = 0;

	@EventHandler
	private Listener<BopeEventPacket.ReceivePacket> receive_event_packet = new Listener<>(event -> {
		if (event.get_packet() instanceof SPacketTimeUpdate) {
			INSTANCE.update_time();
		}
	});

	public BopeEventHandler() {
		Bope.ZERO_ALPINE_EVENT_BUS.subscribe(this);

		reset_tick();
	}

	public float get_tick_rate() {
		float num_ticks = 0.0f;
		float sum_ticks = 0.0f;

		for (float ticks : this.tick_rates) {
			if (ticks > 0.0f) {
				sum_ticks += ticks;
				num_ticks += 1.0f; 
			}
		}

		return MathHelper.clamp(sum_ticks / num_ticks, 0.0f, 20.0f);
	}

	public void reset_tick() {
		this.next_index       = 0;
		this.last_update_tick = - 1l;

		Arrays.fill(this.tick_rates, 0.0f);
	}

	public void update_time() {
		if (this.last_update_tick != - 1l) {
			float time /*       Very gay space and insane code.      */ = (float) (System.currentTimeMillis() - this.last_update_tick) / 1000.0f;
			this.tick_rates[(this.next_index % this.tick_rates.length)] = MathHelper.clamp(20.0f / time, 0.0f, 20.0f);
			
			this.next_index += 1;
		}

		this.last_update_tick = System.currentTimeMillis();
	}
}