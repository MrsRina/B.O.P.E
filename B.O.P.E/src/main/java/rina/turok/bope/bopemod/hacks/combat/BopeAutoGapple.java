package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Util.
import rina.turok.bope.bopemod.util.BopeUtilItem;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 07/05/20.
*
*/
public class BopeAutoGapple extends BopeModule {
	BopeSetting absolute     = create("Absolute", "AutoGappleAbsolute", true);
	BopeSetting enable_totem = create("Auto Enable Totem", "AutoGappleEnableAutoTotem", true);
	BopeSetting slider_smart = create("Smart", "AutoGappleSmart", 2, 1, 18);

	boolean is_smart_ev = false;

	int gapple_stack;
	int last_slot;

	public BopeAutoGapple() {
		super(BopeCategory.BOPE_COMBAT, true);

		// Info.
		this.name        = "Auto Gapple";
		this.tag         = "AutoGapple";
		this.description = "Automatically replace in offhand gapple stack.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void enable() {
		last_slot = -1;

		boolean totem   = false;
		boolean crystal = false;

		if (absolute.get_value(true)) {
			if (Bope.module_is_active("AutoTotem")) {
				totem = true;

				Bope.get_module("AutoTotem").set_disable();
			}

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

			if (totem || crystal) {
				StringBuilder message = new StringBuilder();

				if (totem && crystal) {
					message.append(Bope.dg + "AutoTotem & AutoOffhandCrystal" + Bope.r + " has been " + Bope.re + "disabled");
				} else if (totem) {
					message.append(Bope.dg + "AutoTotem" + Bope.r + " has been " + Bope.re + "disabled");
				} else if (crystal) {
					message.append(Bope.dg + "AutoOffhandCrystal" + Bope.r + " has been " + Bope.re + "disabled");
				}

				BopeMessage.send_client_message(message.toString());
			}
		}
	}

	@Override
	public void disable() {
		if (enable_totem.get_value(true)) {
			if (!Bope.module_is_active("AutoTotem")) {
				Bope.get_module("AutoTotem").set_active(true);
			}
		}
	}

	@Override
	public void update() {
		// 18 * 2 = 36.
		if (mc.player != null && mc.world != null) {
			gapple_stack = mc.player.inventory.mainInventory.stream().filter(item -> item.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();

			if (mc.currentScreen instanceof GuiContainer) {
				return;
			}

			if (mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE && mc.player.getHealth() <= slider_smart.get_value(1) * 2) {
				is_smart_ev = true;

				set_active(false);
			} else if (mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
				return;
			}

			int gapple_slot = BopeUtilItem.get_item_slot(Items.GOLDEN_APPLE);

			if (gapple_slot == -1) {
				return;
			}

			if (gapple_slot < 9) {
				last_slot = gapple_slot;
			}

			BopeUtilItem.set_offhand_item(gapple_slot);
		}
	}
}