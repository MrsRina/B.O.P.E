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

public class BopeHighlight extends BopeModule {
	BopeSetting rgb = create("RGB Effect", "HighlightRGBEffect", true);
	BopeSetting r   = create("R", "HighlightR", 255, 1, 255);
	BopeSetting g   = create("G", "HighlightG", 255, 1, 255);
	BopeSetting b   = create("B", "HighlightB", 255, 1, 255);

	int color_r;
	int color_g;
	int color_b;

	public BopeHighlight() {
		super(BopeCategory.BOPE_RENDER);

		// Info.
		this.name        = "Highlight";
		this.tag         = "Highlight";
		this.description = "Highlight block, no?";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void render(BopeEventRender event) {
		float[] tick_color = {
			(System.currentTimeMillis() % (360 * 32)) / (360f * 32)
		};

		int color_rgb = Color.HSBtoRGB(tick_color[0], 1, 1);

		if (rgb.get_value(true)) {
			color_r = ((color_rgb >> 16) & 0xFF);
			color_g = ((color_rgb >> 8) & 0xFF);
			color_b = (color_rgb & 0xFF);

			r.set_value(color_r);
			g.set_value(color_g);
			b.set_value(color_b);
		} else {
			color_r = r.get_value(1);
			color_g = g.get_value(2);
			color_b = b.get_value(3);
		}

		RayTraceResult result = mc.objectMouseOver;

		if (result != null) {
			if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos block_pos = result.getBlockPos();

				// Prepare.
				TurokRenderHelp.prepare(GL11.GL_QUADS);

				// Draw;
				TurokRenderHelp.draw_cube(block_pos, color_r, color_g, color_b, 255, "down-up-north-south-west-east");

				// Release;
				TurokRenderHelp.release();
			}
		}
	}
}