package rina.turok.bope.bopemod.hacks.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Events.
import rina.turok.bope.bopemod.events.BopeEventRender;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

/**
* @author Rina
*
* Created by Rina.
* 22/05/2020.
*
*/
public class BopeNameTag extends BopeModule {
	BopeSetting armor = create("Armor",  "NameTagArmor",      true);
	BopeSetting range = create("Range",  "NameTagRange",      10, 0, 24);
	BopeSetting size  = create("Size",   "NameTagSize",      2, 0, 4);
	BopeSetting smoth = create("Smooth", "NameTagSmoothFont", false);

	float partial_ticks = 0.0f;

	BopeDraw font = new BopeDraw(1);

	public BopeNameTag() {
		super(BopeCategory.BOPE_RENDER);

		// Info.
		this.name        = "Name Tag";
		this.tag         = "NameTag";
		this.description = "For see the items using of others players.";

		// Release.
		release("B.O.P.E - module - B.O.P.E"); 
	}

	@Override
	public void render(BopeEventRender event) {
		if (mc.player != null && mc.world != null) {
			partial_ticks = event.get_partial_ticks();

			mc.world.loadedEntityList.stream()
			/* inaRinaRinaRinaRin */ .filter( entity -> entity instanceof EntityLivingBase)
			/* inaRinaRinaRinaRin */ .filter( entity -> entity != mc.player)
			/* inaRinaRinaRinaRin */ .map(    entity -> (EntityLivingBase) entity)
			/* inaRinaRinaRinaRin */ .filter(_entity -> !_entity.isDead)
			/* inaRinaRinaRinaRin */ .filter( entity -> entity instanceof EntityPlayer)
			/* inaRinaRinaRinaRin */ .filter( entity -> mc.player.getDistance(entity) < range.get_value(1))
			/* inaRinaRinaRinaRin */ .sorted(Comparator.comparing(entity -> -mc.player.getDistance(entity)))
			/* inaRinaRinaRinaRin */ .forEach(this::draw);
		}

		// Release.
		GlStateManager.enableDepth();
		GlStateManager.depthMask(true);
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		GlStateManager.disableDepth();
		GlStateManager.enableCull();
		GlStateManager.glLineWidth(1);
		glColor3f(1, 1, 1);
	}

	public void draw(Entity entitys) {
		if (mc.getRenderManager().options != null) {
			boolean smooth      = smoth.get_value(true);
			boolean person_view = mc.getRenderManager().options.thirdPersonView == 2;

			float viewer_pitch = mc.getRenderManager().playerViewX;
			float viewer_yaw   = mc.getRenderManager().playerViewY;

			String name = entitys.getName() + " " + ChatFormatting.DARK_RED + Math.round(((EntityLivingBase) entitys).getHealth() + (entitys instanceof EntityPlayer ? ((EntityPlayer) entitys).getAbsorptionAmount() / 2 : 0));

			GlStateManager.pushMatrix();

			Vec3d pos = get_interpolated_entity(entitys, partial_ticks);

			double y = pos.y + (entitys.height + 1.0f - (entitys.isSneaking() ? 0.25f : 0.0f));

			GlStateManager.translate(pos.x - mc.getRenderManager().renderPosX, y - mc.getRenderManager().renderPosY, pos.z - mc.getRenderManager().renderPosZ);
			GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
			GlStateManager.rotate(-viewer_yaw, 0.0f, 1.0f, 0.0f);
			GlStateManager.rotate((float) (person_view ? -1 : 1) * viewer_pitch, 1.0f, 0.0f, 0.0f);
			GlStateManager.disableLighting();
			GlStateManager.depthMask(false);

			float distance = mc.player.getDistance(entitys);
			float scaling  = distance >= 4.0f ? (distance / 8f) * (float) (Math.pow(1.2589254f, size.get_value(1))) : (float) (Math.pow(1.2589254f, 0.5f));

			GlStateManager.scale(scaling, scaling, scaling);

			GlStateManager.scale(-0.025f, -0.025f, 0.025f);

			int colapse_x = font.get_string_width(name, smooth) / 2;

			GlStateManager.disableDepth();

			GlStateManager.enableBlend();

			GlStateManager.enableTexture2D();

			GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);

			font.draw_string(name, -colapse_x, 10, 255, 255, 255, smooth);

			if (armor.get_value(true) && entitys instanceof EntityPlayer) {
				GlStateManager.disableDepth();

				armor_draw((EntityPlayer) entitys, 0, 0);

				GlStateManager.enableDepth();
			}

			GlStateManager.glNormal3f(0.0f, 0.0f, 0.0f);
			glTranslatef(0, 20, 0);
	//
	//		{
	//			glVertex2d(-players.width / 2, 0);
	//			glVertex2d(-players.width / 2, e.height / 2);
	//			glVertex2d( players.width / 2, e.height / 2);
	//			glVertex2d( players.width / 2, 0);
	//		}
	//
			GlStateManager.scale(-40, -40, 40);
			GlStateManager.enableDepth();
			GlStateManager.popMatrix();
		}
	}

	public void armor_draw(EntityPlayer players, int player_x, int player_y) {
		InventoryPlayer inventory = players.inventory;

		ItemStack boots      = inventory.armorItemInSlot(0);
		ItemStack leggings   = inventory.armorItemInSlot(1);
		ItemStack chestplace = inventory.armorItemInSlot(2);
		ItemStack helmet     = inventory.armorItemInSlot(3);

		GlStateManager.pushMatrix();

		RenderHelper.enableGUIStandardItemLighting();

		mc.getRenderItem().zLevel = -100.0f;
		GlStateManager.disableDepth();

		if (helmet != null) {
			mc.getRenderItem().renderItemAndEffectIntoGUI(helmet, player_x, player_y);
			mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, helmet, player_x, player_y, "");				
		}

		GlStateManager.enableDepth();
		GlStateManager.scale(0.75f, 0.75f, 0.75f);

		mc.getRenderItem().zLevel = 0.0f;

		RenderHelper.disableStandardItemLighting();
		GlStateManager.enableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.disableLighting();
		GlStateManager.popMatrix();
	}

	public Vec3d process_interpolated_amount(Entity entity, double x, double y, double z) {
		return new Vec3d(
			(entity.posX - entity.lastTickPosX) * x,
			(entity.posY - entity.lastTickPosY) * y,
			(entity.posZ - entity.lastTickPosZ) * z
		);
	}

	public Vec3d get_interpolated_amout(Entity entity, float ticks) {
		return process_interpolated_amount(entity, ticks, ticks, ticks);
	}

	public Vec3d get_interpolated_entity(Entity entity, float ticks) {
		return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(get_interpolated_amout(entity, ticks));
	}
}