 package rina.turok.bope.bopemod.manager;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.Style;

// Commands.
import rina.turok.bope.bopemod.commands.BopeListCommand;

// Data.
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeCommandManager {
	private String tag;

	public static BopeListCommand command_list;

	public BopeCommandManager(String tag) {
		this.tag = tag;

		command_list = new BopeListCommand(new Style().setColor(TextFormatting.BLUE));
	}

	public void init_commands() {
		command_list.init_bope_command_lookup();
	}

	public static void set_prefix(String new_prefix) {
		command_list.set_prefix(new_prefix);
	}

	public static String get_prefix() {
		return command_list.get_prefix();
	}

	public String get_tag() {
		return this.tag;
	}
}