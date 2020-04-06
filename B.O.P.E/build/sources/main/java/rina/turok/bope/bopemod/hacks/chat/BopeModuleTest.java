package rina.turok.bope.bopemod.hacks;

import org.lwjgl.input.Keyboard;

import rina.turok.bope.bopemod.backgui.BopeButton;
import rina.turok.bope.bopemod.backgui.BopeSlider;
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

		BopeButton.create_new_button("Test Button", "StartTest", false);
		BopeSlider.create_new_slider_float("Test Slider", "FloatValue", 1.0f, 0.5f, 10.0f);
	}

	@Override
	public void onUpdate() {
		if (BopeButton.get_button("StartTest").get_value()) {
			BopeMessage.send_client_message("Value test: 0x0" + BopeSlider.get_slider_float("FloatValue").get_value());
		}
	}

}