package rina.turok.bope.bopemod.hacks.chat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

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

//	@Override
//	public void enable() {
//		entities_select = new ConcurrentHashMap<>();
//	}
//
//	@Override
//	public void disable() {
//		entities_select = null;
//	}
//
//	@Override
//	public void update() {
//		if (mc.player == null) {
//			set_active(false);
//		}
//
//		if (entities_select == null) {
//			entities_select = new ConcurrentHashMap<>();
//		}
//
//		for (Entity entities : mc.world.getLoadedEntityList()) {
//			if (!(entities instanceof EntityPlayer)) {
//				continue;
//			}
//
//			EntityPlayer players = (EntityPlayer) entities;
//
//			if (players.getHealth() > 0) {
//				continue;
//			}
//
//		}
//	}
}