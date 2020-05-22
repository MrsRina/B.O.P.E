package rina.turok.bope.bopemod.hacks.render;

import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;

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
* 18/05/2020. -> 00:25 AM. :sunglass:
*
*/
public class BopeHighlight extends BopeModule {
	BopeSetting mode = create("Mode", "HighlightMode", "Pretty", combobox("Pretty", "Solid", "Outline"));
	
	BopeSetting rgb = create("RGB Effect", "HighlightRGBEffect", true);
	
	BopeSetting r = create("R", "HighlightR", 255, 0, 255);
	BopeSetting g = create("G", "HighlightG", 255, 0, 255);
	BopeSetting b = create("B", "HighlightB", 255, 0, 255);
	BopeSetting a = create("A", "HighlightA", 100, 0, 255);
	
	BopeSetting l_a = create("Outline A", "HighlightLineA", 255, 0, 255);

	int color_r;
	int color_g;
	int color_b;

	boolean outline = false;
	boolean solid   = false;

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
	public void disable() {
		outline = false;
		solid   = false;
	}

	@Override
	public void render(BopeEventRender event) {
		if (mc.player != null && mc.world != null) {
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
	
			if (mode.in("Pretty")) {
				outline = true;
				solid   = true;
			}
	
			if (mode.in("Solid")) {
				outline = false;
				solid   = true;
			}
	
			if (mode.in("Outline")) {
				outline = true;
				solid   = false;
			}
	
			RayTraceResult result = mc.objectMouseOver;
	
			if (result != null) {
				if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
					BlockPos block_pos = result.getBlockPos();
	
					// Solid.
					if (solid) {
						TurokRenderHelp.prepare("quads");
						TurokRenderHelp.draw_cube(block_pos, color_r, color_g, color_b, a.get_value(1), "all");
						TurokRenderHelp.release();
					}
	
					// Outline.
					if (outline) {
						TurokRenderHelp.prepare("lines");
						TurokRenderHelp.draw_cube_line(block_pos, color_r, color_g, color_b, l_a.get_value(1), "all");
						TurokRenderHelp.release();
					}
				}
			}
		}
	}
}