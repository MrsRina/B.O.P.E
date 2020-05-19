package rina.turok.bope.bopemod.hacks.render;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

/**
* @author Rina
*
* Created by Rina.
* 19/05/2020.
*
*/
public class BopePlayerESP extends BopeModule {
	BopeSetting outline = create("Outline", "PlayerESPOutline", true);

	public BopePlayerESP() {
		super(BopeCategory.BOPE_RENDER);

		// Info.
		this.name        = "Player ESP";
		this.tag         = "PlayerESP";
		this.description = "Draw effects into other's players location.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}
}