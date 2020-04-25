package rina.turok.bope.bopemod.commands;

import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/public class BopePrefix extends BopeCommand {
	public BopePrefix() {
		super("prefix", "Change prefix.");
	}

	public boolean get_message(String[] message) {
		String prefix = "null";

		if (message.length > 1) {
			prefix = message[1];
		}

		if (message.length > 2) {
			BopeMessage.send_client_error_message(current_prefix() + "prefix <character>");

			return true;
		}

		if (prefix.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "prefix <character>");

			return true;
		}

		Bope.get_instance().command_manager.set_prefix(prefix);

		BopeMessage.send_client_message("The new prefix is " + prefix);

		return true;
	}
}