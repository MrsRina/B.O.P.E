package rina.turok.bope;

import net.minecraftforge.common.MinecraftForge;

import rina.turok.bope.bopemod.manager.BopeCommandManager;

public class BopeEventRegister {
	public static void register_manager(BopeCommandManager manager) {
		MinecraftForge.EVENT_BUS.register(manager);
	}
}