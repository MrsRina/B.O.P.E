package rina.turok.bope.bopemod.commands;

import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.Minecraft;

import com.mojang.util.UUIDTypeAdapter;

import com.google.common.base.Converter;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.regex.Pattern;
import java.util.*;
import java.net.*;
import java.io.*;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeFriend;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Rina
 *
 * Created by Rina.
 * 30/05/2020.
 *
 **/
public class BopeFriendC extends BopeCommand {
	String[] errors_for_add = {
		"This account does not exist",
		"Who?",
		"Huh, who?",
		"Added Rina as your friend.",
		"????Who??"
	};

	String[] errors_for_remove = {
		"This account does not exist",
		"Does this person exist?",
		"Sorry but you have imaginary friends...",
		"Who is this person?",
		":("
	};

	public BopeFriendC() {
		super("friend", "For add friend or remove.");
	}

	public boolean get_message(String[] message) {
		String type   = "null";
		String friend = "null"; 

		if (message.length > 1) {
			type = message[1];
		}

		if (message.length > 2) {
			friend = message[2];
		}

		if (message.length > 3) {
			BopeMessage.send_client_error_message(current_prefix() + "friend <add/new/rem/remove/del/delete>");

			return true;
		}

		if (type.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "friend <add/new/rem/remove/del/delete> <name>");

			return true;
		}

		if (friend.equals("null")) {
			BopeMessage.send_client_error_message(current_prefix() + "friend <add/new/rem/remove/del/delete> <name>");
			
			return true;
		}

		boolean is_for = true;

		if (type.equalsIgnoreCase("add")  ||
			type.equalsIgnoreCase("new")) {
			// Add, huh.
			is_for = true;
		} else if (type.equalsIgnoreCase("rem")     ||
				   type.equalsIgnoreCase("remove")  ||
				   type.equalsIgnoreCase("del")     ||
				   type.equalsIgnoreCase("delete")) {
			// Remove.
			is_for = false;
		}

		if (is_for) {
			ArrayList<NetworkPlayerInfo> info_map = new ArrayList<NetworkPlayerInfo> (mc.getConnection().getPlayerInfoMap());
			NetworkPlayerInfo profile             = requested_player_from_server(info_map, friend);

			if (profile == null) {
				BopeMessage.send_client_error_message(random_funny(errors_for_add));

				return true;
			} else {
				if (Bope.get_friend_manager().is_friend(friend)) {
					BopeMessage.send_client_message("Already added as friend.");

					return true;
				}

				Bope.get_friend_manager().add_friend(friend);

				BopeMessage.send_client_message("Added " + friend + " as friend.");

				return true;
			}
		} else {
			BopeFriend friend_requested = Bope.get_friend_manager().get_friend_with_name(friend);

			if (friend_requested == null) {
				BopeMessage.send_client_error_message(random_funny(errors_for_remove));

				return true;
			} else {
				Bope.get_friend_manager().remove_friend(friend_requested);

				BopeMessage.send_client_message("Removed " + friend + " :(.");

				return  true;
			}
		}
	}

	public NetworkPlayerInfo requested_player_from_server(ArrayList<NetworkPlayerInfo> list, String friend) {
		return list.stream().filter(player -> player.getGameProfile().getName().equalsIgnoreCase(friend)).findFirst().orElse(null);
	}

	public String random_funny(String[] list) {
		return list[new Random().nextInt(list.length)];
	}
}