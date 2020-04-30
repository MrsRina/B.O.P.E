package rina.turok.bope.bopemod;

import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.Minecraft;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeMessage {
	public static Minecraft mc = Minecraft.getMinecraft();

	public static void user_send_message(String message) {
		if (mc.player != null) {
			mc.player.connection.sendPacket(new CPacketChatMessage(message));
		}
	}

	public static void client_message(String message) {
		if (mc.player != null) {
			mc.player.sendMessage(new ChatMessage(message));

		}
	}

	public static void send_client_message(String message) {
		client_message(ChatFormatting.DARK_BLUE + Bope.BOPE_NAME + ChatFormatting.GRAY + " > " + message);

		Bope.send_client_log(" > " +  message);
	}

	public static void send_client_error_message(String message) {
		client_message(ChatFormatting.RED + Bope.BOPE_NAME + ChatFormatting.GRAY +  " < " + message);
		Bope.send_client_log(" < " +  message);
	}

	public static class ChatMessage extends TextComponentBase {
		String message_input;
		
		public ChatMessage(String message) {		
			Pattern p       = Pattern.compile("&[0123456789abcdefrlosmk]");
			Matcher m       = p.matcher(message);
			StringBuffer sb = new StringBuffer();
	
			while (m.find()) {
				String replacement = "\u00A7" + m.group().substring(1);
				m.appendReplacement(sb, replacement);
			}
	
			m.appendTail(sb);
			this.message_input = sb.toString();
		}
		
		public String getUnformattedComponentText() {
			return this.message_input;
		}
		
		@Override
		public ITextComponent createCopy() {
			return new ChatMessage(this.message_input);
		}
	}
}