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
	BopeSetting.TypeButton start = create_button("Test", false);

	public BopeModuleTest() {
		super(BopeCategory.Category.BOPE_CHAT);

		module_info(
			"Module Test",
			"ModuleTest",
			"Module Test",
			-1
		);
	}

	@Override
	public void onUpdate() {
		if (start.getValue()) {
			BopeMessage.send_client_message("Turn off it KKKKKKKKKKKKKKKKKKKKKK");
		}
	}
}