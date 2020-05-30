package rina.turok.bope.bopemod.guiscreen.hud;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

import com.mojang.realmsclient.gui.ChatFormatting;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 06/05/20.
*
*/
public class BopeGappleCount extends BopePinnable {
	int gapples = 0;

	public BopeGappleCount() {
		super("Gapple Count", "GappleCount", 1, 0, 0);
	}

	@Override
	public void render() {
		if (mc.player != null) {
			if (is_on_gui()) {
				background();
			}

			GlStateManager.pushMatrix();
			RenderHelper.enableGUIStandardItemLighting();

			gapples = mc.player.inventory.mainInventory.stream().filter(stack -> stack.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::getCount).sum();

			int off = 0;

			for (int i = 0; i < 45; i++) {
				ItemStack stack = mc.player.inventory.getStackInSlot(i);
				ItemStack off_h = mc.player.getHeldItemOffhand();

				if (off_h.getItem() == Items.GOLDEN_APPLE) {
					off = off_h.stackSize;
				} else {
					off = 0;
				}

				if (stack.getItem() == Items.GOLDEN_APPLE) {
					mc.getRenderItem().renderItemAndEffectIntoGUI(stack, get_x(), get_y());
					
					create_line(Integer.toString(gapples + off), 16 + 2, 16 - get(Integer.toString(gapples + off), "height"));
				}
			}

			mc.getRenderItem().zLevel = 0.0f;

			RenderHelper.disableStandardItemLighting();		
			
			GlStateManager.popMatrix();

			set_width(16 + get(Integer.toString(gapples) + off, "width") + 2);
			set_height(16);
		}
	}
}