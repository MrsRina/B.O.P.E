package rina.turok.bope.bopemod.hacks;

import org.lwjgl.input.Keyboard;

import rina.turok.bope.bopemod.backgui.BopeButton;
import rina.turok.bope.bopemod.backgui.BopeSlider;
import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

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
		super(
			"Module Test",
			"ModuleTest",
			"Dev module.",
			Keyboard.KEY_Y,
			BopeCategory.Category.BOPE_CHAT
		);

		add_button(start);
	}

	@Override
	public void onUpdate() {
		if (start.get_value()) {
			mc.player.motionZ = 0.00001;
		}
	}
}