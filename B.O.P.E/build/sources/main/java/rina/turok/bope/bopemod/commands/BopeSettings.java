package rina.turok.bope.bopemod.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

// Data.
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;

// Core.
import rina.turok.bope.Bope;

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
			ChatFormatting c = ChatFormatting.GRAY;

			String what = message[1];

			if (what.equals("save")) {
				Bope.get_config_manager().save();

				BopeMessage.send_client_message(ChatFormatting.GREEN + "Successfully " + c + "saved!");
			} else if (what.equals("load")) {
				Bope.get_config_manager().load();

				BopeMessage.send_client_message(ChatFormatting.GREEN + "Successfully " + c + "loaded!");
			}
		}

		return true;
	}
}