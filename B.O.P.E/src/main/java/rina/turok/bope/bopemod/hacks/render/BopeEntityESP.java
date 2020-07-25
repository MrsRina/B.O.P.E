package rina.turok.bope.bopemod.hacks.render;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.item.EntityEnderCrystal;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
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
public class BopeEntityESP extends BopeModule {
	BopeSetting hostile  = create("Hostile", "EntityESPHostile", true);
	BopeSetting pigs_ani = create("Animals & Pigies", "EntityESPAnimals", true);
	BopeSetting crystal  = create("Crystals", "EntityESPCrystal", true);
	BopeSetting item     = create("Items", "EntityESPItems", true);
	BopeSetting render_1 = create("Render Entity", "EntityESPRenderEntity", "Chams", combobox("Chams", "Outline", "Disabled"));
	BopeSetting disp     = create("Distance Render", "EntityESPDistanceRender", 6, 0, 10);
	BopeSetting range    = create("Range", "EntityESPRange", 200, 0, 200);

	public static float distance_player = 0.0f;

	public BopeEntityESP() {
		super(BopeCategory.BOPE_RENDER);

		// Info.
		this.name        = "Entity ESP";
		this.tag         = "EntityESP";
		this.description = "Entity ESP - Extra Sensory Perception.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");	
	}

	@Override
	public void render(BopeEventRender event) {
		for (Entity entity : mc.world.loadedEntityList) {
			if (entity != mc.player && mc.player.getDistance(entity) < (range.get_value(1)) && distance_player > disp.get_value(1)) {
				if (entity instanceof EntityItem && item.get_value(true)) {
					entity.setGlowing(true);
				} else if ((entity instanceof EntityItem && !item.get_value(true))) {
					entity.setGlowing(false);
				}
			}
		}
	}

	@Override
	public void disable() {
		for (Entity entity : mc.world.loadedEntityList) {
			if (entity instanceof EntityItem && item.get_value(true)) {
				entity.setGlowing(false);
			}
		}
	}
}