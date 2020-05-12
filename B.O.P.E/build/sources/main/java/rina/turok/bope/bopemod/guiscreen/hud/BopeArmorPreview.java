package rina.turok.bope.bopemod.guiscreen.hud;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;

import com.mojang.realmsclient.gui.ChatFormatting;

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

			NonNullList<ItemStack> reverse = mc.player.inventory.armorInventory;

			for (ItemStack armor : reverse) {
				if (armor.isEmpty()) {
					continue;
				}

				int update_position_item_x = this.get_x() + width_compare;

				mc.getRenderItem().zLevel = 200f;
				mc.getRenderItem().renderItemAndEffectIntoGUI(armor, update_position_item_x, this.get_y());
				mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, armor, update_position_item_x, this.get_y(), "");

				width_compare += 16;

				this.set_height(19);
				this.set_width(64 + 2); // 16 * 4;
			}

			mc.getRenderItem().zLevel = 0.0f;

			RenderHelper.disableStandardItemLighting();		
			
			GlStateManager.popMatrix();
		}
	}
}