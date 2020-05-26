package rina.turok.bope.bopemod.hacks.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.List;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
 * @author CryroByte
 *
 * Created by CryroByte.
 * 05/07/20.
 *
 */
public class BopeVisualRange extends BopeModule {
	private List<String> people;
	private List<String> peoplenew;

	public BopeVisualRange() {
		super(BopeCategory.BOPE_CHAT);

		// Info.
		this.name        = "Visual Range";
		this.tag         = "VisualRange";
		this.description = "Add who joins to visual range.";

		// Release.
		release("B.O.P.E - module - B.O.P.E");
	}

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
					BopeMessage.send_client_message(ChatFormatting.WHITE + name + ChatFormatting.BLUE);
					people.add(name);
				}
			}
		}

	}
}
