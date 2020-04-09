package rina.turok.bope.bopemod.commands;

import rina.turok.bope.bopemod.backgui.BopeSlider;
import rina.turok.bope.bopemod.backgui.BopeButton;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeCommand;

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
			String value  = message[2];
		}

		return true;
	}
}