package rina.turok.bope.bopemod.hacks.chat;

import net.minecraft.network.play.client.CPacketChatMessage;

import java.util.*;

// Zero alpine.
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
* 09/05/20.
*
*/
public class BopeChatSuffix extends BopeModule {
	BopeSetting ignore = create("Ignore", "ChatSuffixIgnore", true);
	BopeSetting type   = create("Type", "ChatSuffixType", Arrays.asList("Default", "Random", "Custom"), "Default");
	BopeSetting custom = create("Custom", "ChatSuffixCustom", "bope");

	boolean accept_suffix  = true;
	boolean suffix_default = false;
	boolean suffix_random  = false;
	boolean suffix_custom  = false;

	StringBuilder suffix;

	String[] random_client_name = {
		"bope",
		"turok",
		"lunar",
		"mercury",
		"xulu",
		"hephaestus",
		"axios",
		"leafyhack",
		"phobos",
		"bophos",
		"megyn",
		"nutfail",
		"nutlord",
		"nutgod",
		"impact",
		"axios",
		"seppuku",
		"osiris",
		"wao",
		"wwe",
		"team",
		"aurora",
		"salhack",
		"rusherhack",
		"ruhack",
		"mad",
		"big",
		"nigger",
		"reload",
		"black",
		"39y",
		"demtios",
		"69hr",
		"darkmind",
		"walle",
		"sasha",
		"awesome",
		"stone",
		"fuck",
		"udumb",
		"haha",
		"cringe",
		"othersskids",
		"caralho",
		""
	};

	String[] random_client_finish = {
		" eu",
		" gg",
		" ca",
		" me",
		" god",
		" mad",
		" haha",
		" ez",
		" demtios",
		" stone",
		" big",
		" spicy",
		" bit",
		" lunatic",
		" tranny",
		" vadia",
		" cringe",
		" ssssss",
		" phosbos",
		" wao",
		" aurora",
		" chungus",
		" on top",
		" a",
		" powerful",
		""
	};

	public BopeChatSuffix() {
		super(BopeCategory.BOPE_CHAT);

		// Info.
		this.name        = "Chat Suffix";
		this.tag         = "ChatSuffix";
		this.description = "Sentence end with a pretty suffix.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@EventHandler
	private Listener<BopeEventPacket.SendPacket> listener = new Listener<>(event -> {
		if (!(event.get_packet() instanceof CPacketChatMessage)) {
			return;
		}

		boolean ignore_prefix = ignore.get_value(true);

		String message = ((CPacketChatMessage) event.get_packet()).getMessage();

		if (message.startsWith("/")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("\\") && ignore_prefix) accept_suffix = false;
		if (message.startsWith("!")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith(":")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith(";")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith(".")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith(",")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("@")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("&")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("*")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("$")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("#")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith("(")  && ignore_prefix) accept_suffix = false;
		if (message.startsWith(")")  && ignore_prefix) accept_suffix = false;

		if (compare("Default")) {
			suffix_default = true;
			suffix_random  = false;
			suffix_custom  = false;
		}

		if (compare("Random")) {
			suffix_default = false;
			suffix_random  = true;
			suffix_custom  = false;
		}

		if (compare("Custom")) {
			suffix_default = false;
			suffix_random  = false;
			suffix_custom  = true;
		}

		if (accept_suffix) {
			if (suffix_default) {
				message += Bope.BOPE_SIGN + convert_base("bope");
			}

			if (suffix_random) {
				StringBuilder suffix_with_randoms = new StringBuilder();

				suffix_with_randoms.append(convert_base(random_string(random_client_name)));
				suffix_with_randoms.append(convert_base(random_string(random_client_finish)));

				message += Bope.BOPE_SIGN + suffix_with_randoms.toString(); 
			}

			if (suffix_custom) {
				message += Bope.BOPE_SIGN + convert_base(custom.get_value(""));
			}

			if (message.length() >= 256) {
				message.substring(0, 256);
			}
		}

		((CPacketChatMessage) event.get_packet()).message = message;
	});

	@Override
	public void update() {
		detail_option(type.get_current_value());
	}

	public boolean compare(String requested) {
		if (type.get_current_value().equalsIgnoreCase(requested)) {
			return true;
		}

		return false;
	}

	public String random_string(String[] list) {
		return list[new Random().nextInt(list.length)];
	}

	public String convert_base(String base) {
		return Bope.smoth(base);
	}
}