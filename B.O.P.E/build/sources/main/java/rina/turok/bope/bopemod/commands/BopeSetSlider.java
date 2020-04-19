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
public class BopeSetSlider extends BopeCommand {
	public BopeSetSlider() {
		super("empty", "Configure slider value.");
	}

	public boolean get_message(String[] message) {
		if (message.length >= 1) {
			String a = message[1];

			if (a.equals("?")) {
				BopeMessage.send_client_message("Is: " + Boolean.toString(Bope.get_module_manager().get_array_modules().isEmpty()));
			}
		}

		return true;
	}
}