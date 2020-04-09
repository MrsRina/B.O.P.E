package rina.turok.bope.bopemod.commands;

import rina.turok.bope.bopemod.backgui.BopeSlider;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeCommand;

public class BopeSetSlider extends BopeCommand {
	public BopeSetSlider() {
		super("slider", "Configure slider value.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 3) {
			String slider = message[1];
			String value  = message[2];
			String type   = message[3];
		}

		return true;
	}
}