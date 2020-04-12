package rina.turok.bope.bopemod.commands;

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
		if (message.length > 2) {
			String button = message[1];
			String final_ = message[2];

			try {
				if (final_.equals("true")) {
					Bope.get_setting_manager().get_setting(button).set_button_value(true);

					BopeMessage.send_client_message(button + " was setted to " + final_);
				} else if (final_.equals("false")) {
					Bope.get_setting_manager().get_setting(button).set_button_value(false);

					BopeMessage.send_client_message(button + " was setted to " + final_);
				} else {
					Bope.get_setting_manager().get_setting("B.O.P.E on top error management kkkkkkkkk");
				}
			} catch (Exception exc) {
				BopeMessage.send_client_error_message("This " + button + " not exist. Or " + final_ + " is not a real value.");
			}
		}

		return true;
	}
}