package rina.turok.bope;

import net.minecraftforge.common.MinecraftForge;

// Managers.
import rina.turok.bope.bopemod.manager.BopeCommandManager;
import rina.turok.bope.bopemod.manager.BopeEventManager;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeEventRegister {
	public static void register_command_manager(BopeCommandManager manager) {
		MinecraftForge.EVENT_BUS.register(manager);
	}

	public static void register_module_manager(BopeEventManager manager) {
		MinecraftForge.EVENT_BUS.register(manager);
	}
}