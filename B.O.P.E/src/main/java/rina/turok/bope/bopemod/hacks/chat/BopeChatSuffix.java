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
		"39y",
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
		" on top",
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

				suffix_with_randoms.append(convert_base(random_string(random_client_name)));
				suffix_with_randoms.append(convert_base(random_string(random_client_finish)));

				message += Bope.BOPE_SIGN + suffix_with_randoms.toString(); 
			}

			if (suffix_custom) {
				message += Bope.BOPE_SIGN + custom.get_value("");
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

	public String convert_to_smoth(String base) {
		String new_base = base;

		new_base = new_base.replace("!", "\u0021");
		new_base = new_base.replace("@", "\u0040");
		new_base = new_base.replace("#", "\u0023");
		new_base = new_base.replace("$", "\u0024");
		new_base = new_base.replace("%", "\u0025");
		new_base = new_base.replace("^", "\u005e");
		new_base = new_base.replace("&", "\u0026");
		new_base = new_base.replace("*", "\u002a");
		new_base = new_base.replace("(", "\u0028");
		new_base = new_base.replace(")", "\u0029");
		new_base = new_base.replace("_", "\u005f");
		new_base = new_base.replace("+", "\u002b");
		new_base = new_base.replace("-", "\u003d");
		new_base = new_base.replace("=", "\u002d");

		new_base = new_base.replace("0", "\u0030");
		new_base = new_base.replace("1", "\u0031");
		new_base = new_base.replace("2", "\u0032");
		new_base = new_base.replace("3", "\u0033");
		new_base = new_base.replace("4", "\u0034");
		new_base = new_base.replace("5", "\u0035");
		new_base = new_base.replace("6", "\u0036");
		new_base = new_base.replace("7", "\u0037");
		new_base = new_base.replace("8", "\u0038");
		new_base = new_base.replace("9", "\u0039");

		new_base = new_base.replace("A", "\u0041");
		new_base = new_base.replace("B", "\u0042");
		new_base = new_base.replace("C", "\u0043");
		new_base = new_base.replace("D", "\u0044");
		new_base = new_base.replace("E", "\u0045");
		new_base = new_base.replace("F", "\u0046");
		new_base = new_base.replace("G", "\u0047");
		new_base = new_base.replace("H", "\u0048");
		new_base = new_base.replace("I", "\u0049");
		new_base = new_base.replace("J", "\u004a");
		new_base = new_base.replace("K", "\u004b");
		new_base = new_base.replace("L", "\u004c");
		new_base = new_base.replace("M", "\u004d");
		new_base = new_base.replace("N", "\u004e");
		new_base = new_base.replace("O", "\u004f");
		new_base = new_base.replace("P", "\u0050");
		new_base = new_base.replace("Q", "\u0051");
		new_base = new_base.replace("R", "\u0052");
		new_base = new_base.replace("S", "\u0053");
		new_base = new_base.replace("T", "\u0054");
		new_base = new_base.replace("U", "\u0055");
		new_base = new_base.replace("V", "\u0056");
		new_base = new_base.replace("W", "\u0057");
		new_base = new_base.replace("X", "\u0058");
		new_base = new_base.replace("Y", "\u0059");
		new_base = new_base.replace("Z", "\u005a");

		new_base = new_base.replace("a", "\u03b1");
		new_base = new_base.replace("b", "\u0062");
		new_base = new_base.replace("c", "\u0063");
		new_base = new_base.replace("d", "\u0064");
		new_base = new_base.replace("e", "\u0065");
		new_base = new_base.replace("f", "\u0066");
		new_base = new_base.replace("g", "\u0067");
		new_base = new_base.replace("h", "\u0068");
		new_base = new_base.replace("i", "\u0131");
		new_base = new_base.replace("j", "\u006a");
		new_base = new_base.replace("k", "\u006b");
		new_base = new_base.replace("l", "\u006c");
		new_base = new_base.replace("m", "\u006d");
		new_base = new_base.replace("n", "\u006e");
		new_base = new_base.replace("o", "\u006f");
		new_base = new_base.replace("p", "\u0070");
		new_base = new_base.replace("q", "\u0071");
		new_base = new_base.replace("r", "\u0072");
		new_base = new_base.replace("s", "\u0073");
		new_base = new_base.replace("t", "\u0074");
		new_base = new_base.replace("u", "\u0075");
		new_base = new_base.replace("v", "\u0076");
		new_base = new_base.replace("w", "\u026f");
		new_base = new_base.replace("x", "\u0078");
		new_base = new_base.replace("y", "\u0447");
		new_base = new_base.replace("z", "\u007a");

		return new_base;
	}
}