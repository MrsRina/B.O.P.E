package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Modules.
import rina.turok.bope.bopemod.hacks.combat.BopeAutoGapple;
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
public class BopeAutoOffHandCrystal extends BopeModule {
	BopeSetting absolute     = create("Absolute", "AutoOffhandCrystalAbsolute", true);
	BopeSetting enable_totem = create("Auto Enable Totem", "AutoOffhandCrystalEnableAutoTotem", true);
	BopeSetting slider_smart = create("Smart", "AutoOffhandCrystalSmart", 2, 1, 18);

	boolean is_smart_ev  = false;

	String last = "default";

	int crystal_stack;
	int last_slot;

	public BopeAutoOffHandCrystal() {
		super(BopeCategory.BOPE_COMBAT, true);

		// Info.
		this.name        = "Auto Off Hand Crystal";
		this.tag         = "AutoOffHandCrystal";
		this.description = "Automatically replace in offhand crystal.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void enable() {
		last_slot = -1;

		boolean totem  = false;
		boolean gapple = false;

		if (absolute.get_value(true)) {
			if (Bope.module_is_active("AutoTotem")) {
				totem = true;

				Bope.get_module("AutoTotem").set_disable();
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

			if (totem || gapple) {
				StringBuilder message = new StringBuilder();

				if (totem && gapple) {
					message.append(Bope.dg + "AutoTotem & AutoGapple" + Bope.r + " has been " + Bope.re + "disactived");
				} else if (totem) {
					message.append(Bope.dg + "AutoTotem" + Bope.r + " has been " + Bope.re + "disactived");
				} else if (gapple) {
					message.append(Bope.dg + "AutoGapple" + Bope.r + " has been " + Bope.re + "disactived");
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
			crystal_stack = mc.player.inventory.mainInventory.stream().filter(item -> item.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();

			if (mc.currentScreen instanceof GuiContainer) {
				return;
			}

			if (mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL && mc.player.getHealth() <= slider_smart.get_value(1) * 2) {
				is_smart_ev = true;

				set_active(false);
			} else if (mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
				return;
			}

			int crystal_slot = BopeUtilItem.get_item_slot(Items.END_CRYSTAL);

			if (crystal_slot == -1) {
				return;
			}

			if (crystal_slot < 9) {
				last_slot = crystal_slot;
			}

			BopeUtilItem.set_offhand_item(crystal_slot);
		}
	}
}