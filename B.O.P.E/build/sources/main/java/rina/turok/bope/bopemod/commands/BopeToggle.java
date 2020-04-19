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
	String module_toggled;
	String module_state;

	boolean is_real_module;

	public BopeToggle() {
		super("t", "Toggle module.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 1) {
			String module = message[1];
			
			module_toggled = "null";
			module_state   = "null";
			is_real_module = false;

			Bope.get_module_manager().get_array_modules().stream().forEach(module_requested -> {
				if (module_requested.get_tag().equalsIgnoreCase(module)) {
					module_requested.toggle();

					is_real_module = true;
					module_toggled = module_requested.get_tag();
					module_state   = Boolean.toString(module_requested.is_active()); 
				}
			});

			if (is_real_module != false) {
				BopeMessage.send_client_error_message("It module not exist.");
			} else {
				BopeMessage.send_client_message("The module " + module_toggled + " is now " + module_state + ".");
			}
		} else {
			BopeMessage.send_client_message(Bope.get_instance().command_manager.get_prefix() + "t module name.");
		}

		return true;
	}
}