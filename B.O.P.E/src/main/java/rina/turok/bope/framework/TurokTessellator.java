package rina.turok.bope.framework;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.BlockPos;

import org.lwjgl.opengl.GL11;

import rina.turok.bope.framework.TurokGL;

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
		TurokGL.prepare(); // Prepare.

		INSTANCE.getBuffer().begin(mode, DefaultVertexFormats.POSITION_COLOR);
	}

	public static void release() {
		INSTANCE.draw();

		TurokGL.release(); // Buffer //
	}

	public static void prepare_gl() {
		TurokGL.prepare();
	}

	public static void release_gl() {
		TurokGL.release();
	}
}