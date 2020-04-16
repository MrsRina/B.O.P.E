package rina.turok.bope.bopemod.hacks;

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
	BopeSetting.TypeButton start = create_button("Test", true);
	BopeSetting.TypeButton start_2 = create_button("Tes2t", true);

	public BopeModuleTest() {
		super("Module Test", BopeCategory.Category.BOPE_CHAT);

		module_info(
			"ModuleTest",
			"Module Test"
		);
	}

	@Override
	public void onUpdate() {
		if (start.getValue()) {

			BopeMessage.send_client_message("Turn off it KKKKKKKKKKKKKKKKKKKKKK");
		}
	}
}