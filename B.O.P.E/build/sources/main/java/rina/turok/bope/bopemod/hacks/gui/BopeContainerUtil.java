package rina.turok.bope.bopemod.hacks.gui;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
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
public class BopeContainerUtil extends BopeModule {
	BopeSetting transparent = create("Transparent", "ContainerUtilTransparent", 255, 0, 255);

	public BopeContainerUtil() {
		super(BopeCategory.BOPE_GUI);

		// Info.
		this.name        = "Container Util";
		this.tag         = "ContainerUtil";
		this.description = "Modify container inventory, anvil...";

		// Release or launch the module. release(String tag); -> You can place what you want in tag.
		release("B.O.P.E - Module - B.O.P.E");
	}
}