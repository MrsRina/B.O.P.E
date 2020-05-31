package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.MathHelper;
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
import net.minecraft.init.Blocks;

import java.util.*;

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
* - For the first version is it, I have REFERENCED like KAMI.
* - I did somes things to be better.
*
*/
public class BopeSorround extends BopeModule {
	BopeSetting blocks_per_tick = create("Blocks Per Tick", "SorroundBlocksPerTick", 4,  1, 6);
	BopeSetting timeout_tick    = create("Time Out Tick", "SorroundTimeOutTick", 10, 0, 30);
	BopeSetting tick            = create("Tick", "SorroundTick", 2,  0, 10);

	int places_tick = 0;
	int place_tick  = 0;
	int wait_tick   = 0;
	int new_slot    = 0;
	int old_slot    = 0;

	boolean sneak = false;
	boolean verify;
	boolean missing;

	Vec3d[] mask = {
		new Vec3d( 1,  0,  0),
		new Vec3d( 0,  0,  1),
		new Vec3d(-1,  0,  0),
		new Vec3d( 0,  0, -1),
		new Vec3d( 1, -1,  0),
		new Vec3d( 0, -1,  1),
		new Vec3d(-1, -1,  0),
		new Vec3d( 0, -1, -1)
	};

	List<Block> not_true_blocks = Arrays.asList(
		Blocks.ENCHANTING_TABLE,
		Blocks.CRAFTING_TABLE,
		Blocks.BREWING_STAND,
		Blocks.TRAPPED_CHEST,
		Blocks.ENDER_CHEST,
		Blocks.DISPENSER,
		Blocks.TRAPDOOR,
		Blocks.DROPPER,
		Blocks.HOPPER,
		Blocks.CHEST,
		Blocks.ANVIL
	);

	List<Block> not_true_entitys_blocks = Arrays.asList(
		Blocks.LIGHT_BLUE_SHULKER_BOX,
		Blocks.MAGENTA_SHULKER_BOX,
		Blocks.PURPLE_SHULKER_BOX,
		Blocks.YELLOW_SHULKER_BOX,
		Blocks.ORANGE_SHULKER_BOX,
		Blocks.SILVER_SHULKER_BOX,
		Blocks.WHITE_SHULKER_BOX,
		Blocks.BLACK_SHULKER_BOX,
		Blocks.BROWN_SHULKER_BOX,
		Blocks.GREEN_SHULKER_BOX,
		Blocks.LIME_SHULKER_BOX,
		Blocks.PINK_SHULKER_BOX,
		Blocks.CYAN_SHULKER_BOX,
		Blocks.GRAY_SHULKER_BOX,
		Blocks.BLUE_SHULKER_BOX,
		Blocks.RED_SHULKER_BOX
	);

