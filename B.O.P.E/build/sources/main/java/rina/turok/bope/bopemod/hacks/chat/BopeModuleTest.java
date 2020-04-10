package rina.turok.bope.bopemod.hacks;

import org.lwjgl.input.Keyboard;

import rina.turok.bope.bopemod.backgui.BopeButton;
import rina.turok.bope.bopemod.backgui.BopeSlider;
import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.bopemod.BopeSaveModule;
import rina.turok.bope.bopemod.BopeMessage;
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
	BopeButton start = new BopeButton(this, "Start", "start", true);
	
	public BopeModuleTest() {
		super(BopeCategory.Category.BOPE_CHAT);

		Bope.get_module_manager().register_module(new BopeSaveModule (
			"Module Test",
			"ModuleTest",
			-1
		));

		set_module_info (
			"Module Test",
			"ModuleTest",
			"Test Module"
		);
	}

	@Override
	public void onUpdate() {
		if (start.get_value()) {
			mc.player.motionZ = 0.00001;
		}
	}
}