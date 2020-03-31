package rina.turok.bope.bopemod.events;

import rina.turok.bope.framework.BopeWrapper;

import me.zero.alpine.type.Cancellable;

public class BopeEvents extends Cancellable {
	private PreEvent pre_event = PreEvent.PRE;

	private final float bope_partial_ticks;

	public BopeEvents() {
		bope_partial_ticks = BopeWrapper.mc.getRenderPartialTicks();
	}

	public PreEvent get_pre_event() {
		return pre_event;
	}

	public float get_render_partial_ticks() {
		return bope_partial_ticks;
	}

	public enum PreEvent {
		POST,
		PERI,
		PRE;
	}
}