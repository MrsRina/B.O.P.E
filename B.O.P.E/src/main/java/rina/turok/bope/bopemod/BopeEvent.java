package rina.turok.bope.bopemod;

import me.zero.alpine.type.Cancellable;

import net.minecraft.client.Minecraft;

public class BopeEvent extends Cancellable {
	private Era era_switch = Era.EVENT_PRE;
	private final float partial_ticks;

	public BopeEvent() {
		partial_ticks = Minecraft.getMinecraft().getRenderPartialTicks();
	}

	public Era get_era() {
		return era_switch;
	}

	public float get_partial_ticcks() {
		return partial_ticks;
	}

	public enum Era {
		EVENT_PRE,
		EVENT_PERI,
		EVENT_POST;
	}
}