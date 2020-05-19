package rina.turok.turok.draw;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.BlockPos;

import org.lwjgl.opengl.GL11;

import java.util.*;

// Draw.
import rina.turok.turok.draw.TurokGL;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class TurokRenderHelp extends Tessellator {
	public static TurokRenderHelp INSTANCE = new TurokRenderHelp();

	public TurokRenderHelp() {
		super(0x200000);
	}

	public static void prepare(int mode) {
		prepare_gl();

		INSTANCE.getBuffer().begin(mode, DefaultVertexFormats.POSITION_COLOR);
	}

	public static void release() {
		INSTANCE.draw();

		release_gl();
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

	public static void draw_cube(BlockPos block_pos, int r, int g, int b, int a, String faces) {
		draw_cube(block_pos.x, block_pos.y, block_pos.z, 1, 1, 1, r, g, b, a, faces);
	}

	public static void draw_cube(BlockPos block_pos, int w, int h, int d, int r, int g, int b, int a, String faces) {
		draw_cube(block_pos.x, block_pos.y, block_pos.z, w, h, d, r, g, b, a, faces);
	}

	public static void draw_cube(int x, int y, int z, int r, int g, int b, int a, String faces) {
		draw_cube(x, y, z, 1, 1, 1, r, g, b, a, faces);
	}

	public static void draw_cube(int x, int y, int z, int w, int h, int d, int r, int g, int b, int a, String faces) {
		BufferBuilder buffer = INSTANCE.getBuffer();

		if (((boolean) Arrays.asList(faces.split("-")).contains("down"))) {
			buffer.pos(x + w, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y, z).color(r, g, b, a).endVertex();
		}

		if (((boolean) Arrays.asList(faces.split("-")).contains("up"))) {
			buffer.pos(x + w, y + h, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y + h, z + d).color(r, g, b, a).endVertex();
		}

		if (((boolean) Arrays.asList(faces.split("-")).contains("north"))) {
			buffer.pos(x + w, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y + h, z).color(r, g, b, a).endVertex();
		}

		if (((boolean) Arrays.asList(faces.split("-")).contains("south"))) {
			buffer.pos(x, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
		}

		if (((boolean) Arrays.asList(faces.split("-")).contains("west"))) {
			buffer.pos(x, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
		}

		if (((boolean) Arrays.asList(faces.split("-")).contains("east"))) {
			buffer.pos(x + w, y, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y + h, z).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y + h, z + d).color(r, g, b, a).endVertex();
		}
	}
}