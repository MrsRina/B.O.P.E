package rina.turok.bope.bopemod.hacks.chat;

import net.minecraft.network.play.client.CPacketChatMessage;

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
* 09/05/20.
*
* - It idea come from Hephaestus.
*
*/
public class BopeChatSuffix extends BopeModule {
	BopeSetting ignore = create("Ignore", "ChatSuffixIgnore", true);
	BopeSetting type   = create("Type", "ChatSuffixType", "Default", combobox("Default", "Random", "Custom"));
	BopeSetting custom = create("Custom", "ChatSuffixCustom", "bope");

	boolean accept_suffix;
	boolean suffix_default;
	boolean suffix_random;
	boolean suffix_custom;

	StringBuilder suffix;

	// New.
	String[] random_suffix_pre = {
		"bope",
		"turok",
		"mercury",
		"salhack",
		"phbobos",
		"waohack",
		"nutgod",
		"nutlord",
		"nutfail",
		"orkut",
		"osiris",
		"seppuku",
		"aurora",
		"wwe",
		"rusherhack",
		"hephaestus",
		"impact",
		"team",
		"rhuama",
		"oct",
		"future",
		"huzuni",
		"kami",
		"optifine",
		"wrust",
		"root",
		"holocaust",
		"future",
		"twd",
		"aristois",
		"axiom",
		"fit",
		"rina",
		"zbob",
		"trump",
		"atlas",
		"ares",
		"oldturok",
		"kotlin",
		"keemy",
		"based",
		"rgba",
		"rgb",
		"obama"
	};

	String[] random_suffix_mid = {
		".eu",
		".ez",
		".gg",
		".cc",
		".ff",
		".kt",
		".gay",
		".java",
		".god",
		" "
	};

	String[] random_suffix_end = {
		" ",       // None.
		" \u262d", // hammer and sickle ☭
		" \u0fc9", // nut symbol ࿉
		" \u2620", // skull ☠
		" \u2623", // biohazard ☣
		" \u2654", // brown ♔
		" \u2764", // heart
		" \u267f", // wheelchair ♿
		" \u262f"  // yingyang ☯
	};

	public BopeChatSuffix() {
		super(BopeCategory.BOPE_CHAT, false);

		// Info.
		this.name        = "Chat Suffix";
		this.tag         = "ChatSuffix";
		this.description = "Sentence end with a pretty suffix.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@EventHandler
	private Listener<BopeEventPacket.SendPacket> listener = new Listener<>(event -> {
		// If not be the CPacketChatMessage return.
		if (!(event.get_packet() instanceof CPacketChatMessage)) {
			return;
		}

		// Start event suffix.
		accept_suffix = true;

		// Get value.
		boolean ignore_prefix = ignore.get_value(true);

		String message = ((CPacketChatMessage) event.get_packet()).getMessage();

		// If is with some caracther.
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

		// Compare the values type.
		if (type.in("Default")) {
			suffix_default = true;
			suffix_random  = false;
			suffix_custom  = false;
		}

		if (type.in("Random")) {
			suffix_default = false;
			suffix_random  = true;
			suffix_custom  = false;
		}

		if (type.in("Custom")) {
			suffix_default = false;
			suffix_random  = false;
			suffix_custom  = true;
		}

		// If accept.
		if (accept_suffix) {
			if (suffix_default) {
				// Just default.
				message += Bope.BOPE_SIGN + random_suffix_end[3] + " " + convert_base("bope") + random_suffix_end[3];
			}

			if (suffix_random) {
				// Create first the string builder.
				StringBuilder suffix_with_randoms = new StringBuilder();

				// Convert the base using the TurokFont.
				suffix_with_randoms.append(convert_base(random_string(random_suffix_pre)));
				suffix_with_randoms.append(convert_base(random_string(random_suffix_mid)));
				suffix_with_randoms.append(convert_base(random_string(random_suffix_end)));

				message += Bope.BOPE_SIGN + suffix_with_randoms.toString(); 
			}

			// If suffiix custom is on, just get the value from label.
			if (suffix_custom) {
				message += Bope.BOPE_SIGN + convert_base(custom.get_value(""));
			}

			// If message 256 string length substring.
			if (message.length() >= 256) {
				message.substring(0, 256);
			}
		}

		// Send the message.
		((CPacketChatMessage) event.get_packet()).message = message;
	});

	// Get the random values string.
	public String random_string(String[] list) {
		return list[new Random().nextInt(list.length)];
	}

	// Convert the base using the TurokFont.
	public String convert_base(String base) {
		return Bope.smooth(base);
	}

	@Override
	public String detail_option() {
		// Update the detail.
		return this.type.get_current_value();
	}
}