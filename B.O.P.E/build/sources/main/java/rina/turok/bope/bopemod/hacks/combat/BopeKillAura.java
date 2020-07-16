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

// Render.
import rina.turok.bope.bopemod.events.BopeEventRender;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Util.
import rina.turok.bope.bopemod.util.BopeUtilRenderer;

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
	BopeSetting esp     = create("Render Entity Mode", "KillAuraRenderEntityMode", "csgo", combobox("csgo", "rect", "disabled"));
	BopeSetting range   = create("Range", "KillAuraRange", 6, 1, 10);

	boolean with_sword = true;

	EnumHand actual_hand = EnumHand.MAIN_HAND;

	double tick = 0.0;

	List<Entity> entities = null;

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
			entities = mc.world.loadedEntityList.stream()
			/* RinaRinaRinaRinaRinaRinaRinaR */ .filter(entity -> entity != mc.player)
			/* RinaRinaRinaRinaRinaRinaRinaR */ .filter(entity -> mc.player.getDistance(entity) <= range.get_value(1))
			/* RinaRinaRinaRinaRinaRinaRinaR */ .filter(entity -> !entity.isDead)
			/* RinaRinaRinaRinaRinaRinaRinaR */ .filter(entity -> !(Bope.get_friend_manager().is_friend(entity.getName())))
			/* RinaRinaRinaRinaRinaRinaRinaR */ .filter(entity -> (entity instanceof EntityPlayer && player.get_value(true)) || (entity instanceof IMob && hostile.get_value(true)))
			/* RinaRinaRinaRinaRinaRinaRinaR */ .filter(entity -> !entity.isDead)
			/* RinaRinaRinaRinaRinaRinaRinaR */ .sorted(Comparator.comparing(distance -> mc.player.getDistance(distance)))
			/* RinaRinaRinaRinaRinaRinaRinaR */ .collect(Collectors.toList());
		}
	}

	@Override
	public void render(BopeEventRender event) {
		if (entities != null) {
			entities.forEach(entity -> {
				if (!(mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) && sword.get_value(true)) {
					return;
				}

				if (esp.in("csgo")) {
					BopeUtilRenderer.EntityPlayerCSGOESP((Entity) entity, 190, 190, 190, Math.round(mc.player.getDistance(entity) * 25.5f));
				}

				if (esp.in("rect")) {
					BopeUtilRenderer.EntityPlayerRectESP((Entity) entity, 190, 190, 190, Math.round(mc.player.getDistance(entity) * 25.5f));
				}

				attack_entity(entity);
			});
		}
	}

	public void attack_entity(Entity entity) {
		if (mc.player.getCooledAttackStrength(0) >= 1) {
			// Get actual item off hand.
			ItemStack off_hand_item = mc.player.getHeldItemOffhand();

			// If off hand not null and is some SHIELD like use.
			if (off_hand_item != null && off_hand_item.getItem() == Items.SHIELD) {
				// Ignore ant continue.
				mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
			}

			// Start hit on entity.
			mc.playerController.attackEntity(mc.player, entity);
			mc.player.swingArm(actual_hand);
		}
	}
}