package rina.turok.bope;

import rina.turok.bope.bopemod.manager.BopeCommandManager;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

//
// Rina.
// Author: Rina.
//
@Mod(modid = Bope.BOPE_NAME, version = Bope.BOPE_VERSION, acceptedMinecraftVersions = Bope.BOPE_MINECRAFT_VERSION)
public class Bope {
	public static final String BOPE_NAME    = "B.O.P.E";
	public static final String BOPE_VERSION = "0.1";
	public static final String BOPE_SPACE   = " ";

	public static final Logger bope_register_log  = LogManager.getLogger("bope");

	public static BopeCommandManager command_manager;

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		Display.setTitle("B.O.P.E" + BOPE_SPACE + BOPE_VERSION)

		bope_register_log.info("\n\n------------- B.O.P.E -------------");
		bope_register_log.info("B.O.P.E loading utils.");

		bope_register_log.info("B.O.P.E initializing command list.");

		// I will change it.
		MinecraftForge.EVENT_BUS.register(command_manager = new BopeCommandManager());
	}
}