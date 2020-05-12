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
public class BopeAutoGapple extends BopeModule {
	BopeSetting auto_totem_d = create("Disable Auto Totem", "AutoGappleDisableAutoTotem", true);
	BopeSetting slider_smart = create("Smart", "AutoGappleSmart", 2, 1, 18);

	boolean find_gapple = false;
	boolean move_gapple = false;
	boolean is_smart_ev = false;

	int gapple_stack;

	public BopeAutoGapple() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Auto Gapple";
		this.tag         = "AutoGapple";
		this.description = "Automatically replace in offhand gapple stack.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void disable() {
		// If auto disable totem on, just desactive with the actual value.
		if (auto_totem_d.get_value(true)) {
			Bope.get_module_manager().get_module_with_tag("AutoTotem").set_active(!is_active());
		} else {
			// If the smast on active totem.
			if (is_smart_ev) {
				Bope.get_module_manager().get_module_with_tag("AutoTotem").set_active(!is_active());

				is_smart_ev = false;
			}
		}
	}

	@Override
	public void enable() {
		if (auto_totem_d.get_value(true)) {
			// Disable auto totem.
			Bope.get_module_manager().get_module_with_tag("AutoTotem").set_active(!is_active());
		}
	}

	@Override
	public void update() {
		if (mc.player != null && mc.world != null) {
			// If health is < of limit smart, disable and start event is_smart_ev.
			if (mc.player.getHealth() <= slider_smart.get_value(1) * 2) {
				is_smart_ev = true;

				set_active(!is_active());
			}

			// Get gapple stack.
			gapple_stack = mc.player.inventory.mainInventory.stream().filter(item -> item.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::getCount).sum();

			// Ignore if in inventory.
			if (mc.currentScreen instanceof GuiContainer) {
				return;
			}

			// If find gapples.
			if (find_gapple) {
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

				find_gapple = false;
			}

			if (mc.player.getHeldItemOffhand().getItem() != Items.GOLDEN_APPLE) {
				if (move_gapple) {
					mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);

					move_gapple = false;

					if (!mc.player.inventory.itemStack.isEmpty()) {
						find_gapple = true;
					}

					return;
				}

				if (mc.player.inventory.itemStack.isEmpty()) {
					if (gapple_stack == 0) {
						return;
					}

					int item = - 1;

					for (int i = 0; i < 45; i++) {
						if (mc.player.inventory.getStackInSlot(i).getItem() == Items.GOLDEN_APPLE) {
							item = i;

							break;
						}
					}

					if (item == - 1) {
						return;
					}

					// Move.
					mc.playerController.windowClick(0, item < 9 ? item + 36 : item, 0, ClickType.PICKUP, mc.player);

					move_gapple = true;
				}
			}
		}
	}
}