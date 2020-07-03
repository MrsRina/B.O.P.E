package rina.turok.bope.bopemod.commands;

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
		super("settings", "To save/load settings.");
	}

	public boolean get_message(String[] message) {
		String what = "null";

		if (message.length > 1) {
			what = message[1];
		}

		if (what.equals("null")) {
			BopeMessage.send_client_error_message("settings save/load");

			return true;
		}

		if (what.equalsIgnoreCase("save")) {
			Bope.get_config_manager().save_values();
			Bope.get_config_manager().save_binds();
			Bope.get_config_manager().save_client();
			Bope.get_config_manager().save_friends();

			BopeMessage.send_client_message(Bope.dg + "Successfully " + Bope.r + "saved!");
		} else if (what.equalsIgnoreCase("load")) {
			Bope.get_config_manager().load();

			BopeMessage.send_client_message(Bope.dg + "Successfully " + Bope.r + "loaded!");
		} else {
			BopeMessage.send_client_error_message("settings save/load");

			return true;
		}

		return true;
	}
}