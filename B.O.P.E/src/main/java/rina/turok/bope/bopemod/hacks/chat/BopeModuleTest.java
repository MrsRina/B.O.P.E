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
			"Module Test",
			"ModuleTest",
			"Dev module.",
			Keyboard.KEY_Y,
			BopeCategory.Category.BOPE_CHAT
		);

		BopeButton.create_new_button("Button Test 1", "Start", false);
		BopeSlider.create_new_double_slider("Color R", "Slider_r", 5d, 1, 20);
		BopeSlider.create_new_double_slider("Color G", "Slider_g", 5d, 1, 20);
		BopeSlider.create_new_double_slider("Color B", "Slider_b", 5d, 1, 20);
	}

	@Override
	public void onUpdate() {}
}