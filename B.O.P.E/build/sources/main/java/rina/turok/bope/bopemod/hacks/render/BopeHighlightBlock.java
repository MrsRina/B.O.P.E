package rina.turok.bope.bopemod.hacks.render;

import net.minecraft.util.math.RayTraceResult;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import rina.turok.bope.bopemod.hacks.BopeCategory.Category;
import rina.turok.bope.bopemod.events.BopeEventRender;
import rina.turok.bope.bopemod.backgui.BopeSlider;
import rina.turok.bope.framework.TurokRenderHelp;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

public class BopeHighlightBlock extends BopeModule {
	public BopeHighlightBlock() {
		super(
			"Highlight",
			"highlight",
			"Draw a block into split block.",
			Keyboard.KEY_G,
			Category.BOPE_RENDER
		);

		BopeSlider.create_new_float_slider("Color Red", "highlight_color_red", 50.0f, 0.0f, 255.0f);
		BopeSlider.create_new_float_slider("Color Green", "highlight_color_green", 255.0f, 0.0f, 255.0f);
		BopeSlider.create_new_float_slider("Color Blue", "highlight_color_blue", 255.0f, 0.0f, 255.0f);
		BopeSlider.create_new_float_slider("Color Blue", "highlight_color_alpha", 255.0f, 0.0f, 255.0f);
	}

	RayTraceResult split;

	@Override
	public void onDisable() {
		BopeMessage.send_client_message("Tessellator OFF");
	}

	@Override
	public void onEnable() {
		BopeMessage.send_client_message("Tessellator ON");
	}

	@Override
	public void onUpdate() {
		if (mc.player == null || mc.world == null) {
			return;
		}

		split = mc.objectMouseOver;

		if (split == null) {
			return;
		}
	}

	@Override
	public void onWorldRender(BopeEventRender render) {
		if (split != null || mc.world == null || mc.player == null) {
			return;
		}

		if (split.typeOfHit == RayTraceResult.Type.BLOCK) {
			BlockPos position_block = split.getBlockPos();

			float r = BopeSlider.get_float_slider("highlight_color_red").get_value();
			float g = BopeSlider.get_float_slider("highlight_color_green").get_value();
			float b = BopeSlider.get_float_slider("highlight_color_blue").get_value();
			float a = BopeSlider.get_float_slider("highlight_color_alpha").get_value();

			TurokRenderHelp.prepare(GL11.GL_QUADS);
			TurokRenderHelp.draw_cube(position_block, r, g, b, a);
		}

		TurokRenderHelp.release();
	}
}