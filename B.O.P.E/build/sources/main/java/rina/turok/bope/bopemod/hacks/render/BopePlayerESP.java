package rina.turok.bope.bopemod.hacks.render;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;

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

// Util.
import rina.turok.bope.bopemod.util.BopeUtilRenderer;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 05/06/2020.
*
*/
public class BopePlayerESP extends BopeModule {
	BopeSetting render_1 = create("Render Entity", "PlayerESPRenderEntity", "chams", combobox("chams", "outline", "disabled"));
	BopeSetting render_2 = create("Render Entity 2D", "PlayerESPRenderEntity2D", "csgo", combobox("csgo", "rect", "disabled"));
	BopeSetting disp     = create("Distance Render", "PlayerESPDistanceRender", 6, 0, 10);
	BopeSetting range    = create("Range", "PlayerESPRange", 200, 0, 200);

	public static float distance_player = 0.0f;

	public BopePlayerESP() {
		super(BopeCategory.BOPE_RENDER);

		// Info.
		this.name        = "Player ESP";
		this.tag         = "PlayerESP";
		this.description = "Player ESP - Extra Sensory Perception.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");	
	}

	@Override
	public void render(BopeEventRender event) {
		mc.world.loadedEntityList.stream()
		/* inaRinaRinaRinaRin */ .filter(entity -> entity instanceof EntityLivingBase)
		/* inaRinaRinaRinaRin */ .filter(entity -> entity != mc.player)
		/* inaRinaRinaRinaRin */ .map(entity -> (EntityLivingBase) entity)
		/* inaRinaRinaRinaRin */ .filter(entity -> !entity.isDead)
		/* inaRinaRinaRinaRin */ .filter(entity -> entity instanceof EntityPlayer)
		/* inaRinaRinaRinaRin */ .filter(entity -> mc.player.getDistance(entity) < (range.get_value(1)))
		/* inaRinaRinaRinaRin */ .filter(entity -> distance_player > disp.get_value(1))
		/* inaRinaRinaRinaRin */ .sorted(Comparator.comparing(entity -> -mc.player.getDistance(entity)))
		/* inaRinaRinaRinaRin */ .forEach(entities -> {
			EntityPlayer player_entities = (EntityPlayer) entities;

			if (render_2.in("csgo")) {
				if (Bope.get_friend_manager().is_friend(player_entities.getName())) {
					BopeUtilRenderer.EntityPlayerCSGOESP((Entity) entities, Bope.client_r, Bope.client_g, Bope.client_b, Math.round(mc.player.getDistance(entities) * 25.5f));
				} else {
					BopeUtilRenderer.EntityPlayerCSGOESP((Entity) entities, 190, 190, 190, Math.round(mc.player.getDistance(entities) * 25.5f));
				}
			}

			if (render_2.in("rect")) {
				if (Bope.get_friend_manager().is_friend(player_entities.getName())) {
					BopeUtilRenderer.EntityPlayerRectESP((Entity) entities, Bope.client_r, Bope.client_g, Bope.client_b, Math.round(mc.player.getDistance(entities) * 25.5f));
				} else {
					BopeUtilRenderer.EntityPlayerRectESP((Entity) entities, 190, 190, 190, Math.round(mc.player.getDistance(entities) * 25.5f));
				}
			}
		});
	}
}