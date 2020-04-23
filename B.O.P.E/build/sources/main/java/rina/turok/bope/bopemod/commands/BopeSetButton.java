package rina.turok.bope.bopemod.commands;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeCommand;
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
				Bope.get_setting_manager().get_setting_with_tag(module, name).set_value(Boolean.parseBoolean(state));
			} catch (Exception exc) {
				BopeMessage.send_client_error_message("It module setting or setting same not exist, or value not exist.");
			}
		}

		return true;
	}
}