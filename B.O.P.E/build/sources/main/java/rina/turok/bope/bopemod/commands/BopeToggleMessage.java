package rina.turok.bope.bopemod.commands;

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
public class BopeToggleMessage extends BopeCommand {
	public BopeToggleMessage() {
		super("alert", "alert if a module does active.");
	}

	public boolean get_message(String[] message) {
		String module = "null";
		String state  = "null";

		if (message.length > 1) {
			module = message[1];
		}

		if (message.length > 2) {
			state = message[2];
		}

		if (message.length > 3) {
			BopeMessage.send_client_error_message(current_prefix() + "t <ModuleName> <True/On/False/Off>");

			return true;
		}

		if (module.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "t <ModuleName> <True/On/False/Off>");

			return true;
		}

		if (state.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "t <ModuleName> <True/On/False/Off>");

			return true;
		}

		module = module.toLowerCase();
		state  = state.toLowerCase();

		BopeModule module_requested = Bope.get_module_manager().get_module_with_tag(module);

		if (module_requested == null) {
			BopeMessage.send_client_error_message("This module does not exist.");

			return true;
		}

		boolean value = true;

		if (state.equals("true") || state.equals("on")) {
			value = true;
		} else if (state.equals("false") || state.equals("off")) {
			value = false;
		} else {
			BopeMessage.send_client_error_message("This value does not exist. <True/On/False/Off>");

			return true;
		}

		module_requested.set_if_can_send_message_toggle(value);

		BopeMessage.send_client_message("The actual value of " + module_requested.get_name() +  " is " + Boolean.toString(module_requested.can_send_message_when_toggle()) + ".");

		return true;
	}
}