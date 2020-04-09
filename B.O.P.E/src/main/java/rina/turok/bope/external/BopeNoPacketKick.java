package rina.turok.bope.external;

import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.bopemod.BopeModule;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeNoPacketKick {
	private static BopeNoPacketKick INSTANCE;

	public BopeNoPacketKick() {
		INSTANCE = this;
	}

	public static boolean is_active() {
		return INSTANCE.is_active();
	}
}