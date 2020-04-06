package rina.turok.bope.bopemod.commands;

import rina.turok.bope.bopemod.backgui.BopeSlider;
import rina.turok.bope.bopemod.backgui.BopeButton;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeCommand;

public class BopeSetButton extends BopeCommand {
	public BopeSetButton() {
		super("button", "Configure button state.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 2) {
			String button = message[1];
			String value  = message[2];

			try {
				if (value.equals("true")) {
					BopeButton.get_button(button).set_value(true);
				} else if (value.equals("false")) {
					BopeButton.get_button(button).set_value(false);
				} else if (value.equals("get")) {
					BopeMessage.send_client_message("This value is: " + Boolean.toString(BopeButton.get_button(button).get_value()));
				}
			} catch (Exception exc) {
				BopeMessage.send_client_error_message("A error ocurred with this name.");
			}
		}

		return true;
	}
}