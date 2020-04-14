package rina.turok.bope.bopemod.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import rina.turok.bope.bopemod.manager.BopeSettingManager;
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeConfig;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeSettings extends BopeCommand {
	public BopeSettings() {
		super("settings", "Save/load settings.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 1) {
			String what = message[1];

			if (what.equals("save")) {
				BopeConfig.save();

				BopeMessage.send_client_message("Saved " + ChatFormatting.GREEN + "successful!");
			}
		}

		return true;
	}
}