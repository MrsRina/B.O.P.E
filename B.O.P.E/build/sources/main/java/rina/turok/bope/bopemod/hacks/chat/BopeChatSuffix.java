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

public class BopeChatSuffix extends BopeModule {
	BopeSetting ignore = create("Ignore", "ChatSuffixIgnore", true);
	BopeSetting type   = create("Type", "ChatSuffixType", Arrays.asList("Default", "Random", "Custom"), "Default");
	BopeSetting custom = create("Custom", "ChatSuffixCustom", "B.O.P.E");

	boolean accept_suffix  = true;
	boolean suffix_default = false;
	boolean suffix_random  = false;
	boolean suffix_custom  = false;

	StringBuilder suffix;

	String[] random_client_name = {
		"B.O.P.E",
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
		"demtios",
		"69hr",
		"d4arkmind",
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
		".eu",
		".gg",
		".ca",
		".me",
		".GOD",
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
				message += Bope.BOPE_SIGN + convert_base(Bope.BOPE_NAME);
			}

			if (suffix_random) {
				StringBuilder suffix_with_randoms = new StringBuilder();

				suffix_with_randoms.append(random_string(random_client_name));
				suffix_with_randoms.append(random_string(random_client_finish));

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
		String new_base = base.toLowerCase();

		new_base = new_base.replace("a", "\u1d00");
		new_base = new_base.replace("b", "\u0299");
		new_base = new_base.replace("c", "\u1d04");
		new_base = new_base.replace("d", "\u1d05");
		new_base = new_base.replace("e", "\u1d07");
		new_base = new_base.replace("f", "\u0493");
		new_base = new_base.replace("g", "\u0262");
		new_base = new_base.replace("h", "\u029c");
		new_base = new_base.replace("i", "\u026a");
		new_base = new_base.replace("j", "\u1d0a");
		new_base = new_base.replace("k", "\u1d0b");
		new_base = new_base.replace("l", "\u029f");
		new_base = new_base.replace("m", "\u1d0d");
		new_base = new_base.replace("n", "\u0274");
		new_base = new_base.replace("o", "\u1d0f");
		new_base = new_base.replace("p", "\u1d18");
		new_base = new_base.replace("q", "\u01eb");
		new_base = new_base.replace("r", "\u0280");
		new_base = new_base.replace("s", "\u0455");
		new_base = new_base.replace("t", "\u1d1b");
		new_base = new_base.replace("u", "\u1d1c");
		new_base = new_base.replace("v", "\u1d20");
		new_base = new_base.replace("w", "\u1d21");
		new_base = new_base.replace("x", "\u0445");
		new_base = new_base.replace("y", "\u028f");
		new_base = new_base.replace("z", "\u1d22");

		return new_base;
	}
}