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
			key = message[2];
		}

		if (module.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "bind <ModuleNameNoSpace> <key>");

			return true;
		}

		if (key.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "bind <ModuleNameNoSpace> <key>");

			return true;
		}

		BopeModule module_requested = Bope.get_module_manager().get_module_with_tag(module);

		if (module_requested != null) {
			int new_bind = Keyboard.getKeyIndex(key.toUpperCase());

			module_requested.set_bind(new_bind);

			BopeMessage.send_client_message("The bind of module " + module_requested.get_name() + " is now " + module_requested.get_bind("0"));
		} else {
			BopeMessage.send_client_error_message("It key not exist.");
		}

		return true;
	}
}