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
	BopeSetting player    = create("Player",    "KillAuraPlayer",   true);
	BopeSetting hostile   = create("Hostile",   "KillAuraHostile",  false);
	BopeSetting sword     = create("Sword",     "KillAuraSword",    true);
	BopeSetting sync_tps  = create("Sync TPS",  "KillAuraSyncTps",  true);
	BopeSetting range     = create("Range",     "KillAuraRange",    5.0, 0.5, 6.0);

	boolean start_verify = true;

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
		if (mc.player != null && mc.world != null) {
			if (mc.player.isDead | mc.player.getHealth() <= 0) {
				return;
			}

			if (!(mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) && sword.get_value(true)) {
				start_verify = false;
			} else if ((mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) && sword.get_value(true)) {
				start_verify = true;
			} else if (sword.get_value(true) == false) {
				start_verify = true;
			}

			Entity entity = find_entity();

			if (entity != null && start_verify) {
				// Tick.
				float tick_to_hit  = 20.0f - Bope.get_event_handler().get_tick_rate();

				// If possible hit or no.
				boolean is_possible_attack = (mc.player.getCooledAttackStrength(sync_tps.get_value(true) ? - tick_to_hit : 0.0f) >= 1) ? true : false;

				// To hit if able.
				if (is_possible_attack) {
					attack_entity(entity);
				}
			}
		}
	}

	public void attack_entity(Entity entity) {
		// Get actual item off hand.
		ItemStack off_hand_item = mc.player.getHeldItemOffhand();

		// If off hand not null and is some SHIELD like use.
		if (off_hand_item != null && off_hand_item.getItem() == Items.SHIELD) {
			// Ignore ant continue.
			mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
		}

		// Start hit on entity.
		mc.player.connection.sendPacket(new CPacketUseEntity(entity));
		mc.player.swingArm(actual_hand);
		mc.player.resetCooldown();
	}

	// For find a entity.
	public Entity find_entity() {
		// Create a request.
		Entity entity_requested = null;

		for (Entity entitys : mc.world.loadedEntityList) {
			// If entity is not null continue to next event.
			if (entitys != null) {
				// If is compatible.
				if (is_compatible(entitys)) {
					// If is possible to get.
					if (mc.player.getDistance(entitys) <= range.get_value(1.0)) {
						// Atribute the entity into entity_requested.
						entity_requested = entitys;
					}
				}
			}
		}

		// Return the entity requested.
		return entity_requested;
	}

	public boolean is_compatible(Entity entity) {
		if (player.get_value(true) && entity instanceof EntityPlayer) {
			if (entity != mc.player && !(entity.getName().equals(mc.player.getName())) /* && BopeFriendManager.is_friend(entity) == false */) {
				return true;
			}
		}

		if (hostile.get_value(true) && entity instanceof IMob) {
			return true;
		}

		if (entity instanceof EntityLivingBase) {
			EntityLivingBase entity_living_base = (EntityLivingBase) entity;

			if (entity_living_base.getHealth() <= 0) {
				return false;
			}
		}

		return false;
	}
}