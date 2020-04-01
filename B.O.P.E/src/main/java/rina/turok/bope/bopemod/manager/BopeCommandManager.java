package rina.turok.bope.bopemod.manager;

import rina.turok.bope.bopemod.commands.BopeListCommand;
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.Style;

public class BopeCommandManager {
	public static BopeListCommand command_list;

	public BopeCommandManager() {
		command_list = new BopeListCommand(new Style().setColor(TextFormatting.GRAY));
	}

	public void init_commands() {
		command_list.init_bope_command_lookup();
	}

	@SubscribeEvent
	public void onChat(ClientChatEvent event) {
		String   message      = event.getMessage();
		String[] message_args = command_list.get_message(event.getMessage());

		boolean true_command = false;

		if (message_args.length > 0) {
			for (BopeCommand command : command_list.get_pure_command_list()) {
				if (command_list.get_message(event.getMessage())[0].equalsIgnoreCase(chat.name)) {
					true_command = command.get_message(command_list.get_message(event.getMessage()));
				}
			}

			if (!true_command && command_list.has_prefix(event.getMessage())) {
				BopeMessage.send_client_message("Try use .help or talk with Rina or Cyro.");

				true_command = false;
			}

			event.setMessage(""); // For remove totally the message.
		}
	}
}