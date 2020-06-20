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
		super("help", "A help util.");
	}

	public boolean get_message(String[] message) {
		String type = "null";

		if (message.length > 1) {
			type = message[1];
		}

		if (message.length > 2) {
			BopeMessage.send_client_error_message(current_prefix() + "help list/command");

			return true;
		}

		if (type.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "help list/command");

			return true;
		}

		if (type.equalsIgnoreCase("list")) {
			int count = 0;
			int size  = BopeListCommand.get_pure_command_list().size();

			StringBuilder commands_names = new StringBuilder();

			for (BopeCommand commands : BopeListCommand.get_pure_command_list()) {
				count++;

				if (count >= size) {
					commands_names.append(commands.get_name() + ".");
				} else {
					commands_names.append(commands.get_name() + ", ");
				}
			}

			BopeMessage.send_client_message(commands_names.toString());

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