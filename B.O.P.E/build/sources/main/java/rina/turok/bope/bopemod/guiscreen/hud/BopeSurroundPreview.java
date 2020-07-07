package rina.turok.bope.bopemod.guiscreen.hud;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.block.BlockObsidian;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemStack;
import net.minecraft.block.BlockAir;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Util.
import rina.turok.bope.bopemod.util.BopeUtilEntity;
import rina.turok.bope.bopemod.util.BopeUtilItem;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 07/06/20.
*
*/
public class BopeSurroundPreview extends BopePinnable {
	int face = 0;

	public BopeSurroundPreview() {
		super("Surround Preview", "SurroundPreview", 1, 0, 0);
	}

	@Override
	public void render() {
		if (mc.world != null) {
			if (is_on_gui()) {
				background();

				create_line("S", 0 + 16, 0 + 16 + 16, "no dock");
				create_line("W", 0, 0 + 16, "no dock");
				create_line("N", 0 + 16, 0, "no dock");
				create_line("E", 0 + 16 + 16, 0 + 16, "no dock");
			}

			sync_face();

			float partial_tick = Bope.get_module_manager().get_module_with_tag("SystemEventRender0").value_int_0();

			// Vec 3.
			Vec3d vec = BopeUtilEntity.get_interpolated_entity(mc.player, partial_tick);

			// Block pos as player.
			BlockPos player_pos = new BlockPos(vec.x, vec.y, vec.z);

			// Positions.
			BlockPos[] positions = {
				player_pos.south(),
				player_pos.west(),
				player_pos.north(),
				player_pos.east()
			};

			if (!is_touch(positions[0])) {
				render_surround(positions[0], 0);
			}

			if (!is_touch(positions[1])) {
				render_surround(positions[1], 1);
			}

			if (!is_touch(positions[2])) {
				render_surround(positions[2], 2);
			}

			if (!is_touch(positions[0])) {
				render_surround(positions[3], 3);
			}

			set_width(16 + 16 + 16);
			set_height(16 + 16 + 16);
		}
	}

	public void sync_face() {
		switch (MathHelper.floor((double) (mc.player.rotationYaw * 8.0F / 360.0F) + 0.5D) & 7) {
			case 0:
				face = 0;

				break;
			case 2:
				face = 1;

				break;
			case 4:
				face = 2;

				break;
			case 6:
				face = 3;

				break;
		}
	}

	public void render_surround(BlockPos position, int side) {
		GlStateManager.pushMatrix();
		RenderHelper.enableGUIStandardItemLighting();

		Block block = mc.world.getBlockState(position).getBlock();

		int off;

		if (side == face) {
			off = 0;
		} else {
			off = 0;
		}

		if (side == 0 && ((block == Blocks.BEDROCK) || (block == Blocks.OBSIDIAN))) {
			ItemStack item_to_render = new ItemStack(block);

			mc.getRenderItem().renderItemAndEffectIntoGUI(item_to_render, get_x() + 16, get_y() + 16 + 16 + off);
		}

		if (side == 1 && ((block == Blocks.BEDROCK) || (block == Blocks.OBSIDIAN))) {
			ItemStack item_to_render = new ItemStack(block);

			mc.getRenderItem().renderItemAndEffectIntoGUI(item_to_render, get_x() - off, get_y() + 16);
		}

		if (side == 2 && ((block == Blocks.BEDROCK) || (block == Blocks.OBSIDIAN))) {
			ItemStack item_to_render = new ItemStack(block);

			mc.getRenderItem().renderItemAndEffectIntoGUI(item_to_render, get_x() + 16, get_y() - off);
		}

		if (side == 3 && ((block == Blocks.BEDROCK) || (block == Blocks.OBSIDIAN))) {
			ItemStack item_to_render = new ItemStack(block);

			mc.getRenderItem().renderItemAndEffectIntoGUI(item_to_render, get_x() + 16 + 16 + off, get_y() + 16);
		}

		mc.getRenderItem().zLevel = - 5.0f;

		RenderHelper.disableStandardItemLighting();			
			
		GlStateManager.popMatrix();
	}

	public boolean is_touch(BlockPos pos) {
		Block block = mc.world.getBlockState(pos).getBlock();

		return (block instanceof BlockAir || block instanceof BlockLiquid) && mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos)).isEmpty();
	}
}