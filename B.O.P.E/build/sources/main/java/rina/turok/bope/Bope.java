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
	// Somes arguments like, version, name, space...
	public static final String BOPE_NAME    = "B.O.P.E";
	public static final String BOPE_VERSION = "0.1";
	public static final String BOPE_SPACE   = " ";

	// A just log for initializing and if get a error show in log Minecraft.
	public static final Logger bope_register_log = LogManager.getLogger("bope");

	// Starting managers.
	public static BopeModuleManager  module_manager;
	public static BopeSettingManager setting_manager;
	public static BopeCommandManager command_manager;

	// INSTANCE.
	@Mod.Instance
	private static Bope INSTANCE;

	@Mod.EventHandler
	public void BopeStarting(FMLInitializationEvent event) {
		send_log("\n\n------------- B.O.P.E -------------");
		send_log(" - B.O.P.E loading utils.");

		send_log(" - B.O.P.E initializing command manager.");

		// Register event command.
		BopeEventRegister.register_command_manager(command_manager = new BopeCommandManager());

		send_log(" - B.O.P.E initializing event handler.");

		// Init BopeEventHandler.
		BopeEventHandler.INSTANCE = new BopeEventHandler();

		send_log(" - B.O.P.E initializing setting manager.");

		// Init setting manager.
		setting_manager = new BopeSettingManager("Mode -> LoadUtil.");

		send_log(" - B.O.P.E initializing module manager.");

		// Init bope module manager.
		module_manager = new BopeModuleManager("Mode -> LoadUtil.");

		module_manager.init_bope_manager();

		send_log(" - B.O.P.E initializing modules.");

		// Init modules.
		module_manager.init_bope_modules();

		send_log(" - B.O.P.E initializing events.");

		// Register event modules and manager.
		BopeEventRegister.register_module_manager(new BopeEventManager());

		send_log(" - B.O.P.E starting widgets.");

		send_log("\n - B.O.P.E Started");
	}

	public static void load_settings() {

	}

	public static Bope get_instance() {
		return INSTANCE; // A function for get INSTANCE from all client.
	}

	public static void send_log(String log) {
		bope_register_log.info(log);
	}

	public static String get_version() {
		return BOPE_VERSION;
	}

	public static BopeModuleManager get_module_manager() {
		return get_instance().module_manager;
	}

	public static BopeSettingManager get_setting_manager() {
		return get_instance().setting_manager;
	}
}