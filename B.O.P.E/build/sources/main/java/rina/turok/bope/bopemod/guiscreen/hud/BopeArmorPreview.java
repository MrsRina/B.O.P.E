package rina.turok.bope.bopemod.guiscreen.hud;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina.
*
* Created by Rina.
* 09/05/2020.
*
*/
public class BopeArmorPreview extends BopePinnable {
	public BopeArmorPreview() {
		super("Armor Preview", "ArmorPreview", 1, 0, 0);
	}

	@Override
	public void render() {
		if (mc.player != null) {
			// Draw background color.
			if (is_on_gui()) {
				background();
			}

			GlStateManager.pushMatrix();
			RenderHelper.enableGUIStandardItemLighting();

			int width_compare = 2;
			int width         = 64;

			InventoryPlayer inventory = mc.player.inventory;

			ItemStack boots      = inventory.armorItemInSlot(0);
			ItemStack leggings   = inventory.armorItemInSlot(1);
			ItemStack chestplace = inventory.armorItemInSlot(2);
			ItemStack helmet     = inventory.armorItemInSlot(3);

			mc.getRenderItem().zLevel = 200f;

			if (helmet != null) {
				mc.getRenderItem().renderItemAndEffectIntoGUI(helmet, get_x() + 48, get_y());
				mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, helmet, get_x() + 48, get_y(), "");				
			}

			if (chestplace != null) {
				mc.getRenderItem().renderItemAndEffectIntoGUI(chestplace, get_x() + 32, get_y());
				mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, chestplace, get_x() + 32, get_y(), "");
			}

			if (leggings != null) {
				mc.getRenderItem().renderItemAndEffectIntoGUI(leggings, get_x() + 16, get_y());
				mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, leggings, get_x() + 16, get_y(), "");
			}

			if (boots != null) {
				mc.getRenderItem().renderItemAndEffectIntoGUI(boots, get_x(), get_y());
				mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, boots, get_x(), get_y(), "");
			}

			set_height(19);
			set_width(width);

			mc.getRenderItem().zLevel = 0.0f;

			RenderHelper.disableStandardItemLighting();		
			
			GlStateManager.popMatrix();
		}
	}
}