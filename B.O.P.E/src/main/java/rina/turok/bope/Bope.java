package rina.turok.bope;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.lwjgl.opengl.Display;
import org.lwjgl.input.Keyboard;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

// Guiscreen;
import rina.turok.bope.bopemod.guiscreen.BopeGUI;
import rina.turok.bope.bopemod.guiscreen.BopeHUD;

// Managers.
import rina.turok.bope.bopemod.manager.BopeCommandManager;
import rina.turok.bope.bopemod.manager.BopeSettingManager;
import rina.turok.bope.bopemod.manager.BopeConfigManager;
import rina.turok.bope.bopemod.manager.BopeModuleManager;
import rina.turok.bope.bopemod.manager.BopeEventManager;
import rina.turok.bope.bopemod.manager.BopeHUDManager;

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
 * Created by Rina.
 * 05/04/20.
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
	public static final String BOPE_SIGN    = " \u23D0 ";

	// GUI.
	public static final int BOPE_KEY_GUI /****/ = Keyboard.KEY_RSHIFT;
	public static final int BOPE_KEY_DELETE     = Keyboard.KEY_DELETE;
	public static final int BOPE_KEY_GUI_ESCAPE = Keyboard.KEY_ESCAPE;

	// A just log for initializing and if get a error show in log Minecraft.
	public static Logger bope_register_log;

	// Starting managers.
	public static BopeCommandManager command_manager;
	public static BopeSettingManager setting_manager;
	public static BopeConfigManager  config_manager;
	public static BopeModuleManager  module_manager;
	public static BopeEventManager   event_manager;
	public static BopeHUDManager     hud_manager;

	// Cick GUI and HUD.
	public static BopeGUI click_gui;
	public static BopeHUD click_hud;

	@Mod.EventHandler
	public void BopeStarting(FMLInitializationEvent event) {
		init_log(BOPE_NAME);

		send_minecraft_log("Loading packages initializing in main class. [Bope.class]");

		// Init BopeEventHandler.
		BopeEventHandler.INSTANCE = new BopeEventHandler();

		// Init managers.
		setting_manager = new BopeSettingManager("<4><3><4><4><2><4><5><9><4><3><1>");
		command_manager = new BopeCommandManager("<4><3><4><4><2><4><5><9><4><3><1>");
		config_manager  = new BopeConfigManager ("<4><3><4><4><2><4><5><9><4><3><1>");
		module_manager  = new BopeModuleManager ("<4><3><4><4><2><4><5><9><4><3><1>");
		event_manager   = new BopeEventManager  ("<4><3><4><4><2><4><5><9><4><3><1>");
		hud_manager     = new BopeHUDManager    ("000000000000000000000000000000000");

		send_minecraft_log("Managers are initialed.");

		click_gui = new BopeGUI();
		click_hud = new BopeHUD();

		send_minecraft_log("GUI and HUD initialed.");

		// Register event modules and manager.
		BopeEventRegister.register_command_manager(command_manager);
		BopeEventRegister.register_module_manager(event_manager);

		send_minecraft_log("Events registered.");
		send_minecraft_log("Client started.");

		// Load all config.
		config_manager.load();

		// For just fix the GUI.
		if (module_manager.get_module_with_tag("GUI").is_active()) {
			module_manager.get_module_with_tag("GUI").set_active(false);
		}

		// And fix the HUD.
		if (module_manager.get_module_with_tag("HUD").is_active()) {
			module_manager.get_module_with_tag("HUD").set_active(false);
		}
	}

	public void init_log(String name) {
		bope_register_log = LogManager.getLogger(name);

		send_minecraft_log("...");
	}

	public static Bope get_instance() {
		return MASTER; // A function for get INSTANCE from all client.
	}

	public static void send_minecraft_log(String log) {
		bope_register_log.info(log);
	}

	public static void send_client_log(String log) {
		Date   hora = new Date();
		String data = new SimpleDateFormat("HH:mm:ss:").format(hora);

		get_instance().config_manager.send_log("<" + BOPE_NAME + "><" + data + "> " + log);
	}

	public static String get_name() {
		return  BOPE_NAME;
	}

	public static String get_version() {
		return BOPE_VERSION;
	}

	public static String get_actual_user() {
		String player_requested = "NoName";

		if (Minecraft.getMinecraft().player != null) {
			player_requested = Minecraft.getMinecraft().player.getName();
		}

		return player_requested;
	}

	public static BopeCommandManager get_command_manager() {
		return get_instance().command_manager;
	}

	public static BopeConfigManager get_config_manager() {
		return get_instance().config_manager;
	}

	public static BopeModuleManager get_module_manager() {
		return get_instance().module_manager;
	}

	public static BopeSettingManager get_setting_manager() {
		return get_instance().setting_manager;
	}

	public static BopeHUDManager get_hud_manager() {
		return get_instance().hud_manager;
	}

	public String convert_to_smoth_font(String base) {
		String new_base = base;
	}
}                                                    