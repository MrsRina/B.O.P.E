package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.Item;

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
* 11/05/20.
*
*/
public class BopeFastUtil extends BopeModule {
	BopeSetting place   = create("Place",   "BopeFastPlace",   true);
	BopeSetting break_  = create("Break",   "BopeFastBreak",   true);
	BopeSetting use     = create("Use",     "BopeFastUse",     true);
	BopeSetting crystal = create("Crystal", "BopeFastCrystal", true);
	BopeSetting exp     = create("EXP",     "BopeFastExp",     true);

	public BopeFastUtil() {
		super(BopeCategory.BOPE_COMBAT);

		this.name        = "Fast Util"; 
		this.tag         = "FastUtil";
		this.description = "Fast util for events and things in Minecraft.";
	}

	@Override
	public void update() {
		Item main = mc.player.getHeldItemMainhand().getItem();
		Item off  = mc.player.getHeldItemOffhand().getItem();

		boolean main_exp = main instanceof ItemExpBottle;
		boolean off_exp  = off instanceof ItemExpBottle;
		boolean main_cry = main instanceof ItemEndCrystal;
		boolean off_cry  = off instanceof ItemEndCrystal;

		if (main_exp | off_exp && exp.get_value(true)) {
			mc.rightClickDelayTimer = 0;
		}

		if (main_cry | off_cry && crystal.get_value(true)) {
			mc.rightClickDelayTimer = 0;
		}

		if (!(main_exp | off_exp | main_cry | off_cry) && place.get_value(true)) {
			mc.rightClickDelayTimer = 0;
		}

		if (break_.get_value(true)) {
			mc.playerController.blockHitDelay = 0;
		}
	}
}