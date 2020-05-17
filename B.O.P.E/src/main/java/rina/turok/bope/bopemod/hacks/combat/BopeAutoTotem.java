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
	BopeSetting button_inventory = create("Inventory", "AutoTotemInventory", false);
	BopeSetting slider_health    = create("Health",    "AutoTotemHealth",    18, 1, 18);

	boolean find_totem = false;
	boolean move_totem = false;

	int totem_count;

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
	public void update() {
		// 18 * 2 = 36.
		if (mc.player != null && mc.world != null && mc.player.getHealth() <= slider_health.get_value(1) * 2) {
			totem_count = mc.player.inventory.mainInventory.stream().filter(item -> item.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();

			if (mc.currentScreen instanceof GuiContainer && !button_inventory.get_value(true)) {
				return;
			}

			if (find_totem) {
				int totem = - 1;

				for (int items = 0; items < 45; items++) {
					if (mc.player.inventory.getStackInSlot(items).isEmpty) {
						totem = items;

						break;
					}
				}

				if (totem == - 1) {
					return;
				}

				mc.playerController.windowClick(0, totem < 9 ? totem + 36 : totem, 0, ClickType.PICKUP, mc.player);

				find_totem = false;
			}

			if (mc.player.getHeldItemOffhand().getItem() != Items.TOTEM_OF_UNDYING) {
				if (move_totem) {
					mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);

					move_totem = false;

					if (!mc.player.inventory.itemStack.isEmpty()) {
						find_totem = true;
					}

					return;
				}

				if (mc.player.inventory.itemStack.isEmpty()) {
					if (totem_count == 0) {
						return;
					}

					int item = - 1;

					for (int i = 0; i < 45; i++) {
						if (mc.player.inventory.getStackInSlot(i).getItem() == Items.TOTEM_OF_UNDYING) {
							item = i;

							break;
						}
					}

					if (item == - 1) {
						return;
					}

					mc.playerController.windowClick(0, item < 9 ? item + 36 : item, 0, ClickType.PICKUP, mc.player);

					move_totem = true;
				}
			}
		}
	}
}