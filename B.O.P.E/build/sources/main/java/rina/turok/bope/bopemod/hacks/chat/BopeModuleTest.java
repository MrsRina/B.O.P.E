package rina.turok.bope.bopemod.hacks;

import org.lwjgl.input.Keyboard;

import java.util.*;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeSaveModule;
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
	BopeSetting.TypeButton   start    = create_button("Test", true);
	BopeSetting.TypeCombobox combobox = create_combobox("Test", Arrays.asList("test1", "test2"), "test1");

	public BopeModuleTest() {
		super(BopeCategory.Category.BOPE_CHAT);

		module_info(
			"Module Test",
			"ModuleTest",
			"Module Test",
			Keyboard.KEY_P
		);
	}

	@Override
	public void onUpdate() {
		if (start.getValue()) {

			if (combobox.getValue().equals("test1")) {
				BopeMessage.send_client_message("Turn off it KKKKKKKKKKKKKKKKKKKKKK");
			}
		}
	}
}