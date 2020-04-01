package rina.turok.bope.bopemod.commands;

import rina.turok.bope.bopemod.BopeCommand;

public class BopeToggle extends BopeCommand {
	public BopeToggle() {
		super("t", "For toggle modules.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 1) {
			String module = message[1];
		}

		return true;
	}
}