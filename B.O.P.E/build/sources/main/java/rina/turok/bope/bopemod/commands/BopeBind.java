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

			BopeModule module_requested = Bope.get_module_manager().get_module_with_tag(module);

			if (module_requested != null) {
				module_requested.set_string_bind(key);

				BopeMessage.send_client_message("The bind of module " + module_requested.get_name() + " is now " + module_requested.get_string_bind());
			} else {
				BopeMessage.send_client_error_message("It module not exist, or the key is not valid. " + module_requested.get_name() + " " + module_requested.get_string_bind());
			}
		}

		return true;
	}
}