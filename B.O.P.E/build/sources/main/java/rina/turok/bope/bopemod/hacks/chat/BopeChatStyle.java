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
	public List<String> colors_combobox = combobox(
		"False", // False. :)
		"LIGHT_PURPLE",
		"DARK_PURPLE",
		"DARK_GREEN",
		"DARK_GRAY",
		"DARK_BLUE",
		"DARK_AQUA",
		"DARK_RED",
		"YELLOW",
		"GREEN",
		"BLACK",
		"WHITE",
		"BLUE",
		"AQUA",
		"RED"
	);

	BopeSetting color_time = create("Time", "ChatStyleColorTime", "DARK_BLUE", colors_combobox); 
	BopeSetting color_name = create("Name", "ChatStyleColorMessage", "DARK_BLUE", colors_combobox);
	BopeSetting type_mode  = create("Type Mode", "ChatStyleTypeMode", "[]", combobox("[]", "<>"));
	//BopeSetting color_mode   = create("Color Mode", "ChatStyleColorMode", "HUD", combobox("HUD", "Server"));

	public static HashMap<String, ChatFormatting> color = new HashMap<>();

	boolean event_color_time = true;
	boolean event_color_name = true; 

	public BopeChatStyle() {
		super(BopeCategory.BOPE_CHAT, false);

		// Info.
		this.name        = "Chat Style";
		this.tag         = "ChatStyle";
		this.description = "Chat style settings.";

		// Release.
		release("B.O.P.E - Module - B.O.P.E");

		// Colors.
		color.put("LIGHT_PURPLE", ChatFormatting.LIGHT_PURPLE);
		color.put("DARK_PURPLE",  ChatFormatting.DARK_PURPLE);
		color.put("DARK_GREEN",   ChatFormatting.DARK_GREEN);
		color.put("DARK_GRAY",    ChatFormatting.DARK_GRAY);
		color.put("DARK_BLUE",    ChatFormatting.DARK_BLUE);
		color.put("DARK_AQUA",    ChatFormatting.DARK_AQUA);
		color.put("DARK_RED",     ChatFormatting.DARK_RED);
		color.put("YELLOW",       ChatFormatting.YELLOW);
		color.put("GREEN",        ChatFormatting.GREEN);
		color.put("BLACK",        ChatFormatting.BLACK);
		color.put("WHITE",        ChatFormatting.WHITE);
		color.put("BLUE",         ChatFormatting.BLUE);
		color.put("AQUA",         ChatFormatting.AQUA);
		color.put("RED",          ChatFormatting.RED);
	}

	@EventHandler
	private Listener<ClientChatReceivedEvent> listener = new Listener<>(event -> {
		TextComponentString message = new TextComponentString("");

		String original_message = event.getMessage().getUnformattedText();

		event_color_time = true;
		event_color_name = true;

		if (color_time.in("False")) {
			event_color_time = false;
		}

		if (color_name.in("False")) {
			event_color_name = false;
		}

		if (event_color_time) {
			ChatFormatting c = color.get(color_time.get_current_value());

			message.appendText(Bope.g + "[" + c + new SimpleDateFormat("k:mm:a").format(new Date()) + Bope.g + "]" + " > ");
		}

		if (event_color_name) {
			ChatFormatting c = color.get(color_name.get_current_value());

			String[] separates = original_message.trim().split("\\s+");

			String base_1 = separates[0];

			String pre = type_mode.in("[]") ? "[" : "<";
			String end = type_mode.in("[]") ? "]" : ">";
				
			base_1 = base_1.replaceAll("<", Bope.g + pre + c);
			base_1 = base_1.replaceAll(">", Bope.g + end + Bope.r);

			String message_of_player = original_message.substring(separates[0].length());

			message.appendText(base_1 + message_of_player);
		} else {
			message.appendSibling(event.getMessage());
		}

		event.setMessage(message);
	});
}