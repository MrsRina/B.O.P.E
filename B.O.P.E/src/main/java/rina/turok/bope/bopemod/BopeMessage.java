package rina.turok.bope.bopemod;

import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.client.Minecraft;

import com.mojang.realmsclient.gui.ChatFormatting;

import rina.turok.bope.framework.BopeChatMessage;
import rina.turok.bope.Bope;

public class BopeMessage {
	public static Minecraft mc = Minecraft.getMinecraft();

	public static void user_send_message(String message) {
		if (mc.player != null) {
			mc.player.connection.sendPacket(new CPacketChatMessage(message));
		}
	}

	public static void client_message(String message) {
		if (mc.player != null) {
			mc.player.sendMessage(new BopeChatMessage(message));
		}
	}

	public static void send_client_message(String message) {
		client_message(ChatFormatting.BLUE + Bope.BOPE_NAME + " - " + message);
	}
}