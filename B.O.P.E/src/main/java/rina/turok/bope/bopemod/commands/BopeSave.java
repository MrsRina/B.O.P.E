package rina.turok.bope.bopemod.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeConfig;

public class BopeSave extends BopeCommand {
	public BopeSave() {
		super("save", "Save settings.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 0) {
			BopeConfig.save();

			BopeMessage.send_client_message("Saved " + ChatFormatting.GREEN + "successful!");
		}

		return true;
	}
}