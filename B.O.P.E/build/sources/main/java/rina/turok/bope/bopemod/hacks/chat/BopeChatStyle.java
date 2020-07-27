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
		"Disabled", // False. :)
		"Black",
		"Red",
		"Aqua",
		"Blue",
		"Gold",
		"Gray",
		"White",
		"Green",
		"Yello",
		"Dark_Red",
		"Dark_Aqua",
		"Dark_Blue",
		"Dark_Gray",
		"Dark_Green",
		"Dark_Purple",
		"Light_Purple"
	);

	BopeSetting color_time  = create("Time", "ChatStyleColorTime", colors_combobox.get(0), colors_combobox); 
	BopeSetting color_name  = create("Name", "ChatStyleColorMessage", colors_combobox.get(0), colors_combobox);
	BopeSetting color_fname = create("Friend", "ChatStyleColorFriend", colors_combobox.get(14), colors_combobox);
	BopeSetting type_mode   = create("Separator", "ChatStyleSeparator", "<>", combobox("[]", "<>", "None"));
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
		color.put(colors_combobox.get(1),  ChatFormatting.BLACK);
		color.put(colors_combobox.get(2),  ChatFormatting.RED);
		color.put(colors_combobox.get(3),  ChatFormatting.AQUA);
		color.put(colors_combobox.get(4),  ChatFormatting.BLUE);
		color.put(colors_combobox.get(5),  ChatFormatting.GOLD);
		color.put(colors_combobox.get(6),  ChatFormatting.GRAY);
		color.put(colors_combobox.get(7),  ChatFormatting.WHITE);
		color.put(colors_combobox.get(8),  ChatFormatting.GREEN);
		color.put(colors_combobox.get(9),  ChatFormatting.YELLOW);
		color.put(colors_combobox.get(10), ChatFormatting.DARK_RED);
		color.put(colors_combobox.get(11), ChatFormatting.DARK_AQUA);
		color.put(colors_combobox.get(12), ChatFormatting.DARK_BLUE);
		color.put(colors_combobox.get(13), ChatFormatting.DARK_GRAY);
		color.put(colors_combobox.get(14), ChatFormatting.DARK_GREEN);
		color.put(colors_combobox.get(15), ChatFormatting.DARK_PURPLE);
		color.put(colors_combobox.get(16), ChatFormatting.LIGHT_PURPLE);
	}

	@EventHandler
	private Listener<ClientChatReceivedEvent> listener = new Listener<>(event -> {
		TextComponentString message = new TextComponentString("");

		String original_message = event.getMessage().getUnformattedText();

		boolean cancel    = false;
		boolean is_name   = false;
		boolean is_friend = false;

		event_color_time = true;
		event_color_name = true;

		String name = original_message.trim().split("\\s+")[0];

		if (name.contains("<") && name.contains(">")) {
			is_name = true;
		}

		name = name.replaceAll("<", "");
		name = name.replaceAll(">", "");

		if (Bope.get_friend_manager().is_friend(name) || mc.player.getName().equalsIgnoreCase(name)) {
			is_friend = true;
		}

		String pre = "";
		String end = "";

		boolean none = true;

		if (!type_mode.in("None")) {
			pre = type_mode.in("[]") ? "[" : "<";
			end = type_mode.in("[]") ? "]" : ">";

			none = false;
		}

		if (color_time.in("Disabled")) {
			event_color_time = false;
		}

		if (color_fname.in("Disabled") && color_name.in("Disabled")) {
			event_color_name = false;
		}

		if (color_name.in("Disabled")) {
			event_color_name = false;
		}

		if (!color_fname.in("Disabled") && color_name.in("Disabled")) {
			event_color_name = true;
		}

		if (event_color_time) {
			ChatFormatting c = color.get(color_time.get_current_value());

			message.appendText(Bope.r + pre + c + new SimpleDateFormat("k:mm:a").format(new Date()) + Bope.r + end + (is_name == true ? (none == true ? " " : "") : " "));
		
			cancel = false;
		}

		if (event_color_name && is_name) {
			ChatFormatting c;

			if (is_friend && !color_fname.in("Disabled")) {
				c = color.get(color_fname.get_current_value());
			} else {
				c = color.get(color_name.get_current_value());
			}

			String[] separates = original_message.trim().split("\\s+");

			String base_1 = separates[0];
				
			base_1 = base_1.replaceAll("<", Bope.r + "<" + c);
			base_1 = base_1.replaceAll(">", Bope.r + ">" + Bope.r);

			String message_of_player = original_message.substring(separates[0].length());

			message.appendText(base_1 + message_of_player);

			cancel = true;
		}

		if (!cancel) {
			message.appendSibling(event.getMessage());
		}

		event.setMessage(message);
	});
}