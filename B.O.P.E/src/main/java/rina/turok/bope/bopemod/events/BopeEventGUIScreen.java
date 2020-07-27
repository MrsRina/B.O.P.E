package rina.turok.bope.bopemod.events;

import net.minecraft.client.gui.GuiScreen;

// External.
import rina.turok.bope.external.BopeEventCancellable;

/**
 * @author Rina
 *
 * Created by Rina.
 * 08/04/20.
 *
 */
public class BopeEventGUIScreen extends BopeEventCancellable {
	private final GuiScreen guiscreen;

	public BopeEventGUIScreen(GuiScreen screen) {
		super();

		guiscreen = screen;
	}

	public GuiScreen get_guiscreen() {
		return guiscreen;
	}
}