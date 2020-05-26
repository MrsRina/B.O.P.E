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
			if (is_on_gui()) {
				create_rect(0, 0, this.get_width(), this.get_height(), 0, 0, 0, 50);
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
				mc.getRenderItem().renderItemAndEffectIntoGUI(helmet, this.get_x() + 48, this.get_y());
				mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, helmet, this.get_x() + 48, this.get_y(), "");				
			}

			if (chestplace != null) {
				mc.getRenderItem().renderItemAndEffectIntoGUI(chestplace, this.get_x() + 32, this.get_y());
				mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, chestplace, this.get_x() + 32, this.get_y(), "");
			}

			if (leggings != null) {
				mc.getRenderItem().renderItemAndEffectIntoGUI(leggings, this.get_x() + 16, this.get_y());
				mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, leggings, this.get_x() + 16, this.get_y(), "");
			}

			if (boots != null) {
				mc.getRenderItem().renderItemAndEffectIntoGUI(boots, this.get_x(), this.get_y());
				mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, boots, this.get_x(), this.get_y(), "");
			}

			this.set_height(19);
			this.set_width(width);

			mc.getRenderItem().zLevel = 0.0f;

			RenderHelper.disableStandardItemLighting();		
			
			GlStateManager.popMatrix();
		}
	}
}