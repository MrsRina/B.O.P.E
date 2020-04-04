package rina.turok.bope.bopemod.hacks.chat;

import me.zero.alpine.listener.EventHandler; // zero Alpine.
import me.zero.alpine.listener.Listener;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.CPacketChatMessage;

import rina.turok.bope.bopemod.events.BopeEventRender;
import rina.turok.bope.bopemod.events.BopeEventPacket;
import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.bopemod.BopeModule;

// Rina.
public class BopeChatSuffix extends BopeModule {
	public BopeChatSuffix() {
		super("ChatSuffix", "Modify the chat.", Keyboard.KEY_P, BopeCategory.Category.BOPE_CHAT);
	}

	@EventHandler
	public Listener<BopeEventPacket.Send> packet_event = new Listener<>(event -> {
		if (event.get_packet() instanceof CPacketChatMessage) {
			String message = ((CPacketChatMessage) event.get_packet()).getMessage();

			message += " -> B.O.P.E";

			if (message.length() >= 256) {
				message = message.substring(0, 256);
			}

			((CPacketChatMessage) event.get_packet()).message = message;
		}
	});

	@Override
	public void onWorldRender(BopeEventRender event) {
		mc.rightClickDelayTimer = 0;
	}
}