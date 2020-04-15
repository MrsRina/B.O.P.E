package rina.turok.bope.bopemod.hacks;

import org.lwjgl.input.Keyboard;

import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.bopemod.BopeSaveModule;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeSetting;
import rina.turok.bope.bopemod.BopeModule;
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeModuleTest extends BopeModule {
	BopeSetting start;

	public BopeModuleTest() {
		super(BopeCategory.Category.BOPE_CHAT);

		module_info(
			"Module Test",
			"ModuleTest",
			"Module Test",
			-1
		);

		start = create_button(this, "Test", "test", false);
	}

	@Override
	public void onUpdate() {
		if (start.get_button_state()) {
			BopeMessage.send_client_message("Turn off it KKKKKKKKKKKKKKKKKKKKKK");
		}
	}
}