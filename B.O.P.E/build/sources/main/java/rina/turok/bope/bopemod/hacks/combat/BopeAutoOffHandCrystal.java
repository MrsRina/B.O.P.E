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
	BopeSetting disable_anot = create("Disable Totem & Gapple", "AutoOffhandCrystalDisableOffhandModules", true);
	BopeSetting slider_smart = create("Smart", "AutoOffhandCrystalSmart", 2, 1, 18);

	boolean find_crystal = false;
	boolean move_crystal = false;
	boolean is_smart_ev  = false;

	String last = "default";

	int crystal_stack;

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
		if ((Bope.get_module_manager().get_module_with_tag("AutoTotem").is_active() || Bope.get_module_manager().get_module_with_tag("AutoGapple").is_active()) && disable_anot.get_value(true)) {
			Bope.get_module_manager().get_module_with_tag("AutoTotem").set_active(false);

			if (Bope.get_setting_manager().get_setting_with_tag("AutoGapple", "AutoGappleDisableAutoTotem").get_value(true)) {
				Bope.get_setting_manager().get_setting_with_tag("AutoGapple", "AutoGappleDisableAutoTotem").set_value(false);
				Bope.get_module_manager().get_module_with_tag("AutoGapple").set_active(false);
				Bope.get_setting_manager().get_setting_with_tag("AutoGapple", "AutoGappleDisableAutoTotem").set_value(true);
			} else {
				Bope.get_module_manager().get_module_with_tag("AutoGapple").set_active(false);
			}
		}
	}

	@Override
	public void disable() {
		if (is_smart_ev) {
			Bope.get_module_manager().get_module_with_tag("AutoTotem").set_active(true);

			is_smart_ev = false;
		}
	}

	@Override
	public void update() {
		if (mc.player != null && mc.world != null) {
			// If health is < of limit smart, disable and start event is_smart_ev.
			if (mc.player.getHealth() <= slider_smart.get_value(1) * 2) {
				is_smart_ev = true;

				set_active(false);
			}

			// Get crystal stack.
			crystal_stack = mc.player.inventory.mainInventory.stream().filter(item -> item.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::getCount).sum();

			// Ignore if in inventory.
			if (mc.currentScreen instanceof GuiContainer) {
				return;
			}

			// If find gapples.
			if (find_crystal) {
				int gapples = - 1;

				// Search on stack.
				for (int items = 0; items < 45; items++) {
					if (mc.player.inventory.getStackInSlot(items).isEmpty) {
						gapples = items;

						break;
					}
				}

				if (gapples == - 1) {
					return;
				}

				// Move to off hand.
				mc.playerController.windowClick(0, gapples < 9 ? gapples + 36 : gapples, 0, ClickType.PICKUP, mc.player);

				find_crystal = false;
			}

			if (mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
				if (move_crystal) {
					mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);

					move_crystal = false;

					if (!mc.player.inventory.itemStack.isEmpty()) {
						find_crystal = true;
					}

					return;
				}

				if (mc.player.inventory.itemStack.isEmpty()) {
					if (crystal_stack == 0) {
						return;
					}

					int item = - 1;

					for (int i = 0; i < 45; i++) {
						if (mc.player.inventory.getStackInSlot(i).getItem() == Items.END_CRYSTAL) {
							item = i;

							break;
						}
					}

					if (item == - 1) {
						return;
					}

					// Move.
					mc.playerController.windowClick(0, item < 9 ? item + 36 : item, 0, ClickType.PICKUP, mc.player);

					move_crystal = true;
				}
			}
		}
	}
}