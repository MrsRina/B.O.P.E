package rina.turok.bope.bopemod.commands;

import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.Bope;

// Rina.
public class BopePrefix extends BopeCommand {
	public BopePrefix() {
		super("prefix", "Change prefix.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 1) {
			String prefix = message[1];

			Bope.get_instance().command_manager.set_prefix(prefix);

			BopeMessage.send_client_message("The new prefix is " + prefix);
		} else {
			BopeMessage.send_client_message("For change " + Bope.get_instance().command_manager.get_prefix() + "prefix [new prefix].");
		}

		return true;
	}
}