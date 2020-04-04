package rina.turok.bope.framework;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.BlockPos;

import org.lwjgl.opengl.GL11;

//
// Rina.
// Is not the same.
//
public class TurokTessellator extends Tessellator {
	public static TurokTessellator INSTANCE = new TurokTessellator();

	public TurokTessellator() {
		super(0x200000);
	}

	public static void prepare(int mode) {
		prepare_gl(); // Prepare.

		INSTANCE.getBuffer().begin(mode, DefaultVertexFormats.POSITION_COLOR);
	}

	public static void release() {
		INSTANCE.draw();

		release_gl(); // Buffer //
	}

	public static void prepare_gl() {
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.glLineWidth(1.5F);
		GlStateManager.disableTexture2D();
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		GlStateManager.disableDepth();
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.enableAlpha();
		GlStateManager.color(1, 1, 1);
	}

	public static void release_gl() {
		GlStateManager.enableCull();
		GlStateManager.depthMask(true);
		GlStateManager.enableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.enableDepth();
	}
}