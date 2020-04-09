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
*/
public class BopeToggle extends BopeCommand {
	public BopeToggle() {
		super("t", "Toggle module.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 1) {
			String module = message[1];

			try {
				Bope.get_instance().module_manager.get_module(module).toggle();
			} catch (Exception exc) {
				BopeMessage.send_client_error_message("This module not exist.");
			}
		} else {
			BopeMessage.send_client_message(Bope.get_instance().command_manager.get_prefix() + "t module name.");
		}

		return true;
	}
}