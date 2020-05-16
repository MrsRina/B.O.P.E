package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.block.BlockObsidian;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockAir;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// External.
import rina.turok.bope.external.BopeEventCancellable;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 13/05/20.
*
*/
public class BopeSorround extends BopeModule {
	BopeSetting smoth           = create("Smoth", "SorroundSmoth", true);
	BopeSetting slider_mode     = create("Blocks", "SorroundBlocks", 3, 3, 4);
	BopeSetting ticks           = create("Ticks", "SorroundTicks", 0, 0, 10);
	BopeSetting placing_tick    = create("Placing Tick", "SorroundPlacingTick", 13, 1, 30);
	BopeSetting blocks_per_tick = create("Blocks Per Tick", "SorroundBlocksPerTick", 4, 1, 6);

	int places_tick = 0; 
	int place_tick  = 0;
	int wait_tick   = 0;
	int new_slot    = 0;
	int old_slot    = 0;

	boolean sneak = false;
	boolean verify;
	boolean missing;

	Vec3d[] sorround_with_8_blocks = {
		new Vec3d(  1,   0,   0),
		new Vec3d(  0,   0,   1),
		new Vec3d(- 1,   0,   0),
		new Vec3d(  0,   0, - 1),
		new Vec3d(  1, - 1,   0),
		new Vec3d(  0, - 1,   1),
		new Vec3d(- 1, - 1,   0),
		new Vec3d(  0, - 1, - 1)
	};

	Vec3d[] sorround_with_9_blocks = {
		new Vec3d(  1,   0,   0),
		new Vec3d(  0,   0,   1),
		new Vec3d(- 1,   0,   0),
		new Vec3d(  0,   0, - 1),
		new Vec3d(  1, - 1,   0),
		new Vec3d(  0, - 1,   1),
		new Vec3d(- 1, - 1,   0),
		new Vec3d(  0, - 1, - 1),
		new Vec3d(  0, - 1,   0)
	};

	public BopeSorround() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Sorround"; 
		this.tag         = "Sorround";
		this.description = "A barrier into player with obsidian.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

//	@Override
//	public void update() {
//		if (mc.player != null && place_tick >= placing_tick.get_value(1)) {
//			if (!verify) {
//				if (wait_tick < ticks.get_value(1)) {
//					wait_tick++;
//					return;
//				} else {
//					wait_tick = 0;
//				}
//			}

//			if (verify) {
//				verify = false;

//				if (find_in_hotbar() == -1) {
//					disable();
//				}
//			}

//			Vec3d[] many_blocks = new Vec3d[0];

//			int blocks_length = 0;
//			int places        = 0;

//			if (slider_mode.get_value(1) >= 3) {
//				many_blocks   = sorround_with_8_blocks;
//				blocks_length = 8;
//			}

//			if (slider_mode.get_value(1) >= 4) {
//				many_blocks   = sorround_with_9_blocks;
//				blocks_length = 9;
//			}

//			while (places < blocks_per_tick.get_value(1)) {
//				if (places_tick > blocks_length) {
//					places_tick = 0;

//					break;
//				}

//				BlockPos off_place    = new BlockPos(many_blocks[places_tick]);
//				BlockPos target_place = new BlockPos(mc.payer.getPositionVector()).addBlock(off_place.x, off_place.y, off_place.z);


//				if (place_blocks(target_place)) {
//					places = 0;
//				}

//				places_tick++;
//			}
//		}
//	}

//	public void place_blocks(BlockPos pos) {
//		Block block = mc.world.getBlockState(pos).getBlock();

//		if (!(blocks_length instanceof BlockAir) && !(block instanceof BlockLiquid)) {
//			return false;
//		}

//		for (Entity entitys : mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos))) {
//			if (!(entitys instanceof EntityItem) && !(entitys instanceof EntityXPOrb)) {
//				return false;
//			}
//		}

//		EnumFacing side = get_placeale_side(pos);

//		if (side == null) {
//			return false;
//		}

//		BlockPos left_side = pos.offset(side);

//		EnumFacing opposite = side.getOpposite();

//		if (!(is_possible(left_side))) {
//			return false;
//		}

//		BlockPos vect = new Vec3d()
//	}

//	public EnumFacing get_placeale_side(BlockPos pos) {
//		for (EnumFacing sides : EnumFacing.values()) {
//			BlockPos left_side = pos.offSet(sides);

//			if (!(mc.world.getBlockState(left_side).getBlock().canCollideCheck(mc.world.getBlockState(left_side), false))) {
//				continue;
//			}

//			IBlockState state_block = mc.world.getBlockState(left_side);

//			if (!(state_block.getMaterial().isReplaceable())) {
//				return sides;
//			}
//		}

//		return null;
//	}
}