package rina.turok.bope.bopemod.hacks.chat;

import net.minecraftforge.client.event.ClientChatReceivedEvent;

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
public class BopeFriendM extends BopeModule {
	BopeSetting server_join_leave = create("Server Join & Leave", "FriendServerJoinLeave", true);
	BopeSetting second_plan       = create("Second Plan", "FriendSecondPlan", true);

	public BopeFriendM() {
		super(BopeCategory.BOPE_CHAT, false);

		// Info.
		this.name        = "Friend";
		this.tag         = "Friend";
		this.description = "To manager somes actions with friends.";

		// Release.
		release("B.O.P.E - Module - B.O.P.E");
	}
}
