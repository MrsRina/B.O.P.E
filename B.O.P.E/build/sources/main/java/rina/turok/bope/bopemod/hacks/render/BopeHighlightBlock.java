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

			float r = 255;
			float g = 255;
			float b = 255;
			float a = 255;

			TurokRenderHelp.prepare(GL11.GL_QUADS);
			TurokRenderHelp.draw_cube(position_block, r, g, b, a);
		}

		TurokRenderHelp.release();
	}
}