package rina.turok.bope.bopemod.guiscreen.hud;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 28/05/20.
*
*/
public class BopeStoragePreview extends BopePinnable {
	List<Block> possibles_archives = Arrays.asList(
		Blocks.TRAPPED_CHEST,
		Blocks.ENDER_CHEST,
		Blocks.CHEST
	);

	public BopeStoragePreview() {
		super(ChatFormatting.DARK_RED + "Storage Preview", "StoragePreview", 1, 0, 0);
	}

//	@Override
//	public void render() {
//		if (mc.player != null && mc.world != null) {
//			// Draw default background.
//			if (is_on_gui()) {
//				background();
//			}
//
//			// Somes settings.
//			set_width(16 * 9);
//			set_height(16 * 3);
//
//			// Get ray trace result.
//			RayTraceResult result = mc.objectMouseOver;
//
//			// If result is not none.
//			if (result != null) {
//				// If equals block.
//				if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
//					BlockPos    block_pos   = result.getBlockPos();
//					IBlockState block_state = mc.world.getBlockState(block_pos);
//					Block       block       = block_state.getBlock();
//
//					for (TileEntity entity : mc.world.loadedTileEntityList) {
//						if (entity instanceof TileEntityShulkerBox && entity.getPos() == block_pos) {
//							TileEntityShulkerBox shulker = (TileEntityShulkerBox) entity;
//
//							NonNullList<ItemStack> storage = shulker.getItems();
//
//							draw_as_inventory(storage);
//						}
//					}
//				}
//			}
//		}
//	}
//
//	public void draw_as_inventory(NonNullList<ItemStack> storage) {
//		// Draw.
//		GlStateManager.pushMatrix();
//		RenderHelper.enableGUIStandardItemLighting();
//
//		// Draw default background.
//		background();
//
//		// Get items from size.
//		for (int i = 0; i < storage.size(); i++) {
//			ItemStack item_stack = storage.get(i);
//
//			// Possition in item.
//			int item_position_x = (int) get_x() + (i % 9) * 16;
//			int item_position_y = (int) get_y() + (i / 9) * 16;
//					
//			// Draw the items.
//			mc.getRenderItem().renderItemAndEffectIntoGUI(item_stack, item_position_x, item_position_y);
//			mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, item_stack, item_position_x, item_position_y, null);
//		}
//
//		// zLevel.
//		mc.getRenderItem().zLevel = - 5.0f;
//
//		// Release.
//		RenderHelper.disableStandardItemLighting();			
//			
//		GlStateManager.popMatrix();
//	}
}