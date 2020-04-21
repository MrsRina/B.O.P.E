package rina.turok.bope.bopemod.hacks.chat;

import org.lwjgl.input.Keyboard;

import java.util.*;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeSetting;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeModuleTest extends BopeModule {
	BopeSetting button_1 = new BopeSetting(this, "Button1", "button1", true);
	BopeSetting button_2 = new BopeSetting(this, "Button2", "button2", true);

	public BopeModuleTest() {
		super("Module Test", BopeCategory.Category.BOPE_CHAT);

		module_info(
			"ModuleTest",
			"Module Test"
		);

		Bope.get_setting_manager().array_setting.add(button_1);
		Bope.get_setting_manager().array_setting.add(button_2);
	}

	@Override
	public void onUpdate() {
		if (button_1.get_button_value()) {
			if (button_2.get_button_value()) {
				BopeMessage.send_client_message("Button 2 on");
			} else {
				BopeMessage.send_client_message("Button 2 off");
			}
		}
	}
}