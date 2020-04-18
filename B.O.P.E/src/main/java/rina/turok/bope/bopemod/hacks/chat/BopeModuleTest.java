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
	BopeSetting.TypeButton button_1 = create_button("button1", true);
	BopeSetting.TypeButton button_2 = create_button("button2", true);

	public BopeModuleTest() {
		super("Module Test", BopeCategory.Category.BOPE_CHAT);

		module_info(
			"ModuleTest",
			"Module Test"
		);
	}

	@Override
	public void onUpdate() {
		if (button_1.getValue()) {
			if (button_2.getValue()) {
				BopeMessage.send_client_message("Button 2 on");
			} else {
				BopeMessage.send_client_message("Button 2 off");
			}
		}
	}
}