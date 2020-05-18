package rina.turok.turok.draw;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.BlockPos;

import org.lwjgl.opengl.GL11;

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
		TurokGL.prepare(); // Prepare.

		INSTANCE.getBuffer().begin(mode, DefaultVertexFormats.POSITION_COLOR);
	}

	public static void release() {
		TurokGL.release(); // Buffer //

		INSTANCE.draw();
	}

	public static void prepare_gl() {
		TurokGL.prepare();
	}

	public static void release_gl() {
		TurokGL.release();
	}

	public static void draw_cube(BlockPos pos, float r, float g, float b, float a, String faces) {
		BufferBuilder buffer = INSTANCE.getBuffer();

		float w = 1;
		float h = 1;
		float d = 1;

		float x = pos.x;
		float y = pos.y;
		float z = pos.z;

		if (((boolean) Arrays.asList(type.split("-")).contains("down"))) {
			buffer.pos(x + w, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y, z).color(r, g, b, a).endVertex();
		}

		if (((boolean) Arrays.asList(type.split("-")).contains("up"))) {
			buffer.pos(x + w, y + h, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y + h, z + d).color(r, g, b, a).endVertex();
		}

		if (((boolean) Arrays.asList(type.split("-")).contains("north"))) {
			buffer.pos(x + w, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y + h, z).color(r, g, b, a).endVertex();
		}

		if (((boolean) Arrays.asList(type.split("-")).contains("south"))) {
			buffer.pos(x, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
		}

		if (((boolean) Arrays.asList(type.split("-")).contains("west"))) {
			buffer.pos(x, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x, y, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x, y + h, z).color(r, g, b, a).endVertex();
		}

		if (((boolean) Arrays.asList(type.split("-")).contains("east"))) {
			buffer.pos(x + w, y, z + d).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y, z).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y + h, z).color(r, g, b, a).endVertex();
			buffer.pos(x + w, y + h, z + d).color(r, g, b, a).endVertex();
		}
	}
}