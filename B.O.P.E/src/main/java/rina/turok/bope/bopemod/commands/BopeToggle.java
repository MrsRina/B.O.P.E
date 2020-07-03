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
public class BopeToggle extends BopeCommand {
	public BopeToggle() {
		super("t", "For toggle module.");
	}

	public boolean get_message(String[] message) {
		String module = "null";

		if (message.length > 1) {
			module = message[1];
		}

		if (message.length > 2) {
			BopeMessage.send_client_error_message("t module");

			return true;
		}

		if (module.equals("null")) {
			BopeMessage.send_client_error_message("t module");

			return true;
		}

		BopeModule module_requested = Bope.get_instance().module_manager.get_module_with_tag(module);

		if (module_requested != null) {
			module_requested.toggle();

			if (!module_requested.alert()) {
				BopeMessage.send_client_message(module_requested.is_active() == true ? Bope.dg + module_requested.get_tag() : Bope.re + module_requested.get_tag());
			}
		} else {
			BopeMessage.send_client_error_message("Module does not exist.");
		}

		return true;
	}
}