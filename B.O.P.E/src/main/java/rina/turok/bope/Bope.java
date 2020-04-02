package rina.turok.bope;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

// Zero Alpine.
import me.zero.alpine.EventManager;
import me.zero.alpine.EventBus;

import rina.turok.bope.bopemod.manager.BopeCommandManager;
import rina.turok.bope.bopemod.manager.BopeModuleManager;
import rina.turok.bope.BopeEventRegister;

//
// Rina.
// Author: Rina.
//
@Mod(modid = "bope", version = Bope.BOPE_VERSION)
public class Bope {
	// Somes arguments like, version, name, space...
	public static final String BOPE_NAME    = "B.O.P.E";
	public static final String BOPE_VERSION = "0.1";
	public static final String BOPE_SPACE   = " ";

	// A just log for initializing and if get a error show in log Minecraft.
	public static final Logger bope_register_log = LogManager.getLogger("bope");

	// Starting managers.
	public static BopeCommandManager command_manager;
	public static BopeModuleManager  module_manager = new BopeModuleManager("Mode -> LoadUtil.");

	// INSTANCE.
	@Mod.Instance
	private static Bope INSTANCE;

	// EVENT_BUS from ZeroAlpine.
	public static final EventBus EVENT_BUS = new EventManager();

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		Display.setTitle("B.O.P.E" + BOPE_SPACE + BOPE_VERSION);

		bope_register_log.info("\n\n------------- B.O.P.E -------------");
		bope_register_log.info("B.O.P.E loading utils.");

		bope_register_log.info("B.O.P.E initializing command list.");

		// Register event command.
		BopeEventRegister.register_manager(command_manager = new BopeCommandManager());

		// Init bope module manager.
		module_manager.init_bope_manager()

		// Init modules.
		module_manager.init_bope_modules();

		// Update the list modules.
		module_manager.update_module_list();
	}

	public static Bope get_instance() {
		return INSTANCE; // A function for get INSTANCE from all client.
	} 
}