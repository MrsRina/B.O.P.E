package rina.turok.bope.bopemod.hacks.movement;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.gui.GuiChat;

import org.lwjgl.input.Keyboard;

import java.util.*;

// Zero alpine.
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Events.
import rina.turok.bope.bopemod.events.BopeEventMove;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Modify: Rina.
 *
 * Created by Rina.
 * 09/05/20.
 *
 */
public class BopeInventoryWalk extends BopeModule {
	public BopeInventoryWalk() {
		super(BopeCategory.BOPE_MOVEMENT);

		// Info.
		this.name        = "Inventory Walk";
		this.tag         = "InventoryWalk";
		this.description = "Inventory walk.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void update() {
		if (mc.player != null && mc.world != null) {
			if (mc.currentScreen instanceof GuiChat || mc.currentScreen == null) {
				return;
			}

			for (int keys : new int[] {
				mc.gameSettings.keyBindForward.getKeyCode(),
				mc.gameSettings.keyBindLeft.getKeyCode(),
				mc.gameSettings.keyBindRight.getKeyCode(),
				mc.gameSettings.keyBindBack.getKeyCode(),
				mc.gameSettings.keyBindJump.getKeyCode()
			}) {
				if (Keyboard.isKeyDown(keys)) {
					KeyBinding.setKeyBindState(keys, true);
				} else {
					KeyBinding.setKeyBindState(keys, false);
				}
			}
		}
	}
}