package rina.turok.bope.bopemod.util;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.MathHelper;
import net.minecraft.block.BlockObsidian;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockAir;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

/**
 * @author Rina.
 *
 * Created by Rina.
 * 10/06/20.
 *
 **/
public class BopeUtilItem {
	public static final Minecraft mc = Minecraft.getMinecraft();
}