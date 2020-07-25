package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;
import rina.turok.bope.bopemod.BopeMessage;

// Util.
import rina.turok.bope.bopemod.util.BopeUtilItem;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 07/05/20.
*
*/
public class BopeAutoTotem extends BopeModule {
	BopeSetting absolute      = create("Absolutedisabled", "AutoTotemAbsolute", true);
	BopeSetting slider_health = create("Health", "AutoTotemHealth", 18, 1, 18);

	int totem_count;
	int last_slot;

	public BopeAutoTotem() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Auto Totem";
		this.tag         = "AutoTotem";
		this.description = "Automatically replace in offhand totem when used.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void enable() {
		last_slot = -1;

		if (absolute.get_value(true)) {
			boolean crystal = false;
			boolean gapple  = false;

			if (Bope.module_is_active("AutoOffhandCrystal")) {
				boolean cancel = false;

				crystal = true;

				if (Bope.get_setting("AutoOffhandCrystal", "AutoOffhandCrystalEnableAutoTotem").get_value(true)) {
					Bope.get_setting("AutoOffhandCrystal", "AutoOffhandCrystalEnableAutoTotem").set_value(false);
				
					cancel = true;
				}

				Bope.get_module("AutoOffhandCrystal").set_disable();

				if (cancel) {
					Bope.get_setting("AutoOffhandCrystal", "AutoOffhandCrystalEnableAutoTotem").set_value(true);
				}
			}

			if (Bope.module_is_active("AutoGapple")) {
				boolean cancel = false;

				gapple = true;

				if (Bope.get_setting("AutoGapple", "AutoGappleEnableAutoTotem").get_value(true)) {
					Bope.get_setting("AutoGapple", "AutoGappleEnableAutoTotem").set_value(false);
				
					cancel = true;
				}

				Bope.get_module("AutoGapple").set_disable();

				if (cancel) {
					Bope.get_setting("AutoGapple", "AutoGappleEnableAutoTotem").set_value(true);
				}
			}

			if (gapple || crystal) {
				StringBuilder message = new StringBuilder();

				if (gapple && crystal) {
					message.append(Bope.dg + "AutoGapple & AutoOffhandCrystal" + Bope.r + " has been " + Bope.re + "disabled");
				} else if (gapple) {
					message.append(Bope.dg + "AutoGapple" + Bope.r + " has been " + Bope.re + "disabled");
				} else if (crystal) {
					message.append(Bope.dg + "AutoOffhandCrystal" + Bope.r + " has been " + Bope.re + "disabled");
				}

				BopeMessage.send_client_message(message.toString());
			}
		}
	}

	@Override
	public void update() {
		// 18 * 2 = 36.
		if (mc.player != null && mc.world != null && mc.player.getHealth() <= slider_health.get_value(1) * 2) {
			totem_count = mc.player.inventory.mainInventory.stream().filter(item -> item.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();

			if (mc.currentScreen instanceof GuiContainer) {
				return;
			}

			if (mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
				return;
			}

			int totem_slot = BopeUtilItem.get_item_slot(Items.TOTEM_OF_UNDYING);

			if (totem_slot == -1) {
				return;
			}

			BopeUtilItem.set_offhand_item(totem_slot);
		}
	}
}