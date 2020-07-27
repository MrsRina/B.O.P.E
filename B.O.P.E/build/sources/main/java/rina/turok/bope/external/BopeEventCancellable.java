package rina.turok.bope.external;

import me.zero.alpine.type.Cancellable;

import net.minecraft.client.Minecraft;

/**
 * @author Rina
 *
 * Created by Rina.
 * 08/04/20.
 *
 */
public class BopeEventCancellable extends Cancellable {
	private Era era_switch = Era.EVENT_PRE;

	private final float partial_ticks;

	public BopeEventCancellable() {
		partial_ticks = Minecraft.getMinecraft().getRenderPartialTicks();
	}

	public Era get_era() {
		return era_switch;
	}

	public float get_partial_ticks() {
		return partial_ticks;
	}

	public enum Era {
		EVENT_PRE,
		EVENT_PERI,
		EVENT_POST;
	}
}