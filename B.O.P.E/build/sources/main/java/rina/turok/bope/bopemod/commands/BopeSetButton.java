package rina.turok.bope.bopemod.commands;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeSetting;
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeSetButton extends BopeCommand {
	public BopeSetButton() {
		super("button", "Configure button state.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 3) {
			String module = message[1];
			String name   = message[2];
			String state  = message[3];

			try {
				if (state.equals("true")) {
					((BopeSetting.TypeButton) Bope.get_setting_manager().get_setting_by_name_in(name, Bope.get_module_manager().get_module_with_tag(module))).setValue(true);

					BopeMessage.send_client_message("The button " + name + " of " + module + " now is actual value true.");
				} else if (state.equals("false")) {
					((BopeSetting.TypeButton) Bope.get_setting_manager().get_setting_by_name_in(name, Bope.get_module_manager().get_module_with_tag(module))).setValue(false);

					BopeMessage.send_client_message("The button " + name + " of " + module + " now is actual value false.");
				} else {
					BopeMessage.send_client_error_message("Its not a boolean value.");
				}
			} catch (Exception exc) {
				BopeMessage.send_client_error_message("The name of module or setting not exist.");
			}
		}

		return true;
	}
}