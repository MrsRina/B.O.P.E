package rina.turok.bope.bopemod.commands;

import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeBind extends BopeCommand {
	public BopeBind() {
		super("bind", "For bind key.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 2) {
			String module = message[1];
			String key    = message[2];

			try {
				Bope.get_module_manager().get_module(module).set_string_bind(key);

				BopeMessage.send_client_message("The bind of module " + Bope.get_module_manager().get_module(module).get_name() + " is now " + Bope.get_module_manager().get_module(module).get_string_bind());
			} catch (Exception exc) {
				BopeMessage.send_client_error_message("This module not exist or is a invalid key.");
			}
		}

		return true;
	}
}