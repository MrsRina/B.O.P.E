package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;

import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.*;

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
public class BopeKillAura extends BopeModule {
	BopeSetting player  = create("Player", "KillAuraPlayer", true);
	BopeSetting hostile = create("Hostile", "KillAuraHostile", false);
	BopeSetting sword   = create("Sword", "KillAuraSword", true);
	BopeSetting range   = create("Range", "KillAuraRange", 6, 1, 10);

	boolean with_sword = true;

	EnumHand actual_hand = EnumHand.MAIN_HAND;

	double tick = 0.0;

	public BopeKillAura() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Kill Aura"; 
		this.tag         = "KillAura";
		this.description = "To able hit enemies in a range.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void update() {
		if (mc.world != null && mc.player != null) {
			for (Entity entity : mc.world.loadedEntityList) {
				if (entity != mc.player) {
					if (mc.player.getDistance(entity) <= range.get_value(1)) {
						try {
							who_attack(entity);
						} catch (Exception exc) {}
					}
				}
			}	
		}
	}

	public void who_attack(Entity entity) {
		if ((entity instanceof EntityPlayer && player.get_value(true) && !Bope.get_friend_manager().is_friend(entity.getName())) || (entity instanceof IMob && hostile.get_value(true))) {
			if (mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && sword.get_value(true)) {
				attack_entity(entity);
			} else if (!sword.get_value(true)) {
				attack_entity(entity);
			}
		}
	}

	public void attack_entity(Entity entity) {
		if (mc.player.getCooledAttackStrength(0) >= 1) {
			ItemStack off_hand_item = mc.player.getHeldItemOffhand();

			if (off_hand_item != null && off_hand_item.getItem() == Items.SHIELD) {
				mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
			}

			mc.playerController.attackEntity(mc.player, entity);
			mc.player.swingArm(actual_hand);
		}
	}
}