	public BopeSorround() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Sorround"; 
		this.tag         = "Sorround";
		this.description = "A barrier into player with obsidian.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void enable() {
		if (mc.player != null) {
			verify = true;

			old_slot = mc.player.inventory.currentItem;
			new_slot = - 1;
		}
	}

	@Override
	public void disable() {
		if (mc.player != null) {
			if (new_slot != old_slot && old_slot != - 1) {
				mc.player.inventory.currentItem = old_slot;
			}

			if (sneak) {
				mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));

				sneak = false;
			}

			old_slot = - 1;
			new_slot = - 1;

			missing = false;
		}
	}

	@Override
	public void update() {
		if (mc.player != null) {
			if (place_tick >= timeout_tick.get_value(1)) {
				place_tick = 0;

				set_active(!is_active());

				return;
			}

			if (!verify) {
				if (wait_tick < tick.get_value(1)) {
					wait_tick++;
					return;
				} else {
					wait_tick = 0;
				}
			}

			if (verify) {
				verify = false;

				if (find_in_hotbar() == -1) {
					missing = true;
				}
			}

			Vec3d[] many_blocks = mask;

			int blocks_length = mask.length;
			int places        = 0;

			while (places < blocks_per_tick.get_value(1)) {
				if (places_tick >= blocks_length) {
					places_tick = 0;

					break;
				}

				BlockPos off_place    = new BlockPos(many_blocks[places_tick]);
				BlockPos target_place = new BlockPos(mc.player.getPositionVector()).add(off_place.x, off_place.y, off_place.z);

				if (place_blocks(target_place)) {
					places++;
				}

				places_tick++;
			}

			if (places > 0) {
				if (new_slot != old_slot && old_slot != - 1) {
					mc.player.inventory.currentItem = old_slot;

					new_slot = old_slot;
				}

				if (sneak) {
					mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));

					sneak = false;
				}
			}

			place_tick++;

			if (missing) {
				missing = false;

				set_active(!is_active());
			}
		}
	}

	public boolean place_blocks(BlockPos pos) {
		Block block = mc.world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
			return false;
		}

		for (Entity entitys : mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos))) {
			if (!(entitys instanceof EntityItem) && !(entitys instanceof EntityXPOrb)) {
				return false;
			}
		}

		EnumFacing side = get_placeale_side(pos);

		if (side == null) {
			return false;
		}

		BlockPos left_side = pos.offset(side);

		EnumFacing opposite = side.getOpposite();

		if (!(is_possible(left_side))) {
			return false;
		}

		Vec3d hit = new Vec3d(left_side).add(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));

		Block left_block = mc.world.getBlockState(left_side).getBlock();

		int obi_slot = find_in_hotbar();

		if (obi_slot == - 1) {
			missing = true;

			return false;
		}

		if (new_slot != obi_slot) {
			mc.player.inventory.currentItem = obi_slot;

			new_slot = obi_slot;
		}

		if (!(sneak && not_true_blocks.contains(left_block) || not_true_entitys_blocks.contains(left_block))) {
			mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));

			sneak = true;
		}

		reset_rotate(hit);

		mc.playerController.processRightClickBlock(mc.player, mc.world, left_side, opposite, hit, EnumHand.MAIN_HAND);
		mc.player.swingArm(EnumHand.MAIN_HAND);

		mc.rightClickDelayTimer = 4;

		return true;
	}

	public void reset_rotate(Vec3d pos) {
		float[] rotations = legit_rotation(pos);

		mc.player.connection.sendPacket(new CPacketPlayer.Rotation(rotations[0], rotations[1], mc.player.onGround));
	}

	public float[] legit_rotation(Vec3d pos) {
		Vec3d eye_pos = get_eye_pos();

		double diff_x = pos.x - eye_pos.x;
		double diff_y = pos.y - eye_pos.y;
		double diff_z = pos.z - eye_pos.z;

		double diff_x_z = Math.sqrt(diff_x * diff_x + diff_z * diff_z);

		float player_yaw   = (float)   Math.toDegrees(Math.atan2(diff_z, diff_x)) - 90f;
		float player_pitch = (float) - Math.toDegrees(Math.atan2(diff_y, diff_x_z));

		return new float[] {
			mc.player.rotationYaw   + MathHelper.wrapDegrees(player_yaw   - mc.player.rotationYaw),
			mc.player.rotationPitch + MathHelper.wrapDegrees(player_pitch - mc.player.rotationPitch)
		};
	}

	public Vec3d get_eye_pos() {
		return new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ);
	}

	public EnumFacing get_placeale_side(BlockPos pos) {
		for (EnumFacing sides : EnumFacing.values()) {
			BlockPos left_side = pos.offset(sides);

			if (!(mc.world.getBlockState(left_side).getBlock().canCollideCheck(mc.world.getBlockState(left_side), false))) {
				continue;
			}

			IBlockState state_block = mc.world.getBlockState(left_side);

			if (!(state_block.getMaterial().isReplaceable())) {
				return sides;
			}
		}

		return null;
	}

	public int find_in_hotbar() {
		int slot = - 1;

		for (int i = 0; i < 9; i++) {
			ItemStack stack = mc.player.inventory.getStackInSlot(i);

			if (stack != ItemStack.EMPTY) {
				if (stack.getItem() instanceof ItemBlock) {
					Block type_block = ((ItemBlock) stack.getItem()).getBlock();

					if (type_block instanceof BlockObsidian) {
						slot = i;

						break;
					}
				}
			}
		}

		return slot;
	}

	public Block get_block(BlockPos pos) {
		return get_state(pos).getBlock();
	}

	public IBlockState get_state(BlockPos pos) {
		return mc.world.getBlockState(pos);
	}

	public boolean is_possible(BlockPos pos) {
		return get_block(pos).canCollideCheck(get_state(pos), false);
	}
}