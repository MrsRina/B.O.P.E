package rina.turok.bope.bopemod.commands;

import org.lwjgl.input.Keyboard;

// Data.
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
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
		super("bind", "For bind module.");
	}

	public boolean get_message(String[] message) {
		String module = "null;";
		String key    = "null";

		if (message.length > 1) {
			module = message[1];
		}

		if (message.length > 2) {
			key = message[2].toUpperCase();
		}

		if (message.length > 3) {
			BopeMessage.send_client_error_message(current_prefix() + "bind <ModuleNameNoSpace> <key>");

			return true;
		}

		if (module.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "bind <ModuleNameNoSpace> <key>");

			return true;
		}

		if (key.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "bind <ModuleNameNoSpace> <key>");

			return true;
		}

		module = module.toUpperCase();


		if (module.equals("GUI")) {
			BopeMessage.send_client_error_message("The module called GUI cannot be bound.");

			return true;
		}

		BopeModule module_requested = Bope.get_module_manager().get_module_with_tag(module);

		if (module_requested != null) {
			if (!(key.equals("NONE"))) {
				int new_bind = Keyboard.getKeyIndex(key.toUpperCase());

				if (new_bind != 0) {
					if (!(new_bind == module_requested.get_bind(0))) {
						module_requested.set_bind(new_bind);

						BopeMessage.send_client_message(module_requested.get_tag() +  " is bound to " + module_requested.get_bind(""));
					} else {
						BopeMessage.send_client_error_message("The " + module_requested.get_tag() + " already have it key.");
					}
				} else {
					BopeMessage.send_client_error_message("Key does not exist.");
				}
			} else {
				module_requested.set_bind(0);

				BopeMessage.send_client_message(module_requested.get_tag() + " is bound to None.");
			}
		} else {
			BopeMessage.send_client_error_message("Module does not exist.");
		}

		return true;
	}
}