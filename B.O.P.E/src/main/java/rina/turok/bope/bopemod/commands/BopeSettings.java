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
		String what = "null";

		if (message.length > 1) {
			what = message[1];
		}

		if (what.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "settigns <save/load>");

			return true;
		}

		ChatFormatting c = ChatFormatting.GRAY;

		if (what.equalsIgnoreCase("save")) {
			Bope.get_config_manager().save();

			BopeMessage.send_client_message(ChatFormatting.GREEN + "Successfully " + c + "saved!");
		}
		
		if (what.equalsIgnoreCase("load")) {
			Bope.get_config_manager().load();

			BopeMessage.send_client_message(ChatFormatting.GREEN + "Successfully " + c + "loaded!");
		}

		return true;
	}
}