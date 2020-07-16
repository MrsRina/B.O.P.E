package rina.turok.bope.bopemod.hacks.render;

import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.awt.Color;
import java.util.*;

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
	BopeSetting rgb = create("RGB Effect", "HoleColorRGBEffect", true);

	BopeSetting r = create("R", "HoleColorR", 255, 0, 255);
	BopeSetting g = create("G", "HoleColorG", 255, 0, 255);
	BopeSetting b = create("B", "HoleColorB", 255, 0, 255);
	BopeSetting a = create("A", "HoleColorA", 100, 0, 255);

	BopeSetting line_a = create("Outline A", "HoleColorLineOutlineA", 255, 0, 255);

	BopeSetting off_set = create("Off Set Y", "HoleColorOffSetY", 0.2, 0.0, 1.0);
	BopeSetting range   = create("Range", "HoleColorRange", 6, 1, 8);

	ArrayList<BlockPos> hole;

	boolean safe = false;

	boolean outline = false;
	boolean solid   = false;
	boolean docking = false;

	int color_r;
	int color_g;
	int color_b;

	public BopeHoleColor() {
		super(BopeCategory.BOPE_RENDER);

		// Info.
		this.name        = "Hole Color";
		this.tag         = "HoleColor";
		this.description = "It verify the holes and draw.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void update() {
		if (hole == null) {
			hole = new ArrayList<>();
		} else {
			hole.clear();
		}

		if (mc.player != null || mc.world != null) {
			int colapso_range = (int) Math.ceil(range.get_value(1));

			List<BlockPos> spheres = sphere(player_as_blockpos(), colapso_range, colapso_range);

			for (BlockPos pos : spheres) {
				if (!mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
					continue;
				}

				if (!mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
					continue;
				}

				if (!mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
					continue;
				}

				boolean possible = true;

				for (BlockPos seems_blocks : new BlockPos[] {
				/*  RinaRinaRinaRinaRina  */ new BlockPos( 0, -1,  0),
				/*  RinaRinaRinaRinaRina  */ new BlockPos( 0,  0, -1),
				/*  RinaRinaRinaRinaRina  */ new BlockPos( 1,  0,  0),
				/*  RinaRinaRinaRinaRina  */ new BlockPos( 0,  0,  1),
				/*  RinaRinaRinaRinaRina  */ new BlockPos(-1,  0,  0)
				}) {
					Block block = mc.world.getBlockState(pos.add(seems_blocks)).getBlock();

					if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
						possible = false;

						break;
					}
				}

				if (possible) {
					hole.add(pos);
				}
			}
		}
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

		float off_set_h = 0;

		if (hole != null && !hole.isEmpty() || safe != false) {
			off_set_h = (float) off_set.get_value(1.0);

			for (BlockPos holes : hole) {
				TurokRenderHelp.prepare("quads");
				TurokRenderHelp.draw_cube(TurokRenderHelp.get_buffer_build(),
					holes.x, holes.y, holes.z,
					1, off_set_h, 1,
					color_r, color_g, color_b, a.get_value(1),
					"all"
				);

				TurokRenderHelp.release();

				TurokRenderHelp.prepare("lines");
				TurokRenderHelp.draw_cube_line(TurokRenderHelp.get_buffer_build(),
					holes.x, holes.y, holes.z,
					1, off_set_h, 1,
					color_r, color_g, color_b, line_a.get_value(1),
					"all"
				);

				TurokRenderHelp.release();
			}
		}
	}

	// KAMI. :")
	public List<BlockPos> sphere(BlockPos pos, float r, int h) {
		boolean hollow = false;
		boolean sphere = true;

		int plus_y = 0;

		List<BlockPos> sphere_block = new ArrayList<BlockPos>();

		int cx = pos.getX();
		int cy = pos.getY();
		int cz = pos.getZ();

		for (int x = cx - (int)r; x <= cx + r; ++x) {
			for (int z = cz - (int)r; z <= cz + r; ++z) {
				for (int y = sphere ? (cy - (int)r) : cy; y < (sphere ? (cy + r) : ((float)(cy + h))); ++y) {
					double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
					if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
						BlockPos spheres = new BlockPos(x, y + plus_y, z);

						sphere_block.add(spheres);
					}
				}
			}
		}

		return sphere_block;
	}

	public BlockPos player_as_blockpos() {
		return new BlockPos(Math.floor((double) mc.player.posX), Math.floor((double) mc.player.posY), Math.floor((double) mc.player.posZ));
	}
}