package rina.turok.bope.bopemod.commands;

// Commands.
import rina.turok.bope.bopemod.commands.BopeListCommand;

// Data.
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 21/04/20.
*
*/
public class BopeHelp extends BopeCommand {
	public BopeHelp() {
		super("help", "To get list of commands.");
	}

	public boolean get_message(String[] message) {
		String type = "null";

		if (message.length > 1) {
			type = message[1];
		}

		if (message.length > 2) {
			BopeMessage.send_client_error_message(current_prefix() + "help <List/NameCommand>");

			return true;
		}

		if (type.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "help <List/NameCommand>");

			return true;
		}

		if (type.equalsIgnoreCase("list")) {
			int commands_length = 0;

			for (BopeCommand commands : BopeListCommand.get_pure_command_list()) {
				BopeMessage.send_client_message(commands.get_name());

				commands_length++;
			}

			return true;
		}

		BopeCommand command_requested = BopeListCommand.get_command_with_name(type);

		if (command_requested == null) {
			BopeMessage.send_client_error_message("This command does not exist.");

			return true;
		}

		BopeMessage.send_client_message(command_requested.get_name() + " - " + command_requested.get_description());

		return true;
	}
}