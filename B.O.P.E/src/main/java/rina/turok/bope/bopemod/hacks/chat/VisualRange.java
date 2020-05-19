package rina.turok.bope.bopemod.hacks.chat;

import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;
import rina.turok.bope.bopemod.hacks.BopeCategory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import java.util.ArrayList;
import java.util.List;
import com.mojang.realmsclient.gui.ChatFormatting;


// Modules.
// Guiscreen.
// Data.

// Core.


/**
 * @author CryroByte
 *
 * Created by CryroByte.
 * 05/07/20.
 *
 */
public class VisualRange extends BopeModule {



	public VisualRange() {
		super(BopeCategory.BOPE_CHAT);


		this.name        = "VisualRange";
		this.tag         = "VisualRange";
		this.description = "This module is for test.";

		release("B.O.P.E - module - B.O.P.E");
	}
	private List<String> people;
	private List<String> peoplenew;

	@Override
	public void enable() {
		people = new ArrayList<>();
	}

	@Override
	public void update() {
		if (mc.world == null | mc.player == null) return;

		peoplenew = new ArrayList<>();
		List<EntityPlayer> playerEntities = mc.world.playerEntities;

		for (Entity e : playerEntities) {
			if (e.getName().equals(mc.player.getName())) continue;
			peoplenew.add(e.getName());
		}

		if (peoplenew.size() > 0) {
			for (String name : peoplenew) {
				if (!people.contains(name)) {
					BopeMessage.send_client_message(ChatFormatting.WHITE + name + ChatFormatting.BLUE +"  just entered visual range thanks to bope ^_^");
					people.add(name);
				}
			}
		}

	}
}
