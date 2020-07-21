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
import net.minecraft.block.BlockAir;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static org.lwjgl.opengl.GL11.*;

// Util.
import rina.turok.bope.bopemod.util.BopeUtilEntity;

// Bope.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.draw.TurokRenderHelp;

/**
 * @author Rina.
 *
 * Created by Rina.
 * 15/07/20.
 *
 * - Used like reference KAMI utils.
 *
 **/
public class BopeUtilRenderer {
	public static final Minecraft mc = Minecraft.getMinecraft();

	public static void EntityCSGOESP(Entity entities, int r, int g, int b, int a) {
		if (mc.world != null && mc.getRenderManager().options != null) {
			boolean is_third_person_view = mc.getRenderManager().options.thirdPersonView == 2;

			float view_yaw = mc.getRenderManager().playerViewY;

			TurokRenderHelp.prepare_gl(0.5f);

			GlStateManager.pushMatrix();

			Vec3d pos = BopeUtilEntity.get_interpolated_entity(entities, mc.getRenderPartialTicks());

			GlStateManager.translate(pos.x - mc.getRenderManager().renderPosX, pos.y - mc.getRenderManager().renderPosY, pos.z - mc.getRenderManager().renderPosZ);
			GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
			GlStateManager.rotate(-view_yaw, 0.0f, 1.0f, 0.0f);
			GlStateManager.rotate((float) (is_third_person_view ? -1 : 1), 1.0f, 0.0f, 0.0f);

			glColor4f(1, 1, 1, 0.5f);
			glLineWidth(2f);
			glEnable(GL_LINE_SMOOTH);

			if (entities instanceof EntityPlayer) {
				glColor4f((float) r / 255, (float) g / 255, (float) b / 255, (float) a / 255);

				glBegin(GL_LINE_LOOP);
				glVertex2d(-entities.width, 0);
				glVertex2d(-entities.width, entities.height / 3);
				glVertex2d(-entities.width, 0);
				glVertex2d((-entities.width / 3) * 2, 0);
				glEnd();

				glBegin(GL_LINE_LOOP);
				glVertex2d(-entities.width, entities.height);
				glVertex2d((-entities.width / 3) * 2, entities.height);
				glVertex2d(-entities.width, entities.height);
				glVertex2d(-entities.width, (entities.height / 3) * 2);
				glEnd();

				glBegin(GL_LINE_LOOP);
				glVertex2d(entities.width, entities.height);
				glVertex2d((entities.width / 3) * 2, entities.height);
				glVertex2d(entities.width, entities.height);
				glVertex2d(entities.width, (entities.height / 3) * 2);
				glEnd();

				glBegin(GL_LINE_LOOP);
				glVertex2d(entities.width, 0);
				glVertex2d((entities.width / 3) * 2, 0);
				glVertex2d(entities.width, 0);
				glVertex2d(entities.width, entities.height / 3);
				glEnd();
			}

			TurokRenderHelp.release_gl();
			GlStateManager.popMatrix();

			glColor4f(1, 1, 1, 1);
		}
	}

	public static void EntityRectESP(Entity entities, int r, int g, int b, int a) {
		if (mc.world != null && mc.getRenderManager().options != null) {
			boolean is_third_person_view = mc.getRenderManager().options.thirdPersonView == 2;

			float view_yaw = mc.getRenderManager().playerViewY;

			TurokRenderHelp.prepare_gl(0.5f);

			GlStateManager.pushMatrix();

			Vec3d pos = BopeUtilEntity.get_interpolated_entity(entities, mc.getRenderPartialTicks());

			GlStateManager.translate(pos.x - mc.getRenderManager().renderPosX, pos.y - mc.getRenderManager().renderPosY, pos.z - mc.getRenderManager().renderPosZ);
			GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
			GlStateManager.rotate(-view_yaw, 0.0f, 1.0f, 0.0f);
			GlStateManager.rotate((float) (is_third_person_view ? -1 : 1), 1.0f, 0.0f, 0.0f);

			glColor4f(1, 1, 1, 0.5f);
			glLineWidth(2f);
			glEnable(GL_LINE_SMOOTH);

			if (entities instanceof EntityPlayer) {
				glColor4f((float) r / 255, (float) g / 255, (float) b / 255, (float) a / 255);

				glEnable(GL_LINE_SMOOTH);
				glBegin(GL_LINE_LOOP);
				glVertex2d(-entities.width / 2, 0);
				glVertex2d(-entities.width / 2, entities.height);
				glVertex2d(entities.width / 2, entities.height);
				glVertex2d(entities.width / 2, 0);
				glEnd();
			}

			TurokRenderHelp.release_gl();
			GlStateManager.popMatrix();

			glColor4f(1, 1, 1, 1);
		}
	}

	public static void EntityTracerESP(Entity entities, int r, int g, int b, int a) {
		if (mc.world != null && mc.getRenderManager().options != null) {}
	}
}