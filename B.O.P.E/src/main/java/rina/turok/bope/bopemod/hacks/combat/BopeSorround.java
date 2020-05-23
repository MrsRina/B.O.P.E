package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.MathHelper;
import net.minecraft.inventory.Container;
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
*/
public class BopeSorround extends BopeModule {
	public BopeSorround() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Sorround"; 
		this.tag         = "Sorround";
		this.description = "A barrier into player with obsidian.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}
}