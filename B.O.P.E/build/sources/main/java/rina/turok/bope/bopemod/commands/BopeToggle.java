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
			BopeMessage.send_client_error_message(current_prefix() + "t <ModuleNameNoSpace>");

			return true;
		}

		if (module.equals("null")) {
			BopeMessage.send_client_error_message(Bope.get_instance().command_manager.get_prefix() + "t <ModuleNameNoSpace>");

			return true;
		}

		BopeModule module_requested = Bope.get_instance().module_manager.get_module_with_tag(module);

		if (module_requested != null) {
			module_requested.toggle();

			BopeMessage.send_client_message("[" + module_requested.get_tag() + "] - Toggled to " + Boolean.toString(module_requested.is_active()) + ".");
		} else {
			BopeMessage.send_client_error_message("Module does not exist.");
		}

		return true;
	}
}