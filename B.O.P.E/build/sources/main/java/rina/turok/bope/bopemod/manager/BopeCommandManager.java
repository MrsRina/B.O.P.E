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
	String tag;

	private Minecraft mc = Minecraft.getMinecraft();

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

	@SubscribeEvent
	public void onChat(ClientChatEvent event) {
		String   message      = event.getMessage();
		String[] message_args = command_list.get_message(event.getMessage());

		boolean true_command = false;

		if (message_args.length > 0) {		
			for (BopeCommand command : command_list.get_pure_command_list()) {
				try {
					if (command_list.get_message(event.getMessage())[0].equalsIgnoreCase(command.get_name())) {
						event.setCanceled(true);

						mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());

						true_command = command.get_message(command_list.get_message(event.getMessage()));
					}
				} catch (Exception exc) {} // Somes gays problems.
			}

			if (!true_command && command_list.has_prefix(event.getMessage())) {
				event.setCanceled(true);
				
				BopeMessage.send_client_message("Try use .help or talk with Rina or Cyro.");

				true_command = false;
			}
		}
	}

	public String get_tag() {
		return this.tag;
	}
}