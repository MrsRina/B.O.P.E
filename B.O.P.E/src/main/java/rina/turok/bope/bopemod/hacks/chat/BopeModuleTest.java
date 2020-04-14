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
	public BopeModuleTest() {
		super(BopeCategory.Category.BOPE_CHAT);

		module_info(
			"Module Test",
			"ModuleTest",
			"Module Test",
			-1
		);

		create_combobox(this, "Type", "types", "combobox");
		Bope.setting_manager.create_setting(new BopeSetting(this, "Test", "start_test", false));

		get_setting("types").add_item("putting");
		get_setting("types").add_item("gay");


		get_setting("types").set_current_item("putting");
	}

	@Override
	public void onUpdate() {
		if (get_setting("start_test").get_button_state()) {
			if (get_setting("types").get_combobox_value().equals("putting")) {
				mc.player.motionZ = 0.00001;
			}
		}
	}
}