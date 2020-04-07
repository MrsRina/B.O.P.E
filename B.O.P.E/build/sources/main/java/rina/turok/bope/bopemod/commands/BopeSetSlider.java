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

			if (type.equals("double")) {
				try {
					BopeSlider.get_slider_double(slider).set_slider_value(Double.parseDouble(value));
				} catch (Exception exc) {
					BopeMessage.send_client_error_message("This is not a double value.");
				}
			} else if (type.equals("float")) {
				try {
					BopeSlider.get_slider_float(slider).set_slider_value(Float.parseFloat(value));
				} catch (Exception exc) {
					BopeMessage.send_client_error_message("This is not a float value.");
				}
			} else if (type.equals("int")) {
				try {
					BopeSlider.get_slider_int(slider).set_slider_value(Integer.parseInt(value));
				} catch (Exception exc) {
					BopeMessage.send_client_error_message("This is not a int value.");
				}
			}
		}

		return true;
	}
}