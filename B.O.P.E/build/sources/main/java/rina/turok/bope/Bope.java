package rina.turok.bope;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

// Managers.
import rina.turok.bope.bopemod.manager.BopeCommandManager;
import rina.turok.bope.bopemod.manager.BopeSettingManager;
import rina.turok.bope.bopemod.manager.BopeModuleManager;
import rina.turok.bope.bopemod.manager.BopeEventManager;

// External.
import rina.turok.bope.external.BopeEventHandler;

// Core.
import rina.turok.bope.BopeEventRegister;

/** ...
 * @author Rina.
 * 
 * The ZeroAlpine event manager is compatible with license MIT.
 * All Rights for Rina.
 *
 */
@Mod(modid = "bope", version = Bope.BOPE_VERSION)
public class Bope {
	// Master instance.
	@Mod.Instance
	private static Bope MASTER;

	// Somes arguments like, version, name, space...
	public static final String BOPE_NAME    = "B.O.P.E";
	public static final String BOPE_VERSION = "0.1";
	public static final String BOPE_SPACE   = " ";

	// A just log for initializing and if get a error show in log Minecraft.
	public static Logger bope_register_log;

	// Starting managers.
	public static BopeModuleManager  module_manager;
	public static BopeSettingManager setting_manager;
	public static BopeCommandManager command_manager;
	public static BopeEventManager   event_h_manager;

	@Mod.EventHandler
	public void BopeStarting(FMLInitializationEvent event) {
		init_log("BOPE");

		send_log("Client starting...");

		// Init BopeEventHandler.
		BopeEventHandler.INSTANCE = new BopeEventHandler();

		send_log("initializing managers.");

		// Init managers.
		setting_manager = new BopeSettingManager("Setting manager");
		module_manager  = new BopeModuleManager("module manager");
		command_manager = new BopeCommandManager("command manager");
		event_h_manager = new BopeEventManager("event handler manager");

		send_log("Managers are started.");

		send_log("Registering events.");

		// Register event modules and manager.
		BopeEventRegister.register_command_manager(command_manager);
		BopeEventRegister.register_module_manager(event_h_manager);

		send_log("Events are registared.");

		send_log("Client started no problems.");
	}

	public void init_log(String name) {
		bope_register_log = LogManager.getLogger(name);

		send_log("STARTING...");
	}

	public static Bope get_instance() {
		return MASTER; // A function for get INSTANCE from all client.
	}

	public static void send_log(String log) {
		bope_register_log.info(log);
	}

	public static String get_version() {
		return BOPE_VERSION;
	}

	public static BopeCommandManager get_command_manager() {
		return get_instance().command_manager;
	}

	public static BopeModuleManager get_module_manager() {
		return get_instance().module_manager;
	}

	public static BopeSettingManager get_setting_manager() {
		return get_instance().setting_manager;
	}
}