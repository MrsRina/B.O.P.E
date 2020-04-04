package rina.turok.bope.bopemod.hacks;

import org.lwjgl.input.Keyboard;

import rina.turok.bope.bopemod.backgui.BopeButton;
import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

public class BopeModuleTest extends BopeModule {
	public BopeModuleTest() {
		super(
			"test",
			"test",
			"just test gay",
			Keyboard.KEY_Y,
			BopeCategory.Category.BOPE_CHAT
		);

		BopeButton.create_new_button("Gay Very Gay", "TestGay", false);
	}

	@Override
	public void onUpdate() {
		if (BopeButton.get_button("TestGay").get_value()) {
			BopeMessage.send_client_message("You are very gay.");
		} else {
			BopeMessage.send_client_message("You are not very gay.");
		}
	}

}