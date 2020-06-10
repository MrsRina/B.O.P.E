package rina.turok.bope.bopemod.commands;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

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
* 06/05/2020.
*
*/
public class BopeLabel extends BopeCommand {
	public BopeLabel() {
		super("label", "For change a custom label widget from a module.");
	}

	public boolean get_message(String[] message) {
		String module   = "null";
		String label    = "null";
		String change_1 = "null";
		String change_2 = "null";

		int syntax = 3;

		boolean is_to_suffix = false;

		if (message.length > 1) {
			module = message[1];
		}

		if (message.length > 2) {
			label = message[2];
		}

		if (message.length > 3) {
			change_1 = message[3];

			syntax++;
		}

		if (message.length > syntax) {
			BopeMessage.send_client_error_message(current_prefix() + "label <ModuleName> <ModuleNameLabel> <change1> <change2>");

			return true;
		}

		if (module.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "label <ModuleName> <ModuleNameLabel> <change1> <change2>");

			return true;
		}

		if (label.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "label <ModuleName> <ModuleNameLabel> <change1> <change2>");

			return true;
		}

		if (change_1.equals("null") && change_2.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "label <ModuleName> <ModuleNameLabel> <change1> <change2>");

			return true;
		}

		// Just fix for remove "null";
		if (change_2.equals("null")) {
			change_2 = "";
		}

		module = module.toLowerCase();

		BopeModule module_requested = Bope.get_module_manager().get_module_with_tag(module);

		if (module == null) {
			BopeMessage.send_client_error_message("This module does not exist.");

			return true;
		}

		if (module_requested.get_tag().equalsIgnoreCase("BopeChatSuffix")) {
			is_to_suffix = true;
		}

		label = module_requested.get_tag().toLowerCase() + label.toLowerCase();

		BopeSetting setting_requested = Bope.get_setting_manager().get_setting_with_tag(module, label);

		if (setting_requested == null) {
			BopeMessage.send_client_error_message("This label does not exist.");

			return true;
		}

		if (setting_requested.is_info()) {
			BopeMessage.send_client_error_message("You can't change a info.");

			return true;
		}

		String new_value = syntax == 4 ? change_1 : change_1 + " " + change_2;

		if (is_to_suffix) {
			if (change_1.length() >= 16) {
				change_1.substring(0, 16); // 16 bits.
			}

			new_value = change_1.toLowerCase();
		}

		setting_requested.set_value(new_value);

		BopeMessage.send_client_message("The label " + setting_requested.get_name() + " is now " + setting_requested.get_value("") + ".");

		return true;
	}
}
