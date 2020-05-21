package rina.turok.bope.bopemod.hacks.render;

import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;

import org.lwjgl.opengl.GL11;

import java.awt.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Events.
import rina.turok.bope.bopemod.events.BopeEventRender;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Turok.
import rina.turok.turok.draw.TurokRenderHelp;

/**
* @author Rina
*
* Created by Rina.
* 20/05/2020.
*
*/
public class BopeHoleColor extends BopeModule {
	public BopeHoleColor() {
		super(BopeCategory.BOPE_RENDER);

		// Info.
		this.name        = "Hole Color";
		this.tag         = "HoleColor";
		this.description = "It verify the holes and draw.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}
}