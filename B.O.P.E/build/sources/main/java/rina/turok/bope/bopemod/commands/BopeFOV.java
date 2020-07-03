package rina.turok.bope.bopemod.commands;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeCommand;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 04/06/2020.
*
**/
public class BopeFOV extends BopeCommand {
	public BopeFOV() {
		super("fov", "To change fov value");
	}

	public boolean get_message(String[] message) {
		String value = "null";

		if (message.length > 1) {
			value = message[1];
		}

		if (message.length > 2) {
			BopeMessage.send_client_error_message("fov +30 or -180");

			return true;
		}

		if (value.equals("null")) {
			BopeMessage.send_client_error_message("fov +30 or -180");

			return true;
		}

		try {
			Float.parseFloat(value);
		} catch (Exception exc) {
			BopeMessage.send_client_error_message("Its not a float or a intenger.");			

			return true;
		}

		float value_to_float = Float.parseFloat(value);

		mc.gameSettings.fovSetting = clamp(value_to_float, 30.0f, 179.9f);

		BopeMessage.send_client_message("Setted " + Bope.g + Float.toString(clamp(value_to_float, 30.0f, 179.9f)) + Bope.r + " to field of view.");

		return true;
	}

	public float clamp(float value, float min, float max) {
		if (value <= min) {
			return min;
		} else if (value >= max) {
			return max;
		} else {
			return value;
		}
	}
}