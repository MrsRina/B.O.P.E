package rina.turok.bope.bopemod.hacks.chat;

import me.zero.alpine.listener.EventHandler; // zero Alpine.
import me.zero.alpine.listener.Listener;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketChatMessage;

import rina.turok.bope.bopemod.events.BopeEventRender;
import rina.turok.bope.bopemod.events.BopeEventPacket;
import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Rina.
public class BopeTestModule extends BopeModule {
	public BopeTestModule() {
		super("Test module", "Modify the chat.", Keyboard.KEY_P, BopeCategory.Category.BOPE_CHAT);
	}

	@EventHandler
	public Listener<BopeEventPacket.SendPacket> packet_event = new Listener<>(event -> {
		if (event.get_packet() instanceof CPacketCloseWindow) {
			event.cancel();
		}
	});

	@Override
	public void onEnable() {
		BopeMessage.send_client_message(" Test moudle Gay on");
	}

	@Override
	public void onDisable() {
		BopeMessage.send_client_message(" test module Gay off");
	}

	@Override
	public void onRender() {}

	@Override
	public void onWorldRender(BopeEventRender event) {}
}