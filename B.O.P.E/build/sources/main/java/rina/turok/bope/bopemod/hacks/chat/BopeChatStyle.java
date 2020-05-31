package rina.turok.bope.bopemod.hacks.chat;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraft.util.text.TextComponentString;
import com.mojang.realmsclient.gui.ChatFormatting;

import java.text.SimpleDateFormat;
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
public class BopeChatStyle extends BopeModule {
	BopeSetting time_message = create("Time Message", "ChatStyleTimeMessage", true);
	//BopeSetting color_mode   = create("Color Mode", "ChatStyleColorMode", "HUD", combobox("HUD", "Server"));

	public BopeChatStyle() {
		super(BopeCategory.BOPE_CHAT, false);

		// Info.
		this.name        = "Chat Style";
		this.tag         = "ChatStyle";
		this.description = "Chat style settings.";

		// Release.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@EventHandler
	private Listener<ClientChatReceivedEvent> listener = new Listener<>(event -> {
		if (time_message.get_value(true)) {
			TextComponentString time = new TextComponentString(ChatFormatting.GRAY + "<" + new SimpleDateFormat("k:mm").format(new Date()) + ">" + ChatFormatting.RESET + " ");
			
			time.appendSibling(event.getMessage());

			event.setMessage(time);
		} else {
			event.setMessage(event.getMessage());
		}
	});
}