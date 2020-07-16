package rina.turok.bope.bopemod.hacks.chat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.*;

// Zero alpine manager.
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Events.
import rina.turok.bope.bopemod.events.BopeEventPacket;

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
* 31/05/2020.
*
*/
public class BopeAutoGG extends BopeModule {
	// ConcurrentHashMap<Entity, Integer> entities_select = null;

	public BopeAutoGG() {
		super(BopeCategory.BOPE_CHAT);

		// Info.
		this.name        = "Auto GG";
		this.tag         = "AutoGG";
		this.description = "Automaticaly say good game paceaful or no!";

		// Release.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@Override
	public void update() {
		if (mc.player != null && mc.world != null) {
			List<Entity> entities = mc.world.loadedEntityList.stream()
			/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRi */ .filter(entity -> entity != mc.player)
			/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRi */ .filter(entity -> mc.player.getDistance(entity) <= 10)
			/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRi */ .filter(entity -> !entity.isDead)
			/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRi */ .filter(entity -> !(Bope.get_friend_manager().is_friend(entity.getName())))
			/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRi */ .filter(entity -> entity instanceof EntityPlayer)
			/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRi */ .sorted(Comparator.comparing(distance -> mc.player.getDistance(distance)))
			/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRi */ .collect(Collectors.toList());

			entities.forEach(entity -> {
				EntityPlayer entity_player = (EntityPlayer) entity;

				if (entity_player.isDead || entity_player.getHealth() < 0) {
					Bope.dev(entity_player.getName() + " died in your range");
				}
			});
		}
	}
}