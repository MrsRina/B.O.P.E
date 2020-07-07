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
public class BopeUtilMath {
	public static final Minecraft mc = Minecraft.getMinecraft();

	// kamo.
	public static double[] calcule_look_at(double px, double py, double pz, EntityPlayer player) {
		double diff_x = player.posX - px;
		double diff_y = player.posY - py;
		double diff_z = player.posZ - pz;

		double dif_xyz = Math.sqrt(diff_x * diff_x + diff_y * diff_y + diff_z * diff_z);

		double pitch = Math.asin(diff_y);
		double yaw   = Math.atan2(diff_z, diff_x);

		pitch = pitch * 180.0d / Math.PI;
		yaw   = yaw   * 180.0d / Math.PI;

		yaw += 90f;

		return new double[] {
			yaw, pitch
		};
	}

	public static float[] legit_rotation(Vec3d pos) {
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

	public static Vec3d get_eye_pos() {
		return new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ);
	}

	// Seppuku.
	public static double[] movement_speed(double speed) {
		float forward = mc.player.movementInput.moveForward;
		float side    = mc.player.movementInput.moveStrafe;
		float yaw     = mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
	
		if (forward != 0) {
			if (side > 0) {
				yaw += (forward > 0 ? -45 : 45);
			} else if (side < 0) {
				yaw += (forward > 0 ? 45 : -45);
			}

			side = 0;

			if (forward > 0) {
				forward = 1;
			} else if (forward < 0) {
				forward = -1;
			}
		}

		final double sin   = Math.sin(Math.toRadians(yaw + 90));
		final double cos   = Math.cos(Math.toRadians(yaw + 90));
		final double pos_x = (forward * speed * cos + side * speed * sin);
		final double pos_z = (forward * speed * sin - side * speed * cos);

		return new double[] {
			pos_x,
			pos_z
		};
	}
